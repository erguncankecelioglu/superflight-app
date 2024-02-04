package com.superflight1.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResultDTO {

    String flightType;
    String direction;
    List<Flight> flights;

    public ResultDTO(String flightType, String departureAirport, String arrivalAirport, List<Flight> flights) {
        this.flightType = flightType;
        this.direction = departureAirport + " -> " + arrivalAirport;
        this.flights = flights;
    }

}
