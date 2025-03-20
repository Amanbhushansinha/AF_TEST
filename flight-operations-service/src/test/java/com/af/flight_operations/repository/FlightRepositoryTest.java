
package com.af.flight_operations.repository;

import static org.mockito.Mockito.*;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.af.flight_operations.entiry.Flight;

@DataJpaTest
public class FlightRepositoryTest {

	@Autowired
	private FlightRepository flightRepository;

	@Test
	public void testFindByFlightName() {
		Flight flight = new Flight("TestFlight", "Origin", "ORG", "Destination", "DST", Duration.ofHours(2));
		flightRepository.save(flight);

		Optional<Flight> result = flightRepository.findByFlightName("TestFlight");

		assertTrue(result.isPresent());
		assertEquals("TestFlight", result.get().getFlightName());
	}

	@Test
	public void testFindAll() {
		Flight flight1 = new Flight("TestFlight1", "Origin", "ORG1", "Destination", "DST1", Duration.ofHours(2));
		Flight flight2 = new Flight("TestFlight2", "Origin", "ORG2", "Destination", "DST2", Duration.ofHours(3));
		flightRepository.save(flight1);
		flightRepository.save(flight2);

		List<Flight> flights = flightRepository.findAll();

		assertEquals(2, flights.size());
	}
}
