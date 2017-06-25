package com.james;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.james.bo.Airport;
import com.james.bo.Booking;
import com.james.bo.Flight;
import com.james.repo.AirportRepository;
import com.james.repo.BookingRepository;
import com.james.repo.FlightRepository;
import com.james.service.BookingService;

@SpringBootApplication
public class MongoApplication implements CommandLineRunner {
	
	@Autowired
	private AirportRepository airportRepo;

	@Autowired
	private FlightRepository flightRepo;

	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private BookingService bookingService;

	@Override
	public void run(String... args) throws Exception {
		
		if(true) return;
		
		runAirport(args);
		runFlight(args);
		runBooking(args);

	}
	
	private void runAirport(String... args){
		
		this.airportRepo.deleteAll();

		this.airportRepo.save(new Airport("YYZ", "Pearson", "Toronto", "Canada"));
		this.airportRepo.save(new Airport("JFK", "John F.Kennedy", "New York", "USA"));
		this.airportRepo.save(new Airport("LGA", "LaGuardia", "New York", "USA"));

		System.out.println("Airports found with findAll():");
		System.out.println("-------------------------------");
		for (Airport airport : this.airportRepo.findAll()) {
			System.out.println(airport);
		}
		System.out.println();

		System.out.println("Airports found with findByCity('New York'):");
		System.out.println("--------------------------------");
		for (Airport airport : this.airportRepo.findByCity("New York")) {
			System.out.println(airport);
		}		
	}

	private void runFlight(String... args){
		
		this.flightRepo.deleteAll();
		
		Flight f1 = new Flight("Boing 747", "Air Canada", "YYZ", "JFK", new Date(System.currentTimeMillis()+864000000), BigDecimal.valueOf(300));
		Flight f2 = new Flight("Boing 747", "Air Canada", "JFK", "YYZ", new Date(System.currentTimeMillis()+864000000), BigDecimal.valueOf(300));
		Flight f3 = new Flight("Boing 777", "Korean Air", "YYZ", "JFK", new Date(System.currentTimeMillis()+864000000), BigDecimal.valueOf(500));
		Flight f4 = new Flight("Boing 777", "Korean Air", "YYZ", "LGA", new Date(System.currentTimeMillis()+864000000), BigDecimal.valueOf(500));
		
		f1.setDeparture(this.airportRepo.findByCode(f1.getDepartureCode()));
		f1.setArrival(this.airportRepo.findByCode(f1.getArrivalCode()));
		
		f2.setDeparture(this.airportRepo.findByCode(f2.getDepartureCode()));
		f2.setArrival(this.airportRepo.findByCode(f2.getArrivalCode()));

		f3.setDeparture(this.airportRepo.findByCode(f3.getDepartureCode()));
		f3.setArrival(this.airportRepo.findByCode(f3.getArrivalCode()));

		f4.setDeparture(this.airportRepo.findByCode(f4.getDepartureCode()));
		f4.setArrival(this.airportRepo.findByCode(f4.getArrivalCode()));
		
		this.flightRepo.save(f1);
		this.flightRepo.save(f2);
		this.flightRepo.save(f3);
		this.flightRepo.save(f4);

		System.out.println("Flights found with findAll():");
		System.out.println("-------------------------------");
		for (Flight flight : this.flightRepo.findAll()) {
			System.out.println(flight);
		}
		System.out.println();

		System.out.println("Flights found with findFlightsByAirport('YYZ', 'JFK'):");
		System.out.println("--------------------------------");
		for (Flight flight : this.bookingService.findFlightsByAirport("YYZ", "JFK")) {
			System.out.println(flight);
		}	
		
		System.out.println("Flights found with findFlightsByAirportAndDepartureDate('YYZ', 'JFK','from','to'):");
		System.out.println("--------------------------------");
		for (Flight flight : this.bookingService.findFlightsByAirportAndDepartureDate("YYZ", "JFK",new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()+864000000))) {
			System.out.println(flight);
		}		

	}
	
	private void runBooking(String... args){
		
		this.bookingRepo.deleteAll();
		
		Booking bk = null;
		for (Flight flight : this.flightRepo.findByDepartureCodeAndArrivalCode("YYZ", "JFK")) {
			//System.out.println(flight);
			
			bk = new Booking(new Date(System.currentTimeMillis()), 2, "jamessung@g.com", "1111");
			bk.addFlight(flight);
			this.bookingRepo.save(bk);
		}

		System.out.println("Bookings found with findAll():");
		System.out.println("-------------------------------");
		for (Booking booking : this.bookingRepo.findAll()) {
			System.out.println(booking);
		}
		System.out.println();

		System.out.println("Bookings found with findBookingsByBooker('jamessung@g.com'):");
		System.out.println("--------------------------------");
		for (Booking booking : this.bookingService.findBookingsByBooker("jamessung@g.com")) {
			System.out.println(booking);
		}		
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MongoApplication.class, args);
	}
}
