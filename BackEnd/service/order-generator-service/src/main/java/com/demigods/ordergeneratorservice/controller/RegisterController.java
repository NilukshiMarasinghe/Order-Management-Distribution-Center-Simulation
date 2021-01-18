package com.demigods.ordergeneratorservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demigods.ordergeneratorservice.dto.RegistrationDTO;
import com.demigods.ordergeneratorservice.service.RegisterService;

@RestController
@RequestMapping("register") 
public class RegisterController {
	
	Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	private RegisterService registerService;
	
	@PostMapping(path = "/registry", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> register(@RequestBody RegistrationDTO regDetails) {
		logger.info("Recieved new registration request : "+regDetails.getName()+" at "+regDetails.getUri());
		return new ResponseEntity<>(registerService.registerObserver(regDetails), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/registrations")
	public ResponseEntity<?> getRegistrations() {
		return new ResponseEntity<>(registerService.getRegistrations(), HttpStatus.OK);
	}
	
	
}
