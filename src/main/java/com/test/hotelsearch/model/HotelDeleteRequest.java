package com.test.hotelsearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelDeleteRequest {

	@JsonProperty("hotelId")
	private String hotelId;
	
	@JsonProperty("hotelName")
	private String hotelName;
	
	@JsonProperty("priceGreaterThan")
	private String priceGreaterThan;
	
	@JsonProperty("priceLessThan")
	private String priceLessThan;
	
	@JsonProperty("location")
	private String location;
	
	@JsonProperty("noMatchingKeywords")
	private String noMatchingKeywords;

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

	public String getPriceGreaterThan() {
		return priceGreaterThan;
	}

	public void setPriceGreaterThan(String priceGreaterThan) {
		this.priceGreaterThan = priceGreaterThan;
	}

	public String getPriceLessThan() {
		return priceLessThan;
	}

	public void setPriceLessThan(String priceLessThan) {
		this.priceLessThan = priceLessThan;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNoMatchingKeywords() {
		return noMatchingKeywords;
	}

	public void setNoMatchingKeywords(String noMatchingKeywords) {
		this.noMatchingKeywords = noMatchingKeywords;
	}

	@Override
	public String toString() {
		return "HotelDeleteRequest [hotelId=" + hotelId + ", hotelName=" + hotelName + ", priceGreaterThan="
				+ priceGreaterThan + ", priceLessThan=" + priceLessThan + ", location=" + location
				+ ", noMatchingKeywords=" + noMatchingKeywords + "]";
	}
	
	
}
