package com.test.hotelsearch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.hotelsearch.constants.HotelConstants;
import com.test.hotelsearch.model.HotelDeleteRequest;
import com.test.hotelsearch.model.HotelPostResponse;
import com.test.hotelsearch.model.HotelSearchRequest;
import com.test.hotelsearch.model.HotelSearchResponse;
import com.test.hotelsearch.model.HotelUpdateRequest;
import com.test.hotelsearch.model.Hotels;
import com.test.hotelsearch.model.Message;
import com.test.hotelsearch.service.HotelService;

@RestController
@RequestMapping("hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@RequestMapping(value = "/postHotels", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HotelPostResponse> postHotels(@RequestBody Hotels listOfhotels,
			@RequestHeader(value = "Authorization", required = false) String autorization) {
		List<Message> errorMessage = new ArrayList<Message>();
		try {
			if (!HotelConstants.ADMIN_LOGIN.equals(autorization)) {
				errorMessage.add(new Message("INVALID_LOGIN", "You are not authorized to insert", ""));
				HotelPostResponse hotelPostResponse = hotelService.buildErrorResponse(errorMessage);
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(hotelPostResponse);
			}
			return hotelService.posthotels(listOfhotels);
		} catch (Exception exception) {
			errorMessage
					.add(new Message("SYSTEM_ERROR", "System error occured while processing", exception.getMessage()));
			HotelPostResponse hotelPostResponse = hotelService.buildErrorResponse(errorMessage);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hotelPostResponse);
		}
	}

	@RequestMapping(value = "/getHotels", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HotelSearchResponse> getHotels(@RequestBody HotelSearchRequest hotelSearchRequest) {
		try {
			return hotelService.getHotels(hotelSearchRequest);
		} catch (Exception exception) {
			List<Message> errorMessage = new ArrayList<Message>();
			errorMessage
					.add(new Message("SYSTEM_ERROR", "System error occured while processing", exception.getMessage()));
			HotelSearchResponse hotelSearchResponse = hotelService.buildErrorSearchResponse(errorMessage);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hotelSearchResponse);
		}
	}

	@RequestMapping(value = "/deleteHotels", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HotelPostResponse> getHotels(@RequestBody HotelDeleteRequest hotelDeleteRequest,
			@RequestHeader(value = "Authorization", required = false) String autorization) {
		List<Message> errorMessage = new ArrayList<Message>();
		try {
			if (!HotelConstants.ADMIN_LOGIN.equals(autorization)) {
				errorMessage.add(new Message("INVALID_LOGIN", "You are not authorized to delete records", ""));
				HotelPostResponse hotelPostResponse = hotelService.buildErrorResponse(errorMessage);
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(hotelPostResponse);
			}
			return hotelService.deleteHotels(hotelDeleteRequest);
		} catch (Exception exception) {
			errorMessage
					.add(new Message("SYSTEM_ERROR", "System error occured while processing", exception.getMessage()));
			HotelPostResponse hotelPostResponse = hotelService.buildErrorResponse(errorMessage);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hotelPostResponse);
		}
	}

	@RequestMapping(value = "/updateHotels", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HotelPostResponse> updateHotels(@RequestBody HotelUpdateRequest hotelUpdateRequest,
			@RequestHeader(value = "Authorization", required = false) String autorization) {
		List<Message> errorMessage = new ArrayList<Message>();
		try {
			if (!HotelConstants.ADMIN_LOGIN.equals(autorization)) {
				errorMessage.add(new Message("INVALID_LOGIN", "You are not authorized to update records", ""));
				HotelPostResponse hotelPostResponse = hotelService.buildErrorResponse(errorMessage);
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(hotelPostResponse);
			}
			return hotelService.updateHotels(hotelUpdateRequest);
		} catch (Exception exception) {
			errorMessage
					.add(new Message("SYSTEM_ERROR", "System error occured while processing", exception.getMessage()));
			HotelPostResponse hotelPostResponse = hotelService.buildErrorResponse(errorMessage);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hotelPostResponse);
		}
	}

}
