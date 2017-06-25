package com.james.service.impl;

import java.util.Date;
import java.util.List;

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
import com.james.service.BookingService;

@Component
public class BookingServiceImpl implements BookingService{

    @Autowired 
    AirportRepository airportRepo;
    
    @Autowired
    FlightRepository flightRepo;
    
    @Autowired
    BookingRepository bookingRepo;
    
    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);
    
	@Transactional
	public List<Airport> findAirportsByCity(String city) {
		
		List<Airport> ars = airportRepo.findByCity(city);
		
		if(logger.isDebugEnabled()){
			ars.forEach(ar -> logger.debug("found by city {}",ar));
		}
		
		return ars;
	}
	
	@Override
	@Transactional
	public List<Airport> findAllAirports() {
		
		List<Airport> ars = airportRepo.findAll();
		
		if(logger.isDebugEnabled()){
			ars.forEach(ar -> logger.debug("found all {}",ar));
		}
		
		return ars;
	}

	@Transactional
	public List<Flight> findFlightsByAirport(String departureCode, String arrivalCode) {

		List<Flight> fls = flightRepo.findByDepartureCodeAndArrivalCode(departureCode, arrivalCode);
		
		if(logger.isDebugEnabled()){
			fls.forEach(fl -> logger.debug("found by airport {}",fl));
		}
		
		return fls;
	}
	
	@Override
	@Transactional
	public List<Flight> findAllFlights() {
		List<Flight> fls = flightRepo.findAll();

		if(logger.isDebugEnabled()){
			fls.forEach(fl -> logger.debug("found by airport {}",fl));
		}
		
		return fls;
	}

	@Override
	public List<Flight> findFlightsByAirportAndDepartureDate(String departureCode, String arrivalCode, Date from, Date to) {
		
		List<Flight> fls =  flightRepo.findByDepartureCodeAndArrivalCodeAndDepartureDateBetween(departureCode, arrivalCode, from, to);
		
		if(logger.isDebugEnabled()){
			fls.forEach(fl -> logger.debug("found by Departure and Arrival and DepartureDate {}",fl));
		}
		
		return fls;
	}
	
	@Transactional
	public List<Booking> findBookingsByBooker(String booker) {
		
		List<Booking> bks = bookingRepo.findByBooker(booker);

		if(logger.isDebugEnabled()){
			bks.forEach(bk -> logger.debug("found by booker {}",bk));
		}
		
		return bks;
	}
	
	@Transactional
	public Flight findFlightById(String id) {
		return flightRepo.findOne(id);
	}
	
	@Transactional
	public List<Booking> findBookingsByBookerAndPassword(String booker, String password) {
		List<Booking> bks = bookingRepo.findByBookerAndPassword(booker, password);
		
		if(logger.isDebugEnabled()){
			bks.forEach(bk -> logger.debug("found by booker and password {}",bk));
		}
		
		return bks;
		
	}

}
