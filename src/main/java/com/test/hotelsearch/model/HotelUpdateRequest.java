package com.test.hotelsearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelUpdateRequest {

	@JsonProperty("updateValues")
	private HotelUpdateParams hotelUpdateParams;
	
	@JsonProperty("whenCondition")
	private HotelUpdateWhereParams hotelUpdateWhereParams;

	public HotelUpdateParams getHotelUpdateParams() {
		return hotelUpdateParams;
	}

	public void setHotelUpdateParams(HotelUpdateParams hotelUpdateParams) {
		this.hotelUpdateParams = hotelUpdateParams;
	}

	public HotelUpdateWhereParams getHotelUpdateWhereParams() {
		return hotelUpdateWhereParams;
	}

	public void setHotelUpdateWhereParams(HotelUpdateWhereParams hotelUpdateWhereParams) {
		this.hotelUpdateWhereParams = hotelUpdateWhereParams;
	}
	
	
}
