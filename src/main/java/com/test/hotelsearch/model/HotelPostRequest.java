package com.test.hotelsearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelPostRequest {

	@JsonProperty("hotelName")
	private String hotelName;
	
	@JsonProperty("location")
	private String location;
	
	@JsonProperty("priceStartsFrom")
	private String priceStartsFrom;
	
	@JsonProperty("specialities")
	private String specialities;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPriceStartsFrom() {
		return priceStartsFrom;
	}

	public void setPriceStartsFrom(String priceStartsFrom) {
		this.priceStartsFrom = priceStartsFrom;
	}

	public String getSpecialities() {
		return specialities;
	}

	public void setSpecialities(String specialities) {
		this.specialities = specialities;
	}

	@Override
	public String toString() {
		return "HotelPostRequest [hotelName=" + hotelName + ", location=" + location + ", priceStartsFrom="
				+ priceStartsFrom + ", specialities=" + specialities + "]";
	}
	
	
}
