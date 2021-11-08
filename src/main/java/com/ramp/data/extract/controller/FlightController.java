package com.ramp.data.extract.controller;

import com.ramp.data.extract.model.Flight;
import com.ramp.data.extract.service.FlightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class FlightController {

    @Resource
    private FlightService flightService;

    @GetMapping("/flights")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/flight/{airline}")
    public Flight getFlightDetails(@PathVariable("airline") String airline) {
        return flightService.getFlightDetails(airline);
    }

}
