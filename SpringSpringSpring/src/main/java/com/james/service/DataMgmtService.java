package com.james.service;

import com.james.bo.Airport;
import com.james.bo.Booking;
import com.james.bo.Flight;

public interface DataMgmtService {
    
	/**
	 * Insert a airport data into the airport table
	 * 
	 * @param airport object of Airport
	 */
	public void createAirport(Airport airport);
	
	/**
	 * Insert a flight information into the flight table
	 * 
	 * @param flight object of Airport
	 */
	public void createFlight(Flight flight);
	
	/**
	 * Insert a booking information into the booking table
	 * 
	 * @param booking object of Booking
	 */
	public void createBooking(Booking flight);

}
