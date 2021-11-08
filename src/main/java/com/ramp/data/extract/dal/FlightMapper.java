package com.ramp.data.extract.dal;

import com.ramp.data.extract.model.Flight;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FlightMapper {

    @Select("SELECT * FROM flight_tbl LIMIT 10")
    List<Flight> getAllFlightDetails();

    @Select("SELECT * FROM flight_tbl WHERE airline = airline LIMIT 1")
    Flight getFlightDetails(String airline);
}
