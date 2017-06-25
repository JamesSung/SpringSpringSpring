package com.james.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flights")
@CompoundIndexes({
    @CompoundIndex(name = "dept_arrv_deptdt_idx", def = "{'departure': 1, 'arrival': 1, 'departureDate': 1}")
})
public class Flight implements Serializable{
	
	private static final long serialVersionUID = 2914695046322584434L;

	@Id
    private String id;
	
	private String planeName;
	
	private String company;
	
	/**
	 * Departure airport code
	 */
	private String departureCode;
	
	/**
	 * Arrival airport code
	 */
	private String arrivalCode;
	
	@Temporal(TemporalType.DATE)
	private Date departureDate;
	
	private BigDecimal price;
	
	@DBRef(lazy = true)
	private List<Booking> bookings;
	
	@ManyToOne(targetEntity = Airport.class, fetch=FetchType.EAGER)
	@JoinColumn(name="departureId", nullable=false)
	private Airport departure;
	
	@ManyToOne(targetEntity = Airport.class, fetch=FetchType.EAGER)
	@JoinColumn(name="arrivalId", nullable=false)
	private Airport arrival;
	
	/**
	 * default constructor for F/W
	 */
	public Flight(){}
	
	public Flight(String id){
		this.id = id;
	}
	
	public Flight(String planeName, String company, String departureCode, String arrivalCode, Date departureDate, BigDecimal price){
		this.planeName = planeName;
		this.company = company;
		this.departureCode = departureCode;
		this.arrivalCode = arrivalCode;
		this.departureDate = departureDate;
		this.price = price;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlaneName() {
		return planeName;
	}

	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDepartureCode() {
		return departureCode;
	}

	public void setDepartureCode(String departureCode) {
		this.departureCode = departureCode;
	}

	public String getArrivalCode() {
		return arrivalCode;
	}

	public void setArrivalCode(String arrivalCode) {
		this.arrivalCode = arrivalCode;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public Airport getDeparture() {
		return departure;
	}

	public void setDeparture(Airport departure) {
		this.departure = departure;
	}

	public Airport getArrival() {
		return arrival;
	}

	public void setArrival(Airport arrival) {
		this.arrival = arrival;
	}

	@Override
	public String toString(){
		return "Flight:[id: "+this.id+", planeName: " 
							+ this.planeName + ", company: " 
							+ this.company +", departure: " 
							+ this.departure +", arrival: " 
							+ this.arrival +", departureDate: " 
							+ this.departureDate +", price: " 
							+ this.price +"]";
	}	
}
