package com.superflight1.service;

import com.superflight1.util.AirportCode;
import com.superflight1.model.Airport;
import com.superflight1.repository.AirportRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class InitService {
    private AirportRepository airportRepository;

    @PostConstruct
    public void initializeAirports() {
        if (airportRepository.count() == 0) {
            Arrays.stream(AirportCode.values()).forEach(airportCode -> {
                Airport airport = new Airport();
                airport.setCity(airportCode.getCity());
                airport.setCode(airportCode.getCode());
                airportRepository.save(airport);
            });
        }
    }
}
