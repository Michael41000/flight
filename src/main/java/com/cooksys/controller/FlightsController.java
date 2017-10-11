package com.cooksys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.Flight;
import com.cooksys.service.FlightService;
import com.cooksys.service.LocationService;

@RestController
@RequestMapping("flights")
@CrossOrigin
public class FlightsController {
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	FlightService flightService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Flight> getFlightList()
	{
		return flightService.getDailyFlightList();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/origin/{origin}")
	public List<Flight> getFlightsByOrigin(@PathVariable String origin)
	{
		return flightService.getFlightsByOrigin(origin);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/destination/{destination}")
	public List<Flight> getFlightsByDestination(@PathVariable String destination)
	{
		return flightService.getFlightsByDestination(destination);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/origin/{origin}/destination/{destination}")
	public List<Flight> getFlightsByOriginAndDestination(@PathVariable("origin") String origin, @PathVariable("destination") String destination)
	{
		return flightService.getFlightsByOriginAndDestination(origin, destination);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/startsWith/origin/{origin}")
	public List<Flight> getFlightsByOriginStartsWith(@PathVariable String origin)
	{
		return flightService.getFlightsByOriginStartsWith(origin);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/startsWith/destination/{destination}")
	public List<Flight> getFlightsByDestinationStartsWith(@PathVariable String destination)
	{
		return flightService.getFlightsByDestinationStartsWith(destination);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/startsWith/origin/{origin}/startsWith/destination/{destination}")
	public List<Flight> getFlightsByOriginStartsWithAndDestinationStartsWith(@PathVariable("origin") String origin, @PathVariable("destination") String destination)
	{
		return flightService.getFlightsByOriginStartsWithAndDestinationStartsWith(origin, destination);
	}
	
	

}
