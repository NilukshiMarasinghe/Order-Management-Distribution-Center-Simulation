package com.demigods.orderManagementService.akka.actor;

import com.demigods.orderManagementService.dto.internal.TaskRequest;
import com.demigods.orderManagementService.helper.ModelMapperHelper;
import com.demigods.orderManagementService.model.*;
import com.demigods.orderManagementService.service.OrderService;
import com.demigods.orderManagementService.service.TaskService;
import com.demigods.orderManagementService.service.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import akka.actor.AbstractActor;
import org.springframework.web.client.RestTemplate;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Notifier extends AbstractActor {

    Logger logger = LoggerFactory.getLogger(Notifier.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private Environment environment;

    @Autowired
    private ModelMapperHelper modelMapperHelper;

    @Autowired
    private RestTemplate restTemplate;

    /* *  Messages  * */

    //New Task
    public static class NewTask {
        private Task task;

        public NewTask(Task task) {
            this.task = task;
        }

        public Task getTask() {
            return task;
        }
    }

    //Order Update
    public static class OrderUpdate {
        private Order order;

        public OrderUpdate(Order order) {
            this.order = order;
        }

        public Order getOrder() {
            return order;
        }
    }

    /* *  Actions  * */

    void onNewTask(NewTask message) {
        // Notify Worker Service on new Task
        try {
            TaskRequest taskRequest = this.modelMapperHelper.mapObject(message.getTask(), TaskRequest.class);
            restTemplate.postForLocation(environment.getProperty("worker.service.task.notify.url"), taskRequest);
            logger.info("Notified new task to Worker Service");
        } catch (Exception e) {
            logger.error("Error occured while sending task to worker service");
            logger.error("Error: " + e.getMessage());
        }
    }

    void onOrderUpdate(OrderUpdate message) {
        // Notify order generator with status
        try {
            restTemplate.put(environment.getProperty("order.generator.service.order.status.update.url") + "/" + message.order.getId(), message.order.getStatus().name());
            logger.info("Notified order generator service status");
        } catch (Exception e) {
            logger.error("Error occured while updating order in order generator service");
            logger.error("Error: " + e.getMessage());
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(NewTask.class, this::onNewTask)
                .match(OrderUpdate.class, this::onOrderUpdate)
                .build();
    }
}
