package com.af.flight_operations.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.af.flight_operations.entiry.Flight;
import com.af.flight_operations.exception.FlightNotFoundException;
import com.af.flight_operations.exception.InvalidFlightDataException;
import com.af.flight_operations.service.FlightService;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;
    // SLF4J Logger
    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // Add Flight
    @PostMapping
    public Flight addFlight(@RequestBody Flight flight) {
        if (flight == null || flight.getFlightName().isEmpty()) {
            throw new InvalidFlightDataException("Invalid flight data provided.");
        }
        logger.info("Received request to Add the flights", flight);
        return flightService.addFlight(flight);
    }

    // Get Flight by Flight No
    @GetMapping("/{flightName}")
    public Flight getFlightByNo(@PathVariable String flightName) {
        logger.info("Received request to fetch the flights", flightName);
        Flight flight = flightService.getFlightByNo(flightName);
        if (flight == null) {
            throw new FlightNotFoundException("Flight with name " + flightName + " not found.");
        }
        return flight;
    }


    // Get Flights by Origin and Destination
    @GetMapping("/by-origin-destination")
    public List<Flight> getFlightsByOriginDestination(@RequestParam String origin, @RequestParam String destination) {
    	logger.info("Received request to fetch flights from {} to {}", origin, destination);
    	return flightService.getFlightsByOriginDestination(origin, destination);
    }

   
    // Update Flight
    @PutMapping("/{flightName}")
    public Flight updateFlight(@PathVariable String flightName, @RequestBody Flight updatedFlight) {
    	logger.info("Received request to Update flights",updatedFlight);
        return flightService.updateFlight(flightName, updatedFlight);
    }

 // Delete Flight
    @DeleteMapping("/{flightName}")
    public void deleteFlight(@PathVariable String flightName) {
    	logger.info("Received request to Delete the flights",flightName);
        flightService.deleteFlight(flightName);
    }
    
 // Endpoint to get all saved flights
    @GetMapping("/getAllFlights")
    public List<Flight> getAllFlights() {
    	logger.info("Received request to get all the flights");
        return flightService.getAllFlights();  // Call service method to fetch all flights
    }
}