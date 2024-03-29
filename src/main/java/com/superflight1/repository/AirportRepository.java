package com.superflight1.repository;

import com.superflight1.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AirportRepository extends JpaRepository<Airport, Long> {
    Airport findByCity(String city);
}

