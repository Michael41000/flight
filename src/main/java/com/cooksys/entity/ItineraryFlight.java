package com.cooksys.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ItineraryFlight {

	@Column(nullable=false)
	private String origin;
	
	@Column(nullable=false)
	private String destination;

	@Column(nullable=false)
	private Long flightTime;
	
	@Column(name="leaveTime", nullable=false)
	private Long offset;
	
	private Long layoverTime;

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Long getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(Long flightTime) {
		this.flightTime = flightTime;
	}

	public Long getOffset() {
		return offset;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}

	public Long getLayoverTime() {
		return layoverTime;
	}

	public void setLayoverTime(Long layoverTime) {
		this.layoverTime = layoverTime;
	}

	
}
