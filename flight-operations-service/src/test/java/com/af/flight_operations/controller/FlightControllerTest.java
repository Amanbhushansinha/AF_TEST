
package com.af.flight_operations.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.af.flight_operations.entiry.Flight;
import com.af.flight_operations.exception.FlightNotFoundException;
import com.af.flight_operations.service.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddFlight() throws Exception {
        Flight flight = new Flight("TestFlight", "Origin", "ORG", "Destination", "DST", Duration.ofHours(2));
        Mockito.when(flightService.addFlight(Mockito.any())).thenReturn(flight);

        mockMvc.perform(post("/api/flights")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(flight))) // Use the ObjectMapper here
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightName").value("TestFlight"));
    }

    @Test
    public void testGetFlightByNo() throws Exception {
        Flight flight = new Flight("TestFlight", "Origin", "ORG", "Destination", "DST", Duration.ofHours(2));
        Mockito.when(flightService.getFlightByNo("TestFlight")).thenReturn(flight);

        mockMvc.perform(get("/api/flights/TestFlight"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightName").value("TestFlight"));
    }

    @Test
    public void testDeleteFlight() throws Exception {
        Mockito.doNothing().when(flightService).deleteFlight("TestFlight");

        mockMvc.perform(delete("/api/flights/TestFlight"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testGetFlightsByOriginDestination() throws Exception {
        List<Flight> flights = List.of(
                new Flight("Flight1", "Origin", "ORG", "Destination", "DST", Duration.ofHours(2)),
                new Flight("Flight2", "Origin", "ORG", "Destination", "DST", Duration.ofHours(3))
        );
        Mockito.when(flightService.getFlightsByOriginDestination("Origin", "Destination")).thenReturn(flights);

        mockMvc.perform(get("/api/flights/by-origin-destination")
                .param("origin", "Origin")
                .param("destination", "Destination"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].flightName").value("Flight1"))
                .andExpect(jsonPath("$[1].flightName").value("Flight2"));
    }
    
    //@Test
    public void testAddFlightWithInvalidData() throws Exception {
        // Sending an invalid flight (null or missing flight name)
        Flight invalidFlight = new Flight(null, "Origin", "ORG", "Destination", "DST", Duration.ofHours(2));

        mockMvc.perform(post("/api/flights")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(invalidFlight)))
                .andExpect(status().isBadRequest()); // Assuming the controller throws BadRequest for invalid data
    }

    //@Test
    public void testDeleteNonexistentFlight() throws Exception {
        Mockito.doThrow(new FlightNotFoundException("Flight not found")).when(flightService).deleteFlight("NonexistentFlight");

        mockMvc.perform(delete("/api/flights/NonexistentFlight"))
                .andExpect(status().isNotFound());
    }
 
}
