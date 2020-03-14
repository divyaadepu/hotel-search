package com.test.hotelsearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelUpdateParams {

	@JsonProperty("hotelName")
	private String hotelName;
	
	@JsonProperty("priceToAddOrSub")
	private String priceToAddOrSub;
	
	@JsonProperty("location")
	private String location;
	
	@JsonProperty("addKeywords")
	private String addKeywords;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getPriceToAddOrSub() {
		return priceToAddOrSub;
	}

	public void setPriceToAddOrSub(String priceToAddOrSub) {
		this.priceToAddOrSub = priceToAddOrSub;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddKeywords() {
		return addKeywords;
	}

	public void setAddKeywords(String addKeywords) {
		this.addKeywords = addKeywords;
	}

	@Override
	public String toString() {
		return "HotelUpdateParams [hotelName=" + hotelName + ", priceToAddOrSub=" + priceToAddOrSub + ", location="
				+ location + ", addKeywords=" + addKeywords + "]";
	}
}
