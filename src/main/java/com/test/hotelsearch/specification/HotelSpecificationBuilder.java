package com.test.hotelsearch.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.test.hotelsearch.constants.HotelConstants;
import com.test.hotelsearch.model.HotelDeleteRequest;
import com.test.hotelsearch.model.HotelSearchRequest;
import com.test.hotelsearch.model.HotelUpdateWhereParams;

public class HotelSpecificationBuilder<T> {

	public Specification<T> buildHotelSearchSpecification(HotelSearchRequest hotelSearchRequest)
	{
		List<SearchCriteria> searchCriteriaList=new ArrayList<>();
		
		if(hotelSearchRequest.getHotelId()!=null && !hotelSearchRequest.getHotelId().isEmpty()) {
			searchCriteriaList.add(new SearchCriteria("hotelId",HotelConstants.EQUAL, hotelSearchRequest.getHotelId()));
		}
		
		if(hotelSearchRequest.getHotelName()!=null && !hotelSearchRequest.getHotelName().isEmpty()) {
			searchCriteriaList.add(new SearchCriteria("hotelName",HotelConstants.LIKE, hotelSearchRequest.getHotelName()));
		}
		
		if(hotelSearchRequest.getLocation()!=null && !hotelSearchRequest.getLocation().isEmpty()) {
			searchCriteriaList.add(new SearchCriteria("location",HotelConstants.LIKE, hotelSearchRequest.getLocation()));
		}
		
		if(hotelSearchRequest.getPriceRangeFrom()!=null && !hotelSearchRequest.getPriceRangeFrom().isEmpty()) {
			searchCriteriaList.add(new SearchCriteria("price",HotelConstants.GREATER_THAN_EQUAL, hotelSearchRequest.getPriceRangeFrom()));
		}
		
		if(hotelSearchRequest.getPriceRangeTo()!=null && !hotelSearchRequest.getPriceRangeTo().isEmpty()) {
			searchCriteriaList.add(new SearchCriteria("price",HotelConstants.LESS_THAN_EQUAL, hotelSearchRequest.getPriceRangeTo()));
		}
		
		if(hotelSearchRequest.getKeyword()!=null && !hotelSearchRequest.getKeyword().isEmpty()) {
			searchCriteriaList.add(new SearchCriteria("specialities",HotelConstants.LIKE, hotelSearchRequest.getKeyword()));
		}
		
		List<HotelSpecification<T>> specificationList=new ArrayList<HotelSpecification<T>>();
		for(SearchCriteria searchCriteria:searchCriteriaList) {
			specificationList.add(new HotelSpecification<>(searchCriteria));
		}
		Specification<T> result = specificationList.get(0);
		for (int i = 1; i < specificationList.size(); i++) {
			result = Specification.where(result).and(specificationList.get(i));
		}
		return result;
	}
	
