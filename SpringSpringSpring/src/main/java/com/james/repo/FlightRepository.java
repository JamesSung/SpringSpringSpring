package com.james.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.james.bo.Flight;

public interface FlightRepository extends MongoRepository<Flight, String> {
	
	public List<Flight> findByDepartureCodeAndArrivalCode(String departureCode, String arrivalCode);
	
	public List<Flight> findByDepartureCodeAndArrivalCodeAndDepartureDateBetween(String departureCode, String arrivalCode, Date from, Date to);
}
