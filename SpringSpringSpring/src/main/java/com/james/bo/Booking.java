package com.james.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OrderColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.james.util.PWConverter;

@Document(collection = "bookings")
public class Booking implements Serializable {

	private static final long serialVersionUID = -8794503573178217902L;

	@Id
    private String id;
	
	@Temporal(TemporalType.DATE)
	private Date bookDate;
	
	private int numOfTickets = 1;
	
	@Indexed
	private String booker;
	
	@Convert(converter = PWConverter.class)
	private String password;
	
	@Enumerated(EnumType.ORDINAL)
	private BookingStatus status = BookingStatus.PENDING;
	
	@ElementCollection
	@OrderColumn(name = "departureDate")
	private List<Flight> flights;
	
	/**
	 * default constructor for F/W
	 */
	public Booking(){}
	
	public Booking(Date bookDate, int numOfTickets, String booker, String password){
		this.bookDate = bookDate;
		this.numOfTickets = numOfTickets;
		this.booker = booker;
		this.password = password;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public int getNumOfTickets() {
		return numOfTickets;
	}

	public void setNumOfTickets(int numOfTickets) {
		this.numOfTickets = numOfTickets;
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

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlight(List<Flight> flights) {
		this.flights = flights;
	}
	
	public void addFlight(Flight flight){
		if(this.flights == null)
			this.flights = new ArrayList<>();
		
		this.flights.add(flight);
	}
	
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	@Override
	public String toString(){
		return "Booking:[id: "+this.id+", bookDate: " 
							+ this.bookDate + ", numOfTickets: " 
							+ this.numOfTickets +", booker: " 
							+ this.booker +", status: " 
							+ this.status +", flights: [" 
							+ this.flights +"]]";
	}	
}
