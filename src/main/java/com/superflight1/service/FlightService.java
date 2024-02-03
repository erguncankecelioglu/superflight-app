package com.superflight1.service;

import com.superflight1.model.Airport;
import com.superflight1.model.Flight;
import com.superflight1.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class FlightService {

    private FlightRepository flightRepository;

    public List<Flight> ListOneWayFlights(Airport departureAirport, Airport arrivalAirport, LocalDate date){
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        return flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTimeBetween(
                departureAirport,arrivalAirport,startOfDay,endOfDay
        );

    }

    public List<List<Flight>> ListRoundFlights(Airport departureAirport, Airport arrivalAirport,
                                               LocalDate departureDate, LocalDate returnDate) {
        LocalDateTime departureStartOfDay = departureDate.atStartOfDay();
        LocalDateTime departureEndOfDay = departureDate.atTime(LocalTime.MAX);
        LocalDateTime returnStartOfDay = returnDate.atStartOfDay();
        LocalDateTime returnEndOfDay = returnDate.atTime(LocalTime.MAX);

        List<Flight> departureFlights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTimeBetween(
                arrivalAirport, departureAirport, departureStartOfDay, departureEndOfDay
        );
        List<Flight> returnFlights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTimeBetween(
                departureAirport, arrivalAirport, returnStartOfDay, returnEndOfDay
        );
        List<List<Flight>> flights = new ArrayList<>();
        flights.add(departureFlights);
        flights.add(returnFlights);
        return flights;
    }

    public boolean deleteFlight(Flight flight) {
        flight = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTime(
                flight.getDepartureAirport(), flight.getArrivalAirport(), flight.getDepartureDateTime());
        if (flight != null) {
            flightRepository.delete(flight);
            return true;
        }
        return false;
    }

    public boolean updateFlightWithNewDepartureDateTime(Flight flight, LocalDateTime newDepartureDateTime) {
        flight = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTime(
                flight.getDepartureAirport(), flight.getArrivalAirport(), flight.getDepartureDateTime());
        if (flight != null) {
            flight.setDepartureDateTime(newDepartureDateTime);
            flightRepository.save(flight);
            return true;
        }
        return false;
    }
}
