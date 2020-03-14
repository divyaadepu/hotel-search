package com.test.hotelsearch.dao.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.hotelsearch.dao.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, BigInteger>, JpaSpecificationExecutor<Hotel> {
	
	@Modifying
	@Query("update Hotel h set h.hotelName=COALESCE(:hotelName,h.hotelName), h.price=CASE WHEN :price IS NOT NULL THEN (h.price + (:price)) ELSE h.price END ,h.location=COALESCE(:location,h.location),h.specialities=CASE WHEN :keyword IS NOT NULL THEN concat(h.specialities,',',(:keyword)) ELSE h.specialities END  where h.hotelId IN :ids ")
	int updateHotels(String hotelName, int price, String location, String keyword, List<Integer> ids);

}
