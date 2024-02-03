package com.superflight1.util;

import com.superflight1.model.Airport;
import com.superflight1.model.Flight;
import com.superflight1.service.AirportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/mock")
@AllArgsConstructor
public class MockApi {

    private AirportService airportService;
    private static final int NUMBER_OF_FLIGHTS_TO_GENERATE = 40;

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getMockFlights() {
        List<Flight> mockFlights = generateRandomFlights();
        return ResponseEntity.ok(mockFlights);
    }

    public List<Flight> generateRandomFlights() {
        List<Airport> allAirports = airportService.findAllAirports();
        if (allAirports.size() < 2) {
            throw new IllegalStateException("Not enough airports to generate flights. ADD MORE AIRPORTS TO DB!");
        }

        List<Flight> generatedFlights = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < NUMBER_OF_FLIGHTS_TO_GENERATE / 2; i++) {
            Collections.shuffle(allAirports);
            Airport departureAirport = allAirports.get(0);
            Airport arrivalAirport = allAirports.get(1);

            LocalDateTime departureDateTime = generateRandomDepartureDateTime();
            LocalDateTime arrivalDateTime = generateRandomArrivalDateTime(departureDateTime);
            double price = 500 * (10 + random.nextInt(46));

            Flight departureFlight = new Flight();
            departureFlight.setDepartureAirport(departureAirport);
            departureFlight.setArrivalAirport(arrivalAirport);
            departureFlight.setDepartureDateTime(departureDateTime);
            departureFlight.setArrivalDateTime(arrivalDateTime);
            departureFlight.setPrice(price);
            generatedFlights.add(departureFlight);

            LocalDateTime returnDepartureDateTime = arrivalDateTime.plusDays(1 + random.nextInt(3));
            LocalDateTime returnArrivalDateTime = generateRandomArrivalDateTime(returnDepartureDateTime);
            Flight returnFlight = new Flight();
            returnFlight.setDepartureAirport(arrivalAirport);
            returnFlight.setArrivalAirport(departureAirport);
            returnFlight.setDepartureDateTime(returnDepartureDateTime);
            returnFlight.setArrivalDateTime(returnArrivalDateTime);
            returnFlight.setPrice(price);
            generatedFlights.add(returnFlight);
        }

        return generatedFlights;
    }

    private LocalDateTime generateRandomDepartureDateTime() {
        long minDay = LocalDateTime.now().minusDays(1).toEpochSecond(ZoneOffset.UTC);
        long maxDay = LocalDateTime.now().plusMonths(1).toEpochSecond(ZoneOffset.UTC);
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDateTime.ofEpochSecond(randomDay, 0, ZoneOffset.UTC).withHour(ThreadLocalRandom.current().nextInt(1, 24));
    }

    private LocalDateTime generateRandomArrivalDateTime(LocalDateTime departureDateTime) {
        return departureDateTime.plusHours(ThreadLocalRandom.current().nextLong(1, 11)); // 1 to 10 hours after departure
    }
}