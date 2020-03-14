package com.test.hotelsearch.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Hotels {

	@JsonProperty("hotels")
	private List<HotelPostRequest> hotels;

	public List<HotelPostRequest> getHotels() {
		return hotels;
	}

	public void setHotels(List<HotelPostRequest> hotels) {
		this.hotels = hotels;
	}
}
