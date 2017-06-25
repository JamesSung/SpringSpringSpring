package com.james.bo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "airports")
public class Airport implements Serializable{

	private static final long serialVersionUID = 8747842473915887937L;

	@Id
    private String id;
	
	/**
	 * Three-letter code designating many airports around the world, 
	 * defined by the International Air Transport Association
	 */
	@Column(unique = true)
	private String code;

	private String name;
	
	private String city;
	
	private String country;
	
	@OneToMany(targetEntity = Flight.class, mappedBy = "departure", cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
	@JoinColumn(name="departures")
	private List<Flight> departures;

	@OneToMany(targetEntity = Flight.class, mappedBy = "arrival", cascade = CascadeType.REMOVE,fetch=FetchType.LAZY)
	@JoinColumn(name="arrivals")
	private List<Flight> arrivals;

	/**
	 * default constructor for F/W
	 */
	public Airport(){}
	
	public Airport(String code, String name, String city, String country){
		this.code = code;
		this.name = name;
		this.city = city;
		this.country = country;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString(){
		return "Airport:[id: "+this.id+", code: " 
							+ this.code + ", name: " 
							+ this.name +", city: " 
							+ this.city +", country: " 
							+ this.country +"]";
	}
}
