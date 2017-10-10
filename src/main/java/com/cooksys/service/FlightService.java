package com.cooksys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cooksys.aspect.NotifyClients;
import com.cooksys.component.FlightGenerator;
import com.cooksys.entity.Flight;
import com.cooksys.repository.FlightRepository;

@Service
public class FlightService {

	@Autowired
	FlightGenerator generator;
	
	@Autowired
	FlightRepository flightRepository;
	
	public List<Flight> getDailyFlightList()
	{
		return flightRepository.findAllByActiveTrueOrderByOffset();
	}
	
	@NotifyClients
	//The fixedDelay parameter determines how often a new day is generated as expressed in milliseconds
	@Scheduled(fixedDelay=1000 * 60 * 60 * 24)
	private void refreshFlights()
	{
		List<Flight> flightList = generator.generateNewFlightList();
		
		List<Flight> previousFlights = flightRepository.findAllByActiveTrueOrderByOffset();
		for (int i = 0; i < previousFlights.size(); i++)
		{
			Long itinerarySize = flightRepository.getSizeOfItineraryPartOf(previousFlights.get(i).getId());
			if (itinerarySize == 0)
			{
				flightRepository.delete(previousFlights.get(i));
			}
			else
			{
				previousFlights.get(i).setActive(false);
				flightRepository.save(previousFlights.get(i));
			}
		}
		
		for (int i = 0; i < flightList.size(); i++)
		{
			flightRepository.save(flightList.get(i));
		}
	}

	public List<Flight> getFlightsByOriginAndDestination(String origin, String destination) {
		return flightRepository.findAllByOriginIgnoreCaseAndDestinationIgnoreCaseAndActiveTrue(origin, destination);
	}

	public List<Flight> getFlightsByOrigin(String origin) {
		return flightRepository.findAllByOriginIgnoreCaseAndActiveTrue(origin);
	}
	
	public List<Flight> getFlightsByDestination(String destination) {
		return flightRepository.findAllByDestinationIgnoreCaseAndActiveTrue(destination);
	}
	
}
