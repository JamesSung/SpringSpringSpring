package com.james.ctrl;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.james.repo.AirportRepository;
import com.james.repo.BookingRepository;
import com.james.repo.FlightRepository;
import com.james.service.BookingService;
import com.james.service.DataMgmtService;
import com.james.ui.BookingReq;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    //@Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    BookingController subject;
    
//    @MockBean
//    private AirportRepository airportRepository;
//
//    @MockBean
//    private FlightRepository flightRepository;
//
//    @MockBean
//    private BookingRepository bookingRepository;
//
//    @MockBean
//    private BookingService bookingService;
//    
//    @MockBean
//    private DataMgmtService dataMgmtService;
    
    @Before
    public void setup() {  
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    	
    }
    
    @Test
    public void testSearchFlight() throws Exception {
    	BookingReq req = new BookingReq();
    	req.setDeparture("YYZ");
    	req.setArrival("JFK");
    	req.setDepartureDate(new Date(System.currentTimeMillis() + (86400000 * 5)));
    	req.setReturnDate(new Date(System.currentTimeMillis() + 86400000*15));
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	String jsonInString = mapper.writeValueAsString(req);
    	
    	this.mockMvc.perform(get("/searchFlight")
        						.contentType(MediaType.APPLICATION_JSON)
        						.content(jsonInString)).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
       

    }
    
    @Test
    public void testConfirmBooking() throws Exception {
    	BookingReq req = new BookingReq();
    	List<String> fids = new ArrayList<>();
    	fids.add("594dc05958b75446148c04cb");
    	fids.add("594dc05958b75446148c04cd");
    	req.setFlightIds(fids);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	String jsonInString = mapper.writeValueAsString(req);
    	
        this.mockMvc.perform(get("/confirmBooking")
        						.contentType(MediaType.APPLICATION_JSON)
        						.content(jsonInString)).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void testAddBooking() throws Exception {
    	BookingReq req = new BookingReq();
    	req.setBooker("james@gmail.com");
    	req.setPassword("1111");
    	
    	List<String> fids = new ArrayList<>();
    	fids.add("594dc05958b75446148c04cb");
    	fids.add("594dc05958b75446148c04cd");
    	req.setFlightIds(fids);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	String jsonInString = mapper.writeValueAsString(req);
    	
        this.mockMvc.perform(get("/addBooking")
        						.contentType(MediaType.APPLICATION_JSON)
        						.content(jsonInString)).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void testBookingList() throws Exception {
    	BookingReq req = new BookingReq();
    	req.setBooker("james@gmail.com");
    	req.setPassword("1111");
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	String jsonInString = mapper.writeValueAsString(req);
    	
        this.mockMvc.perform(get("/bookingList")
        						.contentType(MediaType.APPLICATION_JSON)
        						.content(jsonInString)).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}