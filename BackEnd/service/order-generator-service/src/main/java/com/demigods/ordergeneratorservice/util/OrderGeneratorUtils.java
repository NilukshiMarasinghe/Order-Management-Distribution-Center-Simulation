package com.demigods.ordergeneratorservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.demigods.ordergeneratorservice.exception.IllegalParameterException;
import com.demigods.ordergeneratorservice.model.OrderItem;
import com.demigods.ordergeneratorservice.model.Product;

public class OrderGeneratorUtils {
	
	static Random rand = new Random();
	
	static Integer[] probabilites = new Integer[] {/*First item*/100, /*Second item*/50, /*Third item*/25, /*Fourth item*/13, /*Fifth item*/6};
	
	public static List<OrderItem> generate(List<Product> productList) {
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		
		for(Integer prob: probabilites ) {
			if(rand.nextInt(100) < prob) {
				orderItems.add(getItem(productList));
			} else {
				break;
			}
		}
		return orderItems;
	}
	
	public static double getTotalWeight(List<OrderItem> orderItems) {
		return orderItems.stream().mapToDouble(orderItem -> orderItem.getProduct().getWeight() * orderItem.getQty()).sum();
	}
	
	private static OrderItem getItem(List<Product> productList) {
		int index = rand.nextInt(productList.size());
		Product prod = productList.get(index);
		int qty = getQty();
		productList.remove(index);
		
		return new OrderItem(prod, qty);
	}
	
	private static int getQty() {
		if(rand.nextInt(100) < 3) return 3;
		if(rand.nextInt(100) < 33) return 2;
		
		return 1;
	}
	
	public static Date parseDate(String dateStr) throws IllegalParameterException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			throw new IllegalParameterException("Invalid date format. Date should be in the format of dd-MM-yyyy (eg: 18-12-2020)");
		}
	}
	

}
