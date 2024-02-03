package com.superflight1.service;

import com.superflight1.util.exception.customExceptions.NotFoundException;
import com.superflight1.model.Airport;
import com.superflight1.repository.AirportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AirportService {

    private AirportRepository airportRepository;

    public Airport findAirport(String city){
        Airport airport = airportRepository.findByCity(city);
        if(airport != null){
            return airport;
        }
        throw new NotFoundException("The airport with city: "+ city +" is not found");
    }

    public boolean deleteAirport(String city){
        Airport airport = airportRepository.findByCity(city);
        if(airport != null){
            airportRepository.delete(airport);
            return true;
        }
        throw new NotFoundException("The airport with city: "+ city +" is not found");
    }

    public boolean changeAirportCode(String city, String code){
        Airport airport = airportRepository.findByCity(city);
        if(airport != null){
            airport.setCode(code);
            airportRepository.save(airport);
            return true;
        }
        throw new NotFoundException("The airport with city: "+ city +" is not found");
    }

    public List<Airport> findAllAirports(){
        return airportRepository.findAll();
    }
}
