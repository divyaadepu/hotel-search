package com.test.hotelsearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelSearchRequest {

	@JsonProperty("hotelId")
	private String hotelId;
	
	@JsonProperty("hotelName")
	private String hotelName;
	
	@JsonProperty("priceRangeFrom")
	private String priceRangeFrom;
	
	@JsonProperty("priceRangeTo")
	private String priceRangeTo;
	
	@JsonProperty("location")
	private String location;
	
	@JsonProperty("keyword")
	private String keyword;
	
	private boolean findAll;

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getPriceRangeFrom() {
		return priceRangeFrom;
	}

	public void setPriceRangeFrom(String priceRangeFrom) {
		this.priceRangeFrom = priceRangeFrom;
	}

	public String getPriceRangeTo() {
		return priceRangeTo;
	}

	public void setPriceRangeTo(String priceRangeTo) {
		this.priceRangeTo = priceRangeTo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public boolean isFindAll() {
		return findAll;
	}

	public void setFindAll(boolean findAll) {
		this.findAll = findAll;
	}

	@Override
	public String toString() {
		return "HotelSearchRequest [hotelId=" + hotelId + ", hotelName=" + hotelName + ", priceRangeFrom="
				+ priceRangeFrom + ", priceRangeTo=" + priceRangeTo + ", location=" + location + ", keyword=" + keyword
				+ "]";
	}
	
}
