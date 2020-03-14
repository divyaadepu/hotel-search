package com.test.hotelsearch.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.test.hotelsearch.dao.entity.Hotel;
import com.test.hotelsearch.dao.repository.HotelRepository;

@Component
@Transactional
public class HotelDao {

	@Autowired
	private HotelRepository hotelRepository;
	
	public void saveAllHotels(List<Hotel> hotelLists) {
		hotelRepository.saveAll(hotelLists);
	}
	
	public List<Hotel> getAllHotels(){
		return hotelRepository.findAll();
	}
	
	public List<Hotel> getHotels(Specification<Hotel> hotelSearchSpecification) {
		return hotelRepository.findAll(hotelSearchSpecification);
	}
	
	public int deleteHotels(Specification<Hotel> hotelSpecification)  {
		List<Hotel> hotels=hotelRepository.findAll(hotelSpecification);
		if(hotels!=null && !hotels.isEmpty()) {
			hotelRepository.deleteAll(hotels);
			return 1;
		}
		return 0;
	}
	
	public int updateHotels(String hotelName, int price, String location, String keyword, List<Integer> ids) {
		return hotelRepository.updateHotels(hotelName, price, location, keyword, ids);
	}
}
