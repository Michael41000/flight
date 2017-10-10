package com.cooksys.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

 

@Entity
@Table(name = "Flight")
public class Flight {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	//Name of city where flight originates
	private String origin;
	
	@Column(nullable=false)
	//Name of city where flight lands
	private String destination;
	
	@Column(nullable=false)
	//How many hours flight is in the air
	private long flightTime;
	
	@Column(name="leaveTime", nullable=false)
	//How many hours after the start of the day until the flight takes off
	private long offset;
	
	@Column(nullable=false)
	private boolean active;
	
	@ManyToMany(mappedBy="itinerary")
	private List<Itinerary> itineraryPartOf;
	
	public List<Itinerary> getItineraryPartOf() {
		return itineraryPartOf;
	}
	public void setItineraryPartOf(List<Itinerary> itineraryPartOf) {
		this.itineraryPartOf = itineraryPartOf;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
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
		return "Flight [id=" + id + ", origin=" + origin + ", destination=" + destination + ", flightTime=" + flightTime
				+ ", offset=" + offset + ", active=" + active + ", itineraryPartOf=" + itineraryPartOf + "]";
	}
	
	

}
