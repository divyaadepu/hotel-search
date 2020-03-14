package com.test.hotelsearch.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelPostResponse {

	@JsonProperty("transactionStatus")
	private String transactionStatus;
	
	@JsonProperty("messages")
	private List<Message> messages;

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

	
}