	public Specification<T> deleteRecordsCriteria(HotelDeleteRequest hotelDeleteRequest){
		List<SearchCriteria> searchCriteriaList=new ArrayList<>();
		
		if(hotelDeleteRequest.getHotelId()!=null && !hotelDeleteRequest.getHotelId().isEmpty()) {
			searchCriteriaList.add(new SearchCriteria("hotelId",HotelConstants.EQUAL, hotelDeleteRequest.getHotelId()));
		}
		
		if(hotelDeleteRequest.getHotelName()!=null && !hotelDeleteRequest.getHotelName().isEmpty()) {
			searchCriteriaList.add(new SearchCriteria("hotelName",HotelConstants.LIKE, hotelDeleteRequest.getHotelName()));
		}
		
		if(hotelDeleteRequest.getLocation()!=null && !hotelDeleteRequest.getLocation().isEmpty()) {
			searchCriteriaList.add(new SearchCriteria("location",HotelConstants.LIKE, hotelDeleteRequest.getLocation()));
		}
		
		if(hotelDeleteRequest.getPriceLessThan()!=null && !hotelDeleteRequest.getPriceLessThan().isEmpty()) {
			searchCriteriaList.add(new SearchCriteria("price",HotelConstants.LESS_THAN, hotelDeleteRequest.getPriceLessThan()));
		}
		
		if(hotelDeleteRequest.getPriceGreaterThan()!=null && !hotelDeleteRequest.getPriceGreaterThan().isEmpty()) {
			searchCriteriaList.add(new SearchCriteria("price",HotelConstants.GREATER_THAN, hotelDeleteRequest.getPriceGreaterThan()));
		}
		
		if(hotelDeleteRequest.getNoMatchingKeywords()!=null && !hotelDeleteRequest.getNoMatchingKeywords().isEmpty()) {
			searchCriteriaList.add(new SearchCriteria("specialities",HotelConstants.NOT_LIKE, hotelDeleteRequest.getNoMatchingKeywords()));
		}
	
		List<HotelSpecification<T>> specificationList=new ArrayList<HotelSpecification<T>>();
		for(SearchCriteria searchCriteria:searchCriteriaList) {
			specificationList.add(new HotelSpecification<>(searchCriteria));
		}
		Specification<T> result = specificationList.get(0);
		for (int i = 1; i < specificationList.size(); i++) {
			result = Specification.where(result).and(specificationList.get(i));
		}
		return result;
	}
	
	public Specification<T> updateRecordsCriteria(HotelUpdateWhereParams hotelUpdateWhereParams){
		List<SearchCriteria> searchCriteriaList=new ArrayList<>();
		
		if(hotelUpdateWhereParams.getHotelId()!=null && !hotelUpdateWhereParams.getHotelId().isEmpty()) {
			searchCriteriaList.add(new SearchCriteria("hotelId",HotelConstants.EQUAL, hotelUpdateWhereParams.getHotelId()));
		}
		
		if(hotelUpdateWhereParams.getHotelName()!=null && !hotelUpdateWhereParams.getHotelName().isEmpty()) {
			searchCriteriaList.add(new SearchCriteria("hotelName",HotelConstants.LIKE, hotelUpdateWhereParams.getHotelName()));
		}
		
		if(hotelUpdateWhereParams.getLocation()!=null && !hotelUpdateWhereParams.getLocation().isEmpty()) {
			searchCriteriaList.add(new SearchCriteria("location",HotelConstants.LIKE, hotelUpdateWhereParams.getLocation()));
		}
		
		if(hotelUpdateWhereParams.getPriceLessThan()!=null && !hotelUpdateWhereParams.getPriceLessThan().isEmpty()) {
			searchCriteriaList.add(new SearchCriteria("price",HotelConstants.LESS_THAN, hotelUpdateWhereParams.getPriceLessThan()));
		}
		
		if(hotelUpdateWhereParams.getPriceGreaterThan()!=null && !hotelUpdateWhereParams.getPriceGreaterThan().isEmpty()) {
			searchCriteriaList.add(new SearchCriteria("price",HotelConstants.GREATER_THAN, hotelUpdateWhereParams.getPriceGreaterThan()));
		}
		
		if(hotelUpdateWhereParams.getMatchingKeywords()!=null && !hotelUpdateWhereParams.getMatchingKeywords().isEmpty()) {
			searchCriteriaList.add(new SearchCriteria("specialities",HotelConstants.LIKE, hotelUpdateWhereParams.getMatchingKeywords()));
		}
	
		List<HotelSpecification<T>> specificationList=new ArrayList<HotelSpecification<T>>();
		for(SearchCriteria searchCriteria:searchCriteriaList) {
			specificationList.add(new HotelSpecification<>(searchCriteria));
		}
		Specification<T> result = specificationList.get(0);
		for (int i = 1; i < specificationList.size(); i++) {
			result = Specification.where(result).and(specificationList.get(i));
		}
		return result;
	}
}
