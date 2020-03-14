package com.test.hotelsearch.model;

import java.util.ArrayList;
import java.util.List;

public class HotelSearchResponse {

	private String transactionStatus;
	
	private List<Message> messages;
	
	private List<HotelPostRequest> hotels;

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public List<Message> getMessages() {
		if(messages==null) {
			messages=new ArrayList<>();
		}
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<HotelPostRequest> getHotels() {
		if(hotels==null) {
			hotels=new ArrayList<>();
		}
		return hotels;
	}

	public void setHotels(List<HotelPostRequest> hotels) {
		this.hotels = hotels;
	}
	
}
