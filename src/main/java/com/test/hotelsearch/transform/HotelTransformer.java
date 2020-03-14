package com.test.hotelsearch.transform;

import org.springframework.stereotype.Component;

import com.test.hotelsearch.dao.entity.Hotel;
import com.test.hotelsearch.model.HotelPostRequest;

@Component
public class HotelTransformer {

	public Hotel transformToDomainObject(HotelPostRequest hotelPostRequest) {
		Hotel hotel=new Hotel();
		
		hotel.setHotelName(hotelPostRequest.getHotelName());
		hotel.setLocation(hotelPostRequest.getLocation().toUpperCase());
		hotel.setPrice(Integer.valueOf(hotelPostRequest.getPriceStartsFrom()));
		hotel.setSpecialities(hotelPostRequest.getSpecialities());
		
		return hotel;
	}

	public HotelPostRequest transformToJsonObject(Hotel hotel) {
		HotelPostRequest hotelPostRequest=new HotelPostRequest();
		hotelPostRequest.setHotelName(hotel.getHotelName());
		hotelPostRequest.setLocation(hotel.getLocation());
		hotelPostRequest.setPriceStartsFrom(String.valueOf(hotel.getPrice()));
		hotelPostRequest.setSpecialities(hotel.getSpecialities());
		return hotelPostRequest;
	}

}
