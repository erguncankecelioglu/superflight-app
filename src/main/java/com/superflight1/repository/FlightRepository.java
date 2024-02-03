package com.superflight1.repository;

import com.superflight1.model.Airport;
import com.superflight1.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTimeBetween(
            Airport departureAirport,
            Airport arrivalAirport,
            LocalDateTime startOfDay,
            LocalDateTime endOfDay
    );

    Flight findByDepartureAirportAndArrivalAirportAndDepartureDateTime(Airport departureAirport, Airport arrivalAirport, LocalDateTime departureDateTime);
}

