package com.demigods.ordergeneratorservice.dto;

public class RegistrationDTO {
	
	private String uri;
	private String name;
	
	public RegistrationDTO() {
		super();
	}
	
	public RegistrationDTO(String uri, String name) {
		super();
		this.uri = uri;
		this.name = name;
	}
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
