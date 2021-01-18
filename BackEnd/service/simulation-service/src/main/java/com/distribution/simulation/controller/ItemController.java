package com.distribution.simulation.controller;


import antlr.StringUtils;
import com.distribution.simulation.dto.response.simulation.ItemSearch;
import com.distribution.simulation.dto.response.simulation.ItemSearchMin;
import com.distribution.simulation.dto.response.wrapper.ListResponseWrapper;
import com.distribution.simulation.dto.response.wrapper.SimpleResponseWrapper;
import com.distribution.simulation.entity.simulation.Item;
import com.distribution.simulation.exception.ComplexValidationException;
import com.distribution.simulation.modelmapper.ModelMapper;
import com.distribution.simulation.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ModelMapper modelMapper;

    // Return all the items in detail format (fully cordinated)
    @GetMapping("${app.endpoint.getAllItems}")
    public ResponseEntity<ListResponseWrapper<ItemSearch>> itemSearch() {
        List<Item> items = this.itemService.getAllItems();
        List<ItemSearch> response = this.modelMapper.map(items, ItemSearch.class);
        return new ResponseEntity<ListResponseWrapper<ItemSearch>>(
                new ListResponseWrapper<ItemSearch>(response), HttpStatus.CREATED);
    }


    // Return all the items in minified version as per the Technical Overview Doc 1 ppt - SLIDE 8
    @GetMapping("${app.endpoint.getAllItemsMin}")
    public ResponseEntity<ListResponseWrapper<ItemSearchMin>> itemMinSearch() {
        List<Item> items = this.itemService.getAllItems();

        List<ItemSearchMin> itemsList = new ArrayList<>(items.size());
        items.forEach(item -> {
            ItemSearchMin itemOb = new ItemSearchMin();
            itemOb.setId(item.getId());
            itemOb.setName(item.getName());
            itemOb.setProductId(item.getProductId());
            itemOb.setWeight(item.getWeight());
            itemOb.setSupplier(item.getSupplier());
            itemOb.setLocation(item.getShelf().getLabel());
            itemsList.add(itemOb);
        });


        return new ResponseEntity<ListResponseWrapper<ItemSearchMin>>(
                new ListResponseWrapper<ItemSearchMin>(itemsList), HttpStatus.CREATED);
    }


    @GetMapping("${app.endpoint.itemView}")
    public ResponseEntity<SimpleResponseWrapper<ItemSearchMin>> viewItem(@PathVariable String productId) {

        if (productId == null) {
            throw new ComplexValidationException("item", "productIdNull");
        }
        Item item = this.itemService.viewByProductId(productId);

        ItemSearchMin itemOb = new ItemSearchMin();
        itemOb.setId(item.getId());
        itemOb.setName(item.getName());
        itemOb.setProductId(item.getProductId());
        itemOb.setWeight(item.getWeight());
        itemOb.setSupplier(item.getSupplier());
        itemOb.setLocation(item.getShelf().getLabel());


        return new ResponseEntity<SimpleResponseWrapper<ItemSearchMin>>(
                new SimpleResponseWrapper<ItemSearchMin>(itemOb), HttpStatus.CREATED);
    }


    @GetMapping("${app.endpoint.itemById}")
    public ResponseEntity<SimpleResponseWrapper<ItemSearchMin>> itemById(@PathVariable Long id) {

        if (id == null) {
            throw new ComplexValidationException("item", "idNull");
        }
        Item item = this.itemService.view(id);

        ItemSearchMin itemOb = new ItemSearchMin();
        itemOb.setId(item.getId());
        itemOb.setName(item.getName());
        itemOb.setProductId(item.getProductId());
        itemOb.setWeight(item.getWeight());
        itemOb.setSupplier(item.getSupplier());
        itemOb.setLocation(item.getShelf().getSection().getSectionId());


        return new ResponseEntity<SimpleResponseWrapper<ItemSearchMin>>(
                new SimpleResponseWrapper<ItemSearchMin>(itemOb), HttpStatus.CREATED);
    }

}
