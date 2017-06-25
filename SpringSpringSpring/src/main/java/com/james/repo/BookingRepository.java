package com.james.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.james.bo.Booking;

public interface BookingRepository extends MongoRepository<Booking, String>{
	
	public List<Booking> findByBooker(String booker);
	
	public List<Booking> findByBookerAndPassword(String booker, String password);
}
