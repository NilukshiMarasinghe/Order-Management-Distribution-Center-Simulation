package com.demigods.ordergeneratorservice.dto;

import java.util.List;

import com.demigods.ordergeneratorservice.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponseDTO {
	
	private List<Product> content;
	
	public ProductResponseDTO() {
		super();
	}

	public ProductResponseDTO(List<Product> content) {
		super();
		this.content = content;
	}

	public List<Product> getContent() {
		return content;
	}

	public void setContent(List<Product> content) {
		this.content = content;
	}

}
