package com.superflight1.repository;

import com.superflight1.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirport(String departureAirport);
    List<Flight> findByArrivalAirport(String arrivalAirport);
    List<Flight> findByDepartureAirportAndArrivalAirport(String departureAirport, String arrivalAirport);
    List<Flight> findByDepartureAirportAndDepartureDateTimeAfter(String departureAirport, LocalDateTime date);
    List<Flight> findByArrivalAirportAndDepartureDateTimeAfter(String arrivalAirport, LocalDateTime date);
}

