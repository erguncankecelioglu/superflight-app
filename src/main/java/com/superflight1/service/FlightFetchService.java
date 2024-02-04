package com.superflight1.service;

import com.superflight1.model.Flight;
import com.superflight1.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class FlightFetchService {

    private final FlightRepository flightRepository;
    private final WebClient webClient;
    @Value("${flight-api.url}")
    private String flightApiUrl;
    @Value("${super-user.username}")
    private String user;
    @Value("${super-user.password}")
    private String password;

    public FlightFetchService(FlightRepository flightRepository, WebClient webClient) {
        this.flightRepository = flightRepository;
        this.webClient = webClient;
    }

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
                .uri(flightApiUrl)
                .headers(headers -> headers.setBasicAuth(user, password))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Flight>>() {})
                .block();
    }

    private boolean isDuplicate(Flight flight) {
        return flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTime(
                flight.getDepartureAirport(), flight.getArrivalAirport(), flight.getDepartureDateTime()) != null;
    }
}
