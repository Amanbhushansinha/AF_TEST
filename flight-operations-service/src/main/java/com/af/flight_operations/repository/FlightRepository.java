package com.af.flight_operations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.af.flight_operations.entiry.Flight;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
	
	
	
	
    Optional<Flight> findByFlightName(String flightName);

    List<Flight> findByFlightNameAndOriginAndDestinationOrderByDurationAsc(String flightName, String origin, String destination);
    
    // Fetch all flights from the database
    List<Flight> findAll();
    
    public default List<Flight> getFlightsByOriginDestination(String origin, String destination, List <Flight> flList) {
    	System.out.println(origin);
    	System.out.println(destination);
    
        return flList.stream()
                .filter(f -> f.getOrigin().equals(origin) && f.getDestination().equals(destination))
                .sorted(Comparator.comparing(Flight::getDuration))
                .collect(Collectors.toList());
    }
    
    
}