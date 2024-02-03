package com.superflight1.service;

import com.superflight1.model.Flight;
import com.superflight1.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@AllArgsConstructor
public class FlightFetchService {

    private FlightRepository flightRepository;
    private WebClient webClient;

    @Scheduled(fixedRate = 11300000, initialDelay = 5000) //initial delay 5 seconds and then every 3 hours
    public void importFlightsTask() {
        List<Flight> flights = fetchFlightsFromMockApi();
        for (Flight flight : flights) {
            if (!isDuplicate(flight)) {
                flightRepository.save(flight);
            }
        }
    }

    private List<Flight> fetchFlightsFromMockApi() {
        return webClient.get()
                .uri("http://localhost:8080/api/mock/flights")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Flight>>() {})
                .block();
    }

    private boolean isDuplicate(Flight flight) {
        return flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTime(
                flight.getDepartureAirport(), flight.getArrivalAirport(), flight.getDepartureDateTime()) != null;
    }
}
