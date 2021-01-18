package com.demigods.orderManagementService.service;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import com.demigods.orderManagementService.akka.actor.Notifier;
import com.demigods.orderManagementService.akka.actor.OrderProcessor;
import com.demigods.orderManagementService.akka.integration.SpringProps;
import com.demigods.orderManagementService.dto.request.task.TaskUpdateRequest;
import com.demigods.orderManagementService.dto.response.task.TaskUpdateResponse;
import com.demigods.orderManagementService.helper.ModelMapperHelper;
import com.demigods.orderManagementService.model.Task;
import com.demigods.orderManagementService.repository.TaskRespository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class TaskService {

	@Autowired
	private ModelMapperHelper modelMapper;

	@Autowired
	private TaskRespository taskRespository;

	@Autowired
	private ActorSystem system;

	ActorSelection orderProcessorActor;

	@PostConstruct
	public void initialize() {
		orderProcessorActor = system.actorSelection("/user/OrderProcessorActor");
	}

	public Task createTask(Task task){
		Task savedTask = this.taskRespository.save(task);
		ActorRef notifier = system.actorOf(SpringProps.create(system, Notifier.class));
		notifier.tell(new Notifier.NewTask(savedTask), ActorRef.noSender());
		return savedTask;
	}

	public Task updateTask(Task task){
		return this.taskRespository.save(task);
	}

	public TaskUpdateResponse updateTaskStatus (TaskUpdateRequest taskUpdateRequest) {
		Optional<Task> optionalTask = this.taskRespository.findById(taskUpdateRequest.getId());
		if(optionalTask.isPresent()){
			Task task = optionalTask.get();
			task.setStatus(taskUpdateRequest.getStatus());
			orderProcessorActor.tell(new OrderProcessor.TaskCompleted(task), ActorRef.noSender());
			return new TaskUpdateResponse("Task Updated");
		}else{
			return new TaskUpdateResponse("Task Not Found");
		}
	}

	public List<Task> getAllTasks(){
		return this.taskRespository.findAll();
	}

	public List<Task> getAllTasksByWorker(int workerId){ return this.taskRespository.findByWorkerId(workerId); };

}
