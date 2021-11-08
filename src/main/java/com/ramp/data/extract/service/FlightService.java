package com.ramp.data.extract.service;

import com.ramp.data.extract.dal.FlightMapper;
import com.ramp.data.extract.model.Flight;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FlightService {

    @Resource
    private FlightMapper flightMapper;

    public List<Flight> getAllFlights() {
        return flightMapper.getAllFlightDetails();
    }

    public Flight getFlightDetails(String airline) {
        return flightMapper.getFlightDetails(airline);
    }
}
