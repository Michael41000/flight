package com.cooksys.dto;

import java.util.List;

import com.cooksys.entity.ItineraryFlight;

public class ItineraryDto {

	private List<ItineraryFlight> itinerary;

	private Long totalFlightTime;

	private Long totalLayoverTime;

	public List<ItineraryFlight> getItinerary() {
		return itinerary;
	}

	public void setItinerary(List<ItineraryFlight> itinerary) {
		this.itinerary = itinerary;
	}
	
	public Long getTotalFlightTime() {
		return totalFlightTime;
	}

	public void setTotalFlightTime(Long totalFlightTime) {
		this.totalFlightTime = totalFlightTime;
	}

	public Long getTotalLayoverTime() {
		return totalLayoverTime;
	}

	public void setTotalLayoverTime(Long totalLayoverTime) {
		this.totalLayoverTime = totalLayoverTime;
	}

}
