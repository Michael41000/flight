package com.cooksys.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cooksys.aspect.NotifyClients;
import com.cooksys.component.FlightGenerator;
import com.cooksys.entity.Flight;

@Service
public class FlightService {

	@Autowired
	FlightGenerator generator;
	
	List<Flight> flightList;
	
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
		flightList.sort(new Comparator<Flight>() {
			@Override
			public int compare(Flight o1, Flight o2) {
				return (int) (o1.getOffset() - o2.getOffset());
			}
		});
	}

	public List<Flight> getFlightsByOriginAndDestination(String origin, String destination) {
		List<Flight> originDestinationList = new ArrayList<Flight>();
		for (int i = 0; i < flightList.size(); i++)
		{
			if (flightList.get(i).getOrigin().toLowerCase().equals(origin.toLowerCase()) 
					&& flightList.get(i).getDestination().toLowerCase().equals(destination.toLowerCase()))
			{
				originDestinationList.add(flightList.get(i));
			}
		}
		return originDestinationList;
	}

	public List<Flight> getFlightsByOrigin(String origin) {
		List<Flight> originList = new ArrayList<Flight>();
		for (int i = 0; i < flightList.size(); i++)
		{
			if (flightList.get(i).getOrigin().toLowerCase().equals(origin.toLowerCase()))
			{
				originList.add(flightList.get(i));
			}
		}
		return originList;
	}
	
	public List<Flight> getFlightsByDestination(String destination) {
		List<Flight> destinationList = new ArrayList<Flight>();
		for (int i = 0; i < flightList.size(); i++)
		{
			if (flightList.get(i).getDestination().toLowerCase().equals(destination.toLowerCase()))
			{
				destinationList.add(flightList.get(i));
			}
		}
		return destinationList;
	}

	public List<Flight> getFlightsByOriginStartsWith(String origin) {
		List<Flight> originList = new ArrayList<Flight>();
		for (int i = 0; i < flightList.size(); i++)
		{
			if (flightList.get(i).getOrigin().toLowerCase().startsWith(origin.toLowerCase()))
			{
				originList.add(flightList.get(i));
			}
		}
		return originList;
	}
	
	public List<Flight> getFlightsByDestinationStartsWith(String destination) {
		List<Flight> destinationList = new ArrayList<Flight>();
		for (int i = 0; i < flightList.size(); i++)
		{
			if (flightList.get(i).getDestination().toLowerCase().startsWith(destination.toLowerCase()))
			{
				destinationList.add(flightList.get(i));
			}
		}
		return destinationList;
	}
	
	public List<Flight> getFlightsByOriginStartsWithAndDestinationStartsWith(String origin, String destination) {
		List<Flight> originDestinationList = new ArrayList<Flight>();
		for (int i = 0; i < flightList.size(); i++)
		{
			if (flightList.get(i).getOrigin().toLowerCase().startsWith(origin.toLowerCase()) 
					&& flightList.get(i).getDestination().toLowerCase().startsWith(destination.toLowerCase()))
			{
				originDestinationList.add(flightList.get(i));
			}
		}
		return originDestinationList;
	}
	
}
