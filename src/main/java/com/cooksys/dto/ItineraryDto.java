package com.cooksys.dto;

import java.util.List;

import com.cooksys.entity.Flight;

public class ItineraryDto {
	
	List<Flight> itinerary;
	
	public List<Flight> getItinerary() {
		return itinerary;
	}

	public void setItinerary(List<Flight> itinerary) {
		this.itinerary = itinerary;
	}

}
