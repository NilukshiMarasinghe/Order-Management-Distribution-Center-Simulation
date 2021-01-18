package com.demigods.ordergeneratorservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demigods.ordergeneratorservice.dto.OrderStatus;
import com.demigods.ordergeneratorservice.dto.WeightProcessedPerSecondMetric;
import com.demigods.ordergeneratorservice.exception.IllegalParameterException;
import com.demigods.ordergeneratorservice.model.Order;
import com.demigods.ordergeneratorservice.repository.OrderRepository;
import com.demigods.ordergeneratorservice.util.OrderGeneratorUtils;

@Service
public class MetricService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	public List<WeightProcessedPerSecondMetric> getWeightTimePerOrderMetric(String start, String end) {
		
		Date startDt = OrderGeneratorUtils.parseDate(start);
		Date endDt = OrderGeneratorUtils.parseDate(end);
		
		if(!validateRangeDate(startDt, endDt)) throw new IllegalParameterException("Invalid date range.");
		
		List<Order> compOrders = orderRepo.findByStatusAndCompletedTimeBetween(OrderStatus.COMPLETE, startDt, endDt);
		
		List<WeightProcessedPerSecondMetric> valueList = new ArrayList<WeightProcessedPerSecondMetric>();
		compOrders.forEach(order -> {
			double weightPerSec = order.getTotalWeight() / order.getTimeTaken();
			valueList.add(new WeightProcessedPerSecondMetric(order.getId(), weightPerSec));
		});
		return valueList;
	}
	
	public List<WeightProcessedPerSecondMetric> getWeightTimePerOrderMetric() {
		
		List<Order> compOrders = orderRepo.findByStatus(OrderStatus.COMPLETE);
		
		List<WeightProcessedPerSecondMetric> valueList = new ArrayList<WeightProcessedPerSecondMetric>();
		compOrders.forEach(order -> {
			double weightPerSec = order.getTotalWeight() / order.getTimeTaken();
			valueList.add(new WeightProcessedPerSecondMetric(order.getId(), weightPerSec));
		});
		return valueList;
	}
	
	private boolean validateRangeDate(Date start, Date end) {
		return (end.getTime() - start.getTime() > 0);
	}

}
