package com.james.ctrl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.util.StringUtils;

import com.james.bo.Airport;
import com.james.bo.Booking;
import com.james.bo.Flight;
import com.james.service.BookingService;
import com.james.service.DataMgmtService;
import com.james.ui.BookingReq;
import com.james.ui.BookingRes;

@RestController
@EnableAutoConfiguration
@EnableWebMvc
public class BookingController {

    @Autowired
    BookingService bookingService;
    
    @Autowired
    DataMgmtService dataMgmtService;
    
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
    
    @RequestMapping("/searchFlight")
    public @ResponseBody BookingRes searchFlight(@RequestBody BookingReq req) {
    	
    	Date curr = new Date(System.currentTimeMillis());
    	
    	if(req.getReturnDate().before(curr)){
        	Calendar cal = Calendar.getInstance();
        	cal.setTime(req.getDepartureDate());
        	cal.add(Calendar.DAY_OF_MONTH, 10);
        	req.setReturnDate(cal.getTime());
    	}
    	
    	//System.out.println("Request Info: " + req);
    	
    	if(logger.isDebugEnabled()){
    		logger.debug("Request Info: " + req);
    	}  
    	
    	List<Flight> fls = bookingService.findFlightsByAirportAndDepartureDate(req.getDeparture(), req.getArrival(), req.getDepartureDate(), req.getReturnDate());
    	//List<Flight> fls =   bookingService.findFlightsByAirportAndDepartureDate("YYZ", "JFK",new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()+864000000));
    	//fls.forEach(fl ->{System.out.println(fl);});
		//for the select options
    	List<Airport> ars = bookingService.findAllAirports();
        
        BookingRes bkRes = new BookingRes();
        bkRes.setArrival(req.getArrival());
        bkRes.setDeparture(req.getDeparture());
        bkRes.setDepartureDate(req.getDepartureDate());
        bkRes.setReturnDate(req.getReturnDate());
        bkRes.setAirporList(ars);
        bkRes.setFlightList(fls);
        
        return bkRes;
    }

    @RequestMapping(value = "/confirmBooking", method = {RequestMethod.GET})
    public @ResponseBody BookingRes confirmBooking(@RequestBody BookingReq req) {
    	
    	List<Flight> fls = new ArrayList<Flight>();
    	List<String> fids = req.getFlightIds();
    	if(fids != null){
    		fids.forEach( id -> {fls.add(bookingService.findFlightById(id));});
    	}

        BookingRes bkRes = new BookingRes();
        bkRes.setFlightList(fls);
        
        return bkRes;
    }

    @RequestMapping(value = "/addBooking", method = RequestMethod.GET)
    public @ResponseBody BookingRes addBooking(@RequestBody BookingReq req) throws Exception{
    	    	
    	List<Flight> fls = new ArrayList<Flight>();
    	List<String> fids = req.getFlightIds();
    	if(fids != null){
    		fids.forEach( id -> {fls.add(bookingService.findFlightById(id));});
    	}
    	
    	Booking booking = new Booking();
    	booking.setBooker(req.getBooker());
    	booking.setPassword(req.getPassword());
    	booking.setBookDate(new Date(System.currentTimeMillis()));
    	booking.setFlights(fls);
    	
    	if(logger.isDebugEnabled()){
    		logger.debug("BookingController.addBooking: " + booking);
    	}
    	
    	dataMgmtService.createBooking(booking);
    	
    	List<Booking> bks = bookingService.findBookingsByBookerAndPassword(req.getBooker(), req.getPassword());
    	if(logger.isDebugEnabled()){
    		logger.debug("bookingList: " + bks);
    	}    	
    	
        BookingRes bkRes = new BookingRes();
        bkRes.setBooker(req.getBooker());
        bkRes.setPassword(req.getPassword());
        bkRes.setBookingList(bks);
        
        return bkRes;

    }
    
    @RequestMapping(value = "/bookingList", method = {RequestMethod.GET})
    public @ResponseBody BookingRes bookingList(@RequestBody BookingReq req) {

        BookingRes bkRes = new BookingRes();

        if(StringUtils.isEmpty(req.getBooker()) || StringUtils.isEmpty(req.getPassword()))  return bkRes;
    		
    	List<Booking> bks = bookingService.findBookingsByBookerAndPassword(req.getBooker(), req.getPassword());
    	if(logger.isDebugEnabled()){
    		logger.debug("bookingList: " + bks);
    	}    	
 
        bkRes.setBooker(req.getBooker());
        bkRes.setPassword(req.getPassword());
        bkRes.setBookingList(bks);
        
        return bkRes;

    }


}
