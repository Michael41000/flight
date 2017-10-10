package com.cooksys.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;

 

@Embeddable
public class Flight {
	
	@Column(nullable=false)
	//Name of city where flight originates
	private String origin;
	
	@Column(nullable=false)
	//Name of city where flight lands
	private String destination;
	
	@Column(nullable=false)
	//How many hours flight is in the air
	private Long flightTime;
	
	@Column(name="leaveTime", nullable=false)
	//How many hours after the start of the day until the flight takes off
	private Long offset;
	
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
	
	public long getFlightTime() {
		return flightTime;
	}
	
	public void setFlightTime(long flightTime) {
		this.flightTime = flightTime;
	}
	
	public long getOffset() {
		return offset;
	}
	
	public void setOffset(long offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "Flight [origin=" + origin + ", destination=" + destination + ", flightTime=" + flightTime + ", offset="
				+ offset + "]";
	}
	
	
	

}
