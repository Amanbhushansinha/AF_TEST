package com.af.flight_operations.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Duration;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.af.flight_operations.entiry.Flight;
import com.af.flight_operations.repository.FlightRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    @Test
    public void testAddFlight() {
        Flight flight = new Flight("TestFlight", "Origin", "ORG", "Destination", "DST", Duration.ofHours(2));
        Mockito.when(flightRepository.save(flight)).thenReturn(flight);

        Flight result = flightService.addFlight(flight);

        assertNotNull(result);
        assertEquals("TestFlight", result.getFlightName());
    }

    @Test
    public void testGetFlightByNo() {
        Flight flight = new Flight("TestFlight", "Origin", "ORG", "Destination", "DST", Duration.ofHours(2));
        Mockito.when(flightRepository.findByFlightName("TestFlight")).thenReturn(Optional.of(flight));

        Flight result = flightService.getFlightByNo("TestFlight");

        assertNotNull(result);
        assertEquals("TestFlight", result.getFlightName());
    }

    @Test
    public void testDeleteFlight() {
        Mockito.doNothing().when(flightRepository).deleteById("TestFlight");

        flightService.deleteFlight("TestFlight");

        Mockito.verify(flightRepository, Mockito.times(1)).deleteById("TestFlight");
    }
}
