package com.demigods.ordergeneratorservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demigods.ordergeneratorservice.service.MetricService;

@RestController
@RequestMapping("metric") 
public class MetricController {
	
	@Autowired
	private MetricService metricService;

	@CrossOrigin(origins = "*")
	@GetMapping("/weight-processing-time")
	public ResponseEntity<?> weightProcessedPerSec(@RequestParam(value = "start", required = true) String start,@RequestParam(value = "end", required = true) String end) {
		return new ResponseEntity<>(metricService.getWeightTimePerOrderMetric(start, end), HttpStatus.OK);
	}
	
//	@GetMapping("/weight-processing-time")
//	public ResponseEntity<?> weightProcessedPerSecDeep() {
//		return new ResponseEntity<>(metricService.getWeightTimePerOrderMetric(), HttpStatus.OK);
//	}
}
