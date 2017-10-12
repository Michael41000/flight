package com.cooksys.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cooksys.dto.Flight;
import com.cooksys.entity.ItineraryFlight;

@Mapper(componentModel="spring")
public interface FlightMapper {
	
	@Mapping(source="layoverTime", target="layoverTime")
	ItineraryFlight toItineraryFlight(Flight flight, Long layoverTime);

}
