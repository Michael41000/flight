package com.cooksys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cooksys.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	List<Flight> findAllByActiveTrueOrderByOffset();

	List<Flight> findAllByOriginIgnoreCaseAndActiveTrue(String origin);
	
	List<Flight> findAllByDestinationIgnoreCaseAndActiveTrue(String destination);
	
	List<Flight> findAllByOriginIgnoreCaseAndDestinationIgnoreCaseAndActiveTrue(String origin, String destination);
	
	List<Flight> findByOriginIgnoreCaseAndActiveTrue(String origin);
	
	@Query("SELECT SIZE(f.itineraryPartOf) FROM Flight f where f.id = :id")
	Long getSizeOfItineraryPartOf(@Param("id") Long id);
	
	
	
}
