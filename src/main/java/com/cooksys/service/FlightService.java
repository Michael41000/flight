package com.cooksys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cooksys.aspect.NotifyClients;
import com.cooksys.component.FlightGenerator;
import com.cooksys.pojo.Flight;

@Service
public class FlightService {

	@Autowired
	FlightGenerator generator;

	private List<Flight> flightList = new ArrayList<>();
	
	public List<Flight> getDailyFlightList()
	{
		return flightList;
	}
	
	@NotifyClients
	//The fixedDelay parameter determines how often a new day is generated as expressed in milliseconds
	@Scheduled(fixedDelay=1000 * 60 * 60 * 24)
	private void refreshFlights()
	{
		flightList = generator.generateNewFlightList();
	}

	public List<Flight> getFlightsByOriginAndDestination(String origin, String destination) {
		List<Flight> originAndDestination = new ArrayList<Flight>();
		for (int i = 0; i < flightList.size(); i++)
		{
			if (flightList.get(i).getOrigin().toLowerCase().equals(origin.toLowerCase()) 
					&& flightList.get(i).getDestination().toLowerCase().equals(destination.toLowerCase()))
			{
				originAndDestination.add(flightList.get(i));
			}
		}
		
		return originAndDestination;
	}

	public List<Flight> getFlightsByOrigin(String origin) {
		List<Flight> originFlights = new ArrayList<Flight>();
		for (int i = 0; i < flightList.size(); i++)
		{
			if (flightList.get(i).getOrigin().toLowerCase().equals(origin.toLowerCase()))
			{
				originFlights.add(flightList.get(i));
			}
		}
		
		return originFlights;
	}
	
	public List<Flight> getFlightsByDestination(String destination) {
		List<Flight> destinationFlights = new ArrayList<Flight>();
		for (int i = 0; i < flightList.size(); i++)
		{
			if (flightList.get(i).getDestination().toLowerCase().equals(destination.toLowerCase()))
			{
				destinationFlights.add(flightList.get(i));
			}
		}
		
		return destinationFlights;
	}
	
}
