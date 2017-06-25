package com.james.ui;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

public class BookingReq {
    
	private String departure;
	
	private String arrival;
	
	private Date departureDate;
	
	private Date returnDate;
	
	private String booker;
	
	private String password;
	
	private List<String> flightIds;

	public String getDeparture() {
		if(StringUtils.isEmpty(departure)) 
			return "YYZ";
		else
			return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		if(StringUtils.isEmpty(arrival)) 
			return "JFK";
		else
			return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public Date getDepartureDate() {
		if(StringUtils.isEmpty(departureDate)) 
			return new Date(System.currentTimeMillis());
		else
			return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getReturnDate() {
		if(StringUtils.isEmpty(returnDate)) 
			return new Date(System.currentTimeMillis());
		else
			return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
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

	public List<String> getFlightIds() {
		return flightIds;
	}

	public void setFlightIds(List<String> flightIds) {
		this.flightIds = flightIds;
	}

	@Override
	public String toString(){
			return "BookingRes:[booker: "+ this.booker +", password: " 
							+ this.password +", departure: " 
							+ this.departure +", arrival: " 
							+ this.arrival +", departureDate: " 
							+ this.departureDate +", returnDate: " 
							+ this.returnDate +", flightIds: " 
							+ this.flightIds  +"]";
	}

}
