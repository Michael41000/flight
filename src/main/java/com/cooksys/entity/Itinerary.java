package com.cooksys.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Itinerary")
public class Itinerary {
	
	@Id
	@GeneratedValue
	private long id;

	@ElementCollection
	private List<ItineraryFlight> itinerary;
	
	@CreationTimestamp
	private Timestamp timeAdded;
	
	private Long totalFlightTime;
	
	private Long totalLayoverTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public List<ItineraryFlight> getItinerary() {
		return itinerary;
	}

	public void setItinerary(List<ItineraryFlight> itinerary) {
		this.itinerary = itinerary;
	}

	public Timestamp getTimeAdded() {
		return timeAdded;
	}

	public void setTimeAdded(Timestamp timeAdded) {
		this.timeAdded = timeAdded;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Itinerary other = (Itinerary) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
