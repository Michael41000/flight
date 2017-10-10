package com.cooksys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.Flight;
import com.cooksys.service.ItineraryService;

@RestController
@RequestMapping("itinerary")
@CrossOrigin
public class ItineraryController {

	@Autowired
	private ItineraryService itineraryService;

	@RequestMapping(method=RequestMethod.GET, value="/origin/{origin}/destination/{destination}")
	public List<Flight> getFastest(@PathVariable("origin") String origin, @PathVariable("destination") String destination) {
		return itineraryService.getFastest(origin, destination);
	}
}