package com.demigods.orderManagementService.akka.actor;

import java.util.ArrayList;
import java.util.List;

import com.demigods.orderManagementService.model.*;
import com.demigods.orderManagementService.service.OrderService;
import com.demigods.orderManagementService.service.PackingStationService;
import com.demigods.orderManagementService.service.TaskService;
import com.demigods.orderManagementService.service.WorkerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.AbstractActor;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class OrderProcessor extends AbstractActor {

	Logger logger = LoggerFactory.getLogger(Notifier.class);

	@Autowired
	OrderService orderService;

	@Autowired
	TaskService taskService;

	@Autowired
	WorkerService workerService;

	@Autowired
	PackingStationService packingStationService;

	/* *  Messages  * */

    // New Order Message
    public static class NewOrder {
		private Order order;

		public NewOrder(Order order) {
			this.order = order;
		}

		public Order getOrder() {
			return order;
		}		
	}

	// Task Completed Message
	public static class TaskCompleted {
		private Task task;

		public TaskCompleted(Task task) {
			this.task = task;
		}

		public Task getTask() {
			return this.task;
		}
	}

	/* *  Actions  * */
	
	void onNewOrder(NewOrder message) {
		Order order = message.order;
		order.setPendingItems(order.getItems());
		this.orderService.createOrder(order);
		this.processOrders();
	}

	void onTaskComplete(TaskCompleted message){
		Task task = message.task;
		this.taskService.updateTask(task);
		Order order = this.orderService.getOrderById(task.getOrderId());

		if(order.isOrderComplete()){
			order.completeOrder();
			PackingStation packingStation = packingStationService.getPackingStationById(order.getPackingStationId());
			packingStation.setIsAvailable(true);
			orderService.updateOrder(order);
			packingStationService.updatePackingStation(packingStation);
		}
		this.processOrders();
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(NewOrder.class, this::onNewOrder)
				.match(TaskCompleted.class, this::onTaskComplete)
				.build();
	}

	/**
	 * Fetches order details, task details, worker details ,packing station details from DB and other services and processes
	 * the orders queued.
	 */
    public void processOrders() {
        List<Order> pendingOrders = this.orderService.getAllPendingOrders();
        List<Worker> availableWorkers = this.workerService.getAvailableWorkers();

        for(Worker worker : availableWorkers) {
				worker.setIsAvailable(true);
				worker.setAvailableCapacity(worker.getCapacity());
				Task task = new Task(worker.getId());
				task.setItems(new ArrayList<OrderItem>());

				for (Order order : pendingOrders) {

					List<PackingStation> availablePackingStations = this.packingStationService.getAvailablePackingStations();

					if(
							// If order is new and packing stations are available, or order is in progress
							(order.getStatus().equals(OrderStatus.NEW) && !availablePackingStations.isEmpty())
									|| order.getStatus().equals(OrderStatus.PROCESSING)
					)
					{
						for (OrderItem orderItem : order.getPendingItems()) {

							int eligibleQty = (int) Math.floor(worker.getAvailableCapacity()/orderItem.getProduct().getWeight());

							/*If eligibleQty is greater than desired quantity set qty as eligibleQty*/
							if (eligibleQty > orderItem.getQty()) {
								eligibleQty = orderItem.getQty();
							}

							if (eligibleQty > 0) { /* Worker is to take some/all items of this type */
								OrderItem eligibleOrderItem = new OrderItem(orderItem.getProduct(), eligibleQty);
								task.getItems().add(eligibleOrderItem);
								worker.setAvailableCapacity(worker.getAvailableCapacity() - (orderItem.getProduct().getWeight() * eligibleQty));
								orderItem.setQty(orderItem.getQty() - eligibleQty);
								worker.setIsAvailable(false); /* Make worker busy */
							}

							if (worker.getAvailableCapacity() <= 0) break;
						}

						/* Remove pending items if quantity is zero */
						order.getPendingItems().removeIf(orderItem -> orderItem.getQty() <= 0);

						if (!worker.getIsAvailable()) { /* Worker not available - task delegated */
							if(order.getStatus().equals(OrderStatus.NEW)){
								PackingStation packingStation = availablePackingStations.get(availablePackingStations.size() - 1);
								order.setPackingStationId(packingStation.getId());
								order.setStatus(OrderStatus.PROCESSING);
								packingStation.setIsAvailable(false);
								this.packingStationService.updatePackingStation(packingStation);
							}
							task.setStatus(TaskStatus.PROCESSING);
							task.setOrderId(order.getId());
							task.setPackingStationId(order.getPackingStationId());
							task = this.taskService.createTask(task);
							order.getTasks().add(task);
							this.orderService.updateOrder(order);
							logger.info("Task delegated on Order "+order.getId()+" to worker "+task.getWorkerId());
							break;
						}
					}
				}
		}
    }

}
