package com.james.repo;

import java.util.List;

import com.james.bo.Airport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

public interface AirportRepository extends MongoRepository<Airport, String>{
	
	public List<Airport> findByCity(String city);
	
	public Airport findByCode(String code);
}
