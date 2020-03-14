package com.test.hotelsearch.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.hotelsearch.dao.HotelDao;
import com.test.hotelsearch.dao.entity.Hotel;
import com.test.hotelsearch.model.HotelDeleteRequest;
import com.test.hotelsearch.model.HotelPostRequest;
import com.test.hotelsearch.model.HotelPostResponse;
import com.test.hotelsearch.model.HotelSearchRequest;
import com.test.hotelsearch.model.HotelSearchResponse;
import com.test.hotelsearch.model.HotelUpdateParams;
import com.test.hotelsearch.model.HotelUpdateRequest;
import com.test.hotelsearch.model.Hotels;
import com.test.hotelsearch.model.Message;
import com.test.hotelsearch.specification.HotelSpecificationBuilder;
import com.test.hotelsearch.transform.HotelTransformer;
import com.test.hotelsearch.validator.HotelValidator;

@Service
public class HotelService {

	@Autowired
	private HotelValidator hotelValidator;

	@Autowired
	private HotelTransformer hotelTransformer;

	@Autowired
	private HotelDao hotelDao;

	public ResponseEntity<HotelPostResponse> posthotels(Hotels listOfhotels) {
		List<Message> messages = new ArrayList<Message>();
		HotelPostResponse hotelPostResponse = new HotelPostResponse();
		try {
			for (HotelPostRequest hotelPostRequest : listOfhotels.getHotels()) {
				List<Message> errorMessage = hotelValidator.validateHotel(hotelPostRequest);
				if (errorMessage != null && !errorMessage.isEmpty()) {
					messages.addAll(errorMessage);
				}
			}
			if (messages != null && !messages.isEmpty()) {
				hotelPostResponse = buildErrorResponse(messages);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hotelPostResponse);
			} else {
				List<Hotel> hotelList = new ArrayList<Hotel>();
				for (HotelPostRequest hotelPostRequest : listOfhotels.getHotels()) {
					Hotel hotel = hotelTransformer.transformToDomainObject(hotelPostRequest);
					hotelList.add(hotel);
				}
				hotelDao.saveAllHotels(hotelList);
				messages.add(new Message("RECORDS_INSERTED", "Records have been successfully inserted",
						String.valueOf(listOfhotels.getHotels().size())));
				hotelPostResponse = buildSuccessResponse(messages);
				return ResponseEntity.status(HttpStatus.OK).body(hotelPostResponse);
			}
		} catch (Exception exception) {
			messages.add(new Message("SYSTEM_ERROR", "System error occured while processing", exception.getMessage()));
			hotelPostResponse = buildErrorResponse(messages);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hotelPostResponse);
		}

	}

	private HotelPostResponse buildSuccessResponse(List<Message> messages) {
		HotelPostResponse hotelPostResponse = new HotelPostResponse();
		hotelPostResponse.setTransactionStatus("Success");
		hotelPostResponse.setMessages(messages);
		return hotelPostResponse;
	}

	public HotelPostResponse buildErrorResponse(List<Message> messages) {
		HotelPostResponse hotelPostResponse = new HotelPostResponse();
		hotelPostResponse.setTransactionStatus("Error");
		hotelPostResponse.setMessages(messages);
		return hotelPostResponse;
	}

	public ResponseEntity<HotelSearchResponse> getHotels(HotelSearchRequest hotelSearchRequest) {
		List<Message> messages = new ArrayList<Message>();
		HotelSearchResponse hotelSearchResponse = new HotelSearchResponse();
		try {
			messages = hotelValidator.validateSearchRequest(hotelSearchRequest);
			if (messages != null && !messages.isEmpty()) {
				hotelSearchResponse = buildErrorSearchResponse(messages);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hotelSearchResponse);
			} else {
				List<HotelPostRequest> hotelLists = new ArrayList<>();
				List<Hotel> hotels = new ArrayList<Hotel>();
				if (hotelSearchRequest.isFindAll()) {
					hotels = hotelDao.getAllHotels();
				} else {
					HotelSpecificationBuilder<Hotel> hotelSearchSpecification = new HotelSpecificationBuilder<Hotel>();
					hotels = hotelDao
							.getHotels(hotelSearchSpecification.buildHotelSearchSpecification(hotelSearchRequest));
				}
				if (hotels != null && !hotels.isEmpty()) {
					for (Hotel hotel : hotels) {
						hotelLists.add(hotelTransformer.transformToJsonObject(hotel));
					}
				} else {
					messages.add(new Message("NO_HOTELS_FOUND", "No hotels found for above search criteria",
							hotelSearchRequest.toString()));
				}
				hotelSearchResponse = buildSuccessSearchResponse(hotelLists, messages);
				return ResponseEntity.status(HttpStatus.OK).body(hotelSearchResponse);
			}
		} catch (Exception exception) {
			messages.add(new Message("SYSTEM_ERROR", "System error occured while processing", exception.getMessage()));
			hotelSearchResponse = buildErrorSearchResponse(messages);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hotelSearchResponse);
		}
	}

	public HotelSearchResponse buildErrorSearchResponse(List<Message> messages) {
		HotelSearchResponse hotelSearchResponse = new HotelSearchResponse();
		hotelSearchResponse.setTransactionStatus("Error");
		hotelSearchResponse.setMessages(messages);
		return hotelSearchResponse;
	}

	private HotelSearchResponse buildSuccessSearchResponse(List<HotelPostRequest> hotelLists, List<Message> messages) {
		HotelSearchResponse hotelSearchResponse = new HotelSearchResponse();
		hotelSearchResponse.setTransactionStatus("Success");
		hotelSearchResponse.setMessages(messages);
		hotelSearchResponse.setHotels(hotelLists);
		return hotelSearchResponse;
	}

	public ResponseEntity<HotelPostResponse> deleteHotels(HotelDeleteRequest hotelDeleteRequest) {
		List<Message> messages = new ArrayList<Message>();
		HotelPostResponse hotelPostResponse = new HotelPostResponse();
		try {
			messages = hotelValidator.validateDeleteHotelRequest(hotelDeleteRequest);
			if (messages != null && !messages.isEmpty()) {
				hotelPostResponse = buildErrorResponse(messages);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hotelPostResponse);
			} else {
				HotelSpecificationBuilder<Hotel> hotelSpecification = new HotelSpecificationBuilder<Hotel>();
				int n=hotelDao.deleteHotels(hotelSpecification.deleteRecordsCriteria(hotelDeleteRequest));
				if (n > 0) {
					messages.add(new Message("RECORDS_DELETED", "Records have been deleted successfully",""));
				} else {
					messages.add(new Message("NO_RECORDS_DELETED", "No records found for above criteria",
							String.valueOf(0)));
				}
				hotelPostResponse = buildSuccessDeleteResponse(messages);
				return ResponseEntity.status(HttpStatus.OK).body(hotelPostResponse);
			}
		} catch (Exception exception) {
			messages.add(new Message("SYSTEM_ERROR", "System error occured while processing", exception.getMessage()));
			hotelPostResponse = buildErrorResponse(messages);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hotelPostResponse);
		}
	}

	private HotelPostResponse buildSuccessDeleteResponse(List<Message> messages) {
		HotelPostResponse hotelPostResponse = new HotelPostResponse();
		hotelPostResponse.setTransactionStatus("Success");
		hotelPostResponse.setMessages(messages);
		return hotelPostResponse;
	}

	public ResponseEntity<HotelPostResponse> updateHotels(HotelUpdateRequest hotelUpdateRequest) {
		List<Message> messages = new ArrayList<Message>();
		HotelPostResponse hotelPostResponse = new HotelPostResponse();
		try {
			messages = hotelValidator.validateUpdateRequest(hotelUpdateRequest);
			if (messages != null && !messages.isEmpty()) {
				hotelPostResponse = buildErrorResponse(messages);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hotelPostResponse);
			} else {
				HotelSpecificationBuilder<Hotel> hotelSpecification = new HotelSpecificationBuilder<Hotel>();
				List<Hotel> hotels = hotelDao.getHotels(
						hotelSpecification.updateRecordsCriteria(hotelUpdateRequest.getHotelUpdateWhereParams()));

				if (hotels != null && !hotels.isEmpty()) {
					List<Integer> hotelIds = new ArrayList<Integer>();
					for (Hotel hotel : hotels) {
						hotelIds.add(hotel.getHotelId());
					}
					HotelUpdateParams hotelUpdateParams = createNullParamsIfEmpty(
							hotelUpdateRequest.getHotelUpdateParams());
					int n = hotelDao.updateHotels(hotelUpdateParams.getHotelName(),
							Integer.valueOf(hotelUpdateParams.getPriceToAddOrSub()), hotelUpdateParams.getLocation(),
							hotelUpdateParams.getAddKeywords(), hotelIds);
					if (n > 0) {
						messages.add(new Message("RECORDS_UPDATED", "Records have been updated successfully",
								String.valueOf(n)));
					} else {
						messages.add(new Message("NO_RECORDS_UPDATED",
								"No records have been updated for above criteria", String.valueOf(0)));
					}
					hotelPostResponse = buildSuccessDeleteResponse(messages);
					return ResponseEntity.status(HttpStatus.OK).body(hotelPostResponse);
				} else {
					messages.add(new Message("NO_RECORDS_UPDATED", "No records have been updated for above criteria",
							String.valueOf(0)));
					hotelPostResponse = buildSuccessDeleteResponse(messages);
					return ResponseEntity.status(HttpStatus.OK).body(hotelPostResponse);
				}
			}
		} catch (Exception exception) {
			messages.add(new Message("SYSTEM_ERROR", "System error occured while processing", exception.getMessage()));
			hotelPostResponse = buildErrorResponse(messages);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hotelPostResponse);
		}
	}

	private HotelUpdateParams createNullParamsIfEmpty(HotelUpdateParams hotelUpdateParams) {
		HotelUpdateParams hotel = new HotelUpdateParams();
		if (hotelUpdateParams.getHotelName().trim().isEmpty()) {
			hotel.setHotelName(null);
		} else {
			hotel.setHotelName(hotelUpdateParams.getHotelName());
		}

		if (hotelUpdateParams.getLocation().trim().isEmpty()) {
			hotel.setLocation(null);
		} else {
			hotel.setLocation(hotelUpdateParams.getLocation());
		}

		if (hotelUpdateParams.getPriceToAddOrSub().trim().isEmpty()) {
			hotel.setPriceToAddOrSub(null);
		} else {
			hotel.setPriceToAddOrSub(hotelUpdateParams.getPriceToAddOrSub());
		}

		if (hotelUpdateParams.getAddKeywords().trim().isEmpty()) {
			hotel.setAddKeywords(null);
		} else {
			hotel.setAddKeywords(hotelUpdateParams.getAddKeywords());
		}
		return hotel;
	}

}