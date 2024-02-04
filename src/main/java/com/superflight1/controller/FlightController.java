package com.superflight1.controller;

import com.superflight1.model.Airport;
import com.superflight1.service.AirportService;
import com.superflight1.service.FlightService;
import com.superflight1.util.exception.customExceptions.BadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/flights")
@AllArgsConstructor
public class FlightController {

    private FlightService flightService;
    private AirportService airportService;

    @GetMapping("/get")
    @Operation(description = "Get flight information as a list of flight lists by providing departure and arrival airport cities, " +
            "and two dates in YYYY-MM-DD format. The first date is for departure. The second date, which is optional, is for the return. " +
            "If the return date is not provided, the API returns information for a one-way flight. " +
            "If the return date is provided, the API returns information for a round trip.")
    public ResponseEntity<?> getFlights(
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam String departureDate,
            @RequestParam(required = false) String returnDate) {
        Airport depAirport = airportService.findAirport(departureAirport);
        Airport arrAirport = airportService.findAirport(arrivalAirport);
        LocalDate departureLocalDate;
        if (depAirport == null || arrAirport == null) {
            throw new BadRequestException("Invalid airport name");
        }
        try {
            departureLocalDate = LocalDate.parse(departureDate);
        } catch (Exception e) {
            throw new BadRequestException("Invalid date format. Please use YYYY-MM-DD");
        }
        if (returnDate == null || returnDate.trim().isEmpty()) {
            return ResponseEntity.ok(flightService.ListOneWayFlights(depAirport, arrAirport, departureLocalDate));
        } else {
            LocalDate returnLocalDate;
            try {
                returnLocalDate = LocalDate.parse(returnDate);
            } catch (Exception e) {
                throw new BadRequestException("Invalid date format. Please use YYYY-MM-DD");
            }
            if (returnLocalDate.isBefore(departureLocalDate)) {
                throw new BadRequestException("Return date cannot be before departure date");
            }
            return ResponseEntity.ok(flightService.ListRoundFlights(depAirport, arrAirport, departureLocalDate, returnLocalDate));
        }
    }

}
