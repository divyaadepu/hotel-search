package com.test.hotelsearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {

	@JsonProperty("code")
	private String code;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("record")
	private String record;

	public Message(String code, String description, String record) {
		super();
		this.code = code;
		this.description = description;
		this.record = record;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}
	
	
}
