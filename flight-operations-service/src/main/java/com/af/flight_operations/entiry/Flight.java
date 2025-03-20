package com.af.flight_operations.entiry;


import java.time.Duration;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Flight {

    @Id
    private String flightName;
    private String origin;
    private String originCode;
    private String destination;
    private String destinationCode;
    private Duration duration;
    
    // No-argument constructor
    public Flight() {
    }


    // Constructor, Getters, and Setters
    public Flight(String flightName, String origin, String originCode, String destination, String destinationCode, Duration duration) {
        this.flightName = flightName;
        this.origin = origin;
        this.originCode = originCode;
        this.destination = destination;
        this.destinationCode = destinationCode;
        this.duration = duration;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}