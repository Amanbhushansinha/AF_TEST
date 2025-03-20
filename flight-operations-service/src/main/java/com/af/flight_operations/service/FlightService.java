package com.af.flight_operations.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.af.flight_operations.controller.FlightController;
import com.af.flight_operations.entiry.Flight;
import com.af.flight_operations.repository.FlightRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
 // SLF4J Logger
    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // Add Flight
    public Flight addFlight(Flight flight) {
    	logger.debug("Adding flights:");
        return flightRepository.save(flight);
    }

    // Get Flight by Flight No
    public Flight getFlightByNo(String flightName) {
    	logger.debug("Fetching flights by number ", flightName);
        return flightRepository.findByFlightName(flightName)
                .orElseThrow(() -> new NoSuchElementException("Flight not found"));
    }

    // Get Flights by Origin and Destination
    public List<Flight> getFlightsByOriginDestination(String origin, String destination) {
    	List<Flight> flist = flightRepository.findAll();
    	logger.debug("Fetching flights from {} to {}", origin, destination);
        return flightRepository.getFlightsByOriginDestination(origin, destination,flist);
    }

    // Get Flights with Flight No, Origin, and Destination
    public List<Flight> getFlightWithDetails(String flightName, String origin, String destination) {
    	logger.debug("Fetching flights from {} to {}", origin, destination);
        return flightRepository.findByFlightNameAndOriginAndDestinationOrderByDurationAsc(flightName, origin, destination);
    }

    // Update Flight
    public Flight updateFlight(String flightName, Flight updatedFlight) {
        Flight existingFlight = getFlightByNo(flightName);
        existingFlight.setFlightName(updatedFlight.getFlightName());
        existingFlight.setOrigin(updatedFlight.getOrigin());
        existingFlight.setOriginCode(updatedFlight.getOriginCode());
        existingFlight.setDestination(updatedFlight.getDestination());
        existingFlight.setDestinationCode(updatedFlight.getDestinationCode());
        existingFlight.setDuration(updatedFlight.getDuration());
        return flightRepository.save(existingFlight);
    }

    // Delete Flight
    public void deleteFlight(String flightName) {
        flightRepository.deleteById(flightName);
    }
    
    // Method to get all flights
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();  // Fetch all flights
    }
    
  
	public List<Flight> getFlightsByOriginAndDestination(String origin, String destination) {
		// TODO Auto-generated method stub
		return null;
	}
}