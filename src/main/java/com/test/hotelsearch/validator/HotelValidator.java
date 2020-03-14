package com.test.hotelsearch.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.test.hotelsearch.model.HotelDeleteRequest;
import com.test.hotelsearch.model.HotelPostRequest;
import com.test.hotelsearch.model.HotelSearchRequest;
import com.test.hotelsearch.model.HotelUpdateParams;
import com.test.hotelsearch.model.HotelUpdateRequest;
import com.test.hotelsearch.model.HotelUpdateWhereParams;
import com.test.hotelsearch.model.Message;

@Component
public class HotelValidator {

	public List<Message> validateHotel(HotelPostRequest hotelPostRequest) {
		List<Message> messages = new ArrayList<Message>();

		if (hotelPostRequest.getHotelName() == null || hotelPostRequest.getHotelName().isEmpty()) {
			messages.add(
					new Message("HOTEL_NAME_INVALID", "Hotel Name is mandatory field", hotelPostRequest.toString()));
		}
		if (hotelPostRequest.getLocation() == null || hotelPostRequest.getLocation().isEmpty()) {
			messages.add(new Message("LOCATION_INVALID", "Location of hotel is mandatory field",
					hotelPostRequest.toString()));
		}

		if (hotelPostRequest.getPriceStartsFrom() != null && !hotelPostRequest.getPriceStartsFrom().isEmpty()) {
			try {
				Integer.valueOf(hotelPostRequest.getPriceStartsFrom());
			} catch (Exception exception) {
				messages.add(new Message("PRICE_INVALID", "Price should be in numeric", hotelPostRequest.toString()));
			}
		}
		return messages;
	}

	public List<Message> validateSearchRequest(HotelSearchRequest hotelSearchRequest) {
		List<Message> messages = new ArrayList<Message>();

		if ((hotelSearchRequest.getHotelId() == null || hotelSearchRequest.getHotelId().trim().isEmpty())
				&& (hotelSearchRequest.getHotelName() == null || hotelSearchRequest.getHotelName().trim().isEmpty())
				&& (hotelSearchRequest.getPriceRangeFrom() == null
				|| hotelSearchRequest.getPriceRangeFrom().trim().isEmpty())
				&& (hotelSearchRequest.getPriceRangeTo() == null || hotelSearchRequest.getPriceRangeTo().trim().isEmpty())
				&& (hotelSearchRequest.getLocation() == null || hotelSearchRequest.getLocation().trim().isEmpty())
				&& (hotelSearchRequest.getKeyword() == null || hotelSearchRequest.getKeyword().trim().isEmpty())) {
			hotelSearchRequest.setFindAll(true);
		} else {

			if (hotelSearchRequest.getHotelId() != null && !hotelSearchRequest.getHotelId().isEmpty()) {
				try {
					Integer.valueOf(hotelSearchRequest.getHotelId());
				} catch (Exception exception) {
					messages.add(new Message("HOTEL_ID_INVALID", "Hotel Id should be in numeric",
							hotelSearchRequest.toString()));
				}
			}

			if (hotelSearchRequest.getPriceRangeFrom() != null && !hotelSearchRequest.getPriceRangeFrom().isEmpty()) {
				try {
					Integer.valueOf(hotelSearchRequest.getPriceRangeFrom());
				} catch (Exception exception) {
					messages.add(new Message("PRICE_FROM_INVALID", "Price Range From should be in numeric",
							hotelSearchRequest.toString()));
				}
			}

			if (hotelSearchRequest.getPriceRangeTo() != null && !hotelSearchRequest.getPriceRangeTo().isEmpty()) {
				try {
					Integer.valueOf(hotelSearchRequest.getPriceRangeTo());
				} catch (Exception exception) {
					messages.add(new Message("PRICE_TO_INVALID", "Price Range To should be in numeric",
							hotelSearchRequest.toString()));
				}
			}
		}
		return messages;
	}

