package com.james.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.james.bo.Airport;
import com.james.bo.Booking;
import com.james.bo.Flight;
import com.james.repo.AirportRepository;
import com.james.repo.BookingRepository;
import com.james.repo.FlightRepository;
import com.james.service.DataMgmtService;

@Component
public class DataMgmtServiceImpl implements DataMgmtService {

    @Autowired 
    AirportRepository airportRepo;
    
    @Autowired
    FlightRepository flightRepo;
    
    @Autowired
    BookingRepository bookingRepo;
    
    private static final Logger logger = LoggerFactory.getLogger(DataMgmtServiceImpl.class);

	@Transactional
	public void createAirport(Airport airport) {
		if(logger.isDebugEnabled()){
			logger.debug("create airport:{}" + airport);
		}
		
		airportRepo.insert(airport);
		
	}
    
	@Transactional
	public void createFlight(Flight flight){
		if(logger.isDebugEnabled()){
			logger.debug("create flight:{}" + flight);
		}
		
		flightRepo.insert(flight);
	}
	
	@Transactional
	public void createBooking(Booking booking){
		if(logger.isDebugEnabled()){
			logger.debug("insert booking:{}" + booking);
		}
		
		bookingRepo.insert(booking);
	}
}
