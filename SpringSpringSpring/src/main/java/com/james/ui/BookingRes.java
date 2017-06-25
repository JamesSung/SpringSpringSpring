package com.james.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.james.bo.Airport;
import com.james.bo.Booking;
import com.james.bo.Flight;

public class BookingRes {
	
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	private String departure;
	
	private String arrival;
	
	private String departureDate;
	
	private String returnDate;
	
	private String booker;
	
	private String password;
	
	private List<Airport> airporList;
	
	private List<Flight> flightList;
	
	private List<Booking> bookingList;

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = dateFormat.format(departureDate);
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = dateFormat.format(returnDate);
	}

	public String getBooker() {
		return booker;
	}

	public void setBooker(String booker) {
		this.booker = booker;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Airport> getAirporList() {
		return airporList;
	}

	public void setAirporList(List<Airport> airporList) {
		this.airporList = airporList;
	}

	public List<Flight> getFlightList() {
		return flightList;
	}

	public void setFlightList(List<Flight> flightList) {
		this.flightList = flightList;
	}

	public List<Booking> getBookingList() {
		return bookingList;
	}

	public void setBookingList(List<Booking> bookingList) {
		this.bookingList = bookingList;
	}
	
	@Override
	public String toString(){
			return "BookingRes:[booker: "+ this.booker +", password: " 
							+ this.password +", departure: " 
							+ this.departure +", arrival: " 
							+ this.arrival +", departureDate: " 
							+ this.departureDate +", returnDate: " 
							+ this.returnDate +", airporList: " 
							+ this.airporList +", flightList: " 
							+ this.flightList +", bookingList: " 
							+ this.bookingList +"]";
	}
	
}