	public List<Message> validateUpdateRequest(HotelUpdateRequest hotelUpdateRequest) {
		List<Message> errorMessage = new ArrayList<Message>();

		if (hotelUpdateRequest.getHotelUpdateParams() == null
				|| hotelUpdateRequest.getHotelUpdateWhereParams() == null) {
			errorMessage.add(new Message("INVALID_UPDATE_REQUEST",
					"Atleast one of the field from updateValues and whenCondition is mandatory",
					hotelUpdateRequest.toString()));
		} else {
			HotelUpdateWhereParams hotelUpdateWhereParams = hotelUpdateRequest.getHotelUpdateWhereParams();
			if ((hotelUpdateWhereParams.getHotelId() == null || hotelUpdateWhereParams.getHotelId().trim().isEmpty())
					&& (hotelUpdateWhereParams.getHotelName() == null
					|| hotelUpdateWhereParams.getHotelName().trim().isEmpty())
					&& (hotelUpdateWhereParams.getPriceGreaterThan() == null
					|| hotelUpdateWhereParams.getPriceGreaterThan().trim().isEmpty())
					&& (hotelUpdateWhereParams.getPriceLessThan() == null
					|| hotelUpdateWhereParams.getPriceLessThan().trim().isEmpty())
					&& (hotelUpdateWhereParams.getLocation() == null
					|| hotelUpdateWhereParams.getLocation().trim().isEmpty())
					&& (hotelUpdateWhereParams.getMatchingKeywords() == null
					|| hotelUpdateWhereParams.getMatchingKeywords().trim().isEmpty())) {
				errorMessage.add(new Message("INVALID_UPDATE_WHERE_CONDITION",
						"Atleast one of the field is mandatory in whereCondition", hotelUpdateWhereParams.toString()));
			} else {

				if (hotelUpdateWhereParams.getHotelId() != null && !hotelUpdateWhereParams.getHotelId().isEmpty()) {
					try {
						Integer.valueOf(hotelUpdateWhereParams.getHotelId());
					} catch (Exception exception) {
						errorMessage.add(new Message("HOTEL_ID_INVALID", "Hotel Id should be in numeric",
								hotelUpdateWhereParams.toString()));
					}
				}

				if (hotelUpdateWhereParams.getPriceGreaterThan() != null
						&& !hotelUpdateWhereParams.getPriceGreaterThan().isEmpty()) {
					try {
						Integer.valueOf(hotelUpdateWhereParams.getPriceGreaterThan());
					} catch (Exception exception) {
						errorMessage.add(new Message("PRICE_GREATER_THAN_INVALID",
								"Price Value From should be in numeric", hotelUpdateWhereParams.toString()));
					}
				}

				if (hotelUpdateWhereParams.getPriceLessThan() != null
						&& !hotelUpdateWhereParams.getPriceLessThan().isEmpty()) {
					try {
						Integer.valueOf(hotelUpdateWhereParams.getPriceLessThan());
					} catch (Exception exception) {
						errorMessage.add(new Message("PRICE_LESS_THAN_INVALID", "Price Value From should be in numeric",
								hotelUpdateWhereParams.toString()));
					}
				}
			}

			HotelUpdateParams hotelUpdateParams = hotelUpdateRequest.getHotelUpdateParams();

			if ((hotelUpdateParams.getHotelName() == null || hotelUpdateParams.getHotelName().trim().isEmpty())
					&& (hotelUpdateParams.getLocation() == null || hotelUpdateParams.getLocation().trim().isEmpty())
					&& (hotelUpdateParams.getPriceToAddOrSub() == null
					|| hotelUpdateParams.getPriceToAddOrSub().trim().isEmpty())
					&& (hotelUpdateParams.getAddKeywords() == null
					|| hotelUpdateParams.getAddKeywords().trim().isEmpty())) {
				errorMessage.add(new Message("INVALID_UPDATE_VALUES_CONDITION",
						"Atleast one of the field is mandatory in updateValues", hotelUpdateParams.toString()));
			} else {
				if (hotelUpdateParams.getPriceToAddOrSub() != null
						&& !hotelUpdateParams.getPriceToAddOrSub().trim().isEmpty()) {
					try {
						Integer.valueOf(hotelUpdateParams.getPriceToAddOrSub());
					} catch (Exception exception) {
						errorMessage.add(new Message("PRICE_INVALID", "Price Value From should be in numeric",
								hotelUpdateParams.toString()));
					}
				}
			}
		}
		return errorMessage;
	}

	public List<Message> validateDeleteHotelRequest(HotelDeleteRequest hotelDeleteRequest) {
		List<Message> errorMessage = new ArrayList<Message>();

		if ((hotelDeleteRequest.getHotelId() == null || hotelDeleteRequest.getHotelId().trim().isEmpty())
				&& (hotelDeleteRequest.getHotelName() == null || hotelDeleteRequest.getHotelName().trim().isEmpty())
				&& (hotelDeleteRequest.getPriceGreaterThan() == null
				|| hotelDeleteRequest.getPriceGreaterThan().trim().isEmpty())
				&& (hotelDeleteRequest.getPriceLessThan() == null || hotelDeleteRequest.getPriceLessThan().isEmpty())
				&& (hotelDeleteRequest.getLocation() == null || hotelDeleteRequest.getLocation().trim().isEmpty())
				&& (hotelDeleteRequest.getNoMatchingKeywords() == null
				|| hotelDeleteRequest.getNoMatchingKeywords().trim().isEmpty())) {
			errorMessage.add(new Message("INVALID_DELETE_REQUEST", "Atleast one of the field is mandatory",
					hotelDeleteRequest.toString()));
		} else {

			if (hotelDeleteRequest.getHotelId() != null && !hotelDeleteRequest.getHotelId().isEmpty()) {
				try {
					Integer.valueOf(hotelDeleteRequest.getHotelId());
				} catch (Exception exception) {
					errorMessage.add(new Message("HOTEL_ID_INVALID", "Hotel Id should be in numeric",
							hotelDeleteRequest.toString()));
				}
			}
			
			if (hotelDeleteRequest.getPriceGreaterThan() != null
					&& !hotelDeleteRequest.getPriceGreaterThan().isEmpty()) {
				try {
					Integer.valueOf(hotelDeleteRequest.getPriceGreaterThan());
				} catch (Exception exception) {
					errorMessage.add(new Message("PRICE_GREATER_THAN_INVALID", "Price Value From should be in numeric",
							hotelDeleteRequest.toString()));
				}
			}

			if (hotelDeleteRequest.getPriceLessThan() != null && !hotelDeleteRequest.getPriceLessThan().isEmpty()) {
				try {
					Integer.valueOf(hotelDeleteRequest.getPriceLessThan());
				} catch (Exception exception) {
					errorMessage.add(new Message("PRICE_LESS_THAN_INVALID", "Price Value From should be in numeric",
							hotelDeleteRequest.toString()));
				}
			}
		}
		return errorMessage;
	}

}
