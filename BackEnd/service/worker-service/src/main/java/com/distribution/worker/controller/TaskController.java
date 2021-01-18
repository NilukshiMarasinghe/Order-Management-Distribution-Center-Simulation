package com.distribution.worker.controller;


import com.distribution.worker.dto.request.LocationIn;
import com.distribution.worker.dto.request.TaskRequest;
import com.distribution.worker.dto.response.ms2ms.ItemOut;
import com.distribution.worker.dto.response.ms2ms.UpdateTaskIn;
import com.distribution.worker.dto.response.worker.TaskRecordSearch;
import com.distribution.worker.dto.response.worker.TaskRequestOut;
import com.distribution.worker.dto.response.wrapper.ListResponseWrapper;
import com.distribution.worker.dto.response.wrapper.SimpleResponseWrapper;
import com.distribution.worker.entity.worker.Item;
import com.distribution.worker.entity.worker.OrderItem;
import com.distribution.worker.entity.worker.TaskRecord;
import com.distribution.worker.modelmapper.ModelMapper;
import com.distribution.worker.service.Ms2MsService;
import com.distribution.worker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.Random;

@RestController
public class TaskController {

    @Autowired
    private Ms2MsService msService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TaskService taskService;

    @PostMapping("${app.endpoint.getShortestPath}")
    public ResponseEntity<SimpleResponseWrapper<TaskRequestOut>> getShortestPath(@Validated @RequestBody TaskRequest in) {


        // mark Worker as UNAVAILABLE
        this.msService.changeWorkerStatus(new Long(in.getWorkerId()), false);

        // separate the list of location string
        List<TaskRequest.OrderItemIn> inComingItems = in.getItems();
        List<String> sections = inComingItems.stream()
                .map(orderItemIn -> orderItemIn.getProduct().getLocation())
                .collect(Collectors.toList());

        // getting packing station id
        Long packingStationId = Long.parseLong(in.getPackingStationId());
        LocationIn location = new LocationIn(packingStationId, sections);
        //  Make micro service call for Simulation Service to get shortest path
        List<String> shortestPath = this.msService.getShortestPath(location);

        TaskRecord record = new TaskRecord();
        record.setOrderId(in.getOrderId());
        record.setWorkerId(new Long(in.getWorkerId()));
        record.setPackingStationId(in.getPackingStationId());
        record.setStatus(in.getStatus());
        record.setTaskId(in.getId());

        List<OrderItem> incomingItems = new ArrayList<>(in.getItems().size());
        in.getItems().forEach(orderItemIn -> {
            //Item item = this.modelMapper.map(orderItemIn.getProduct(), Item.class);
            Item item = new Item();
            item.setProductId(orderItemIn.getProduct().getProductId());

            int qty = orderItemIn.getQty();
            OrderItem orderItem = new OrderItem();
            orderItem.setQty(qty);
            orderItem.setProduct(item);
            orderItem.setTaskRecord(record);
            incomingItems.add(orderItem);
        });

        record.setItems(incomingItems);

        // convert shortest path list to single string
        String shortestPathStr = shortestPath.stream().map(String::toString).collect(Collectors.joining("|"));
        record.setShortedPath(shortestPathStr);
        // store task record
        TaskRecord added = this.taskService.save(record);
        TaskRequestOut out = this.modelMapper.map(added, TaskRequestOut.class);

        // Dummy Time out for mock the picking order and releasing the WORK as available
        try {
            Random rand = new Random();
            Thread.sleep(rand.nextInt(100)*1000);
            this.msService.changeWorkerStatus(record.getWorkerId(), true);
            UpdateTaskIn upIn = new UpdateTaskIn();
            upIn.setId(record.getTaskId());
            upIn.setStatus("COMPLETE");
            msService.taskUpdate(upIn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<SimpleResponseWrapper<TaskRequestOut>>(
                new SimpleResponseWrapper<TaskRequestOut>(out), HttpStatus.CREATED);
    }


    @GetMapping("${app.endpoint.getTasksByPicker}")
    public ResponseEntity<ListResponseWrapper<TaskRecordSearch>> getTasksByPicker(@PathVariable Long pickerId) {

        List<TaskRecord> records = this.taskService.getTasksByPickerId(pickerId);
        List<TaskRecordSearch> searchResult = this.modelMapper.map(records, TaskRecordSearch.class);

        searchResult.forEach(record -> {
            // record.setItems(new ArrayList<>());
            record.getItems().forEach(orderItem -> {
                ItemOut.ProductOut productOut = this.msService.fetchItemDetail(orderItem.getProduct().getProductId());
                TaskRecordSearch.OrderItemOut.ItemOut item = this.modelMapper.map(productOut, TaskRecordSearch.OrderItemOut.ItemOut.class);
                orderItem.setProduct(item);
            });
        });
        return new ResponseEntity<ListResponseWrapper<TaskRecordSearch>>(
                new ListResponseWrapper<TaskRecordSearch>(searchResult), HttpStatus.CREATED);
    }

}
