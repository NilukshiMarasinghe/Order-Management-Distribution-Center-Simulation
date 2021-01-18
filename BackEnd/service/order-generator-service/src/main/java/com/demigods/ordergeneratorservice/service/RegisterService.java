package com.demigods.ordergeneratorservice.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.demigods.ordergeneratorservice.dto.RegistrationDTO;
import com.demigods.ordergeneratorservice.exception.AlreadyRegisteredException;

@Service
public class RegisterService {
	
	List<RegistrationDTO> registrationList;
	
	@PostConstruct
	public void initialize() {
		registrationList = new ArrayList<RegistrationDTO>();
	}
	
	public String registerObserver(RegistrationDTO details) {
		registrationList.forEach(reg -> {
			if(reg.getUri().equalsIgnoreCase(details.getUri())) throw new AlreadyRegisteredException("Already registered.");
		});
		
		registrationList.add(details);
		return "Registration successfull to order service.";
	}
	
	public List<RegistrationDTO> getRegistrations() {
		return this.registrationList;
	}
	
}
