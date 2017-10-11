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
	List<Flight> itinerary;
	
	@CreationTimestamp
	Timestamp timeAdded;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public List<Flight> getItinerary() {
		return itinerary;
	}

	public void setItinerary(List<Flight> itinerary) {
		this.itinerary = itinerary;
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
