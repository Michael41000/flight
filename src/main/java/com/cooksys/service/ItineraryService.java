package com.cooksys.service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.dto.ItineraryDto;
import com.cooksys.entity.Flight;
import com.cooksys.entity.Itinerary;
import com.cooksys.mapper.ItineraryMapper;

@Service
public class ItineraryService {

	@Autowired
	FlightService flightService;
	
	@Autowired
	ItineraryMapper itineraryMapper;
	
	public List<Flight> getFastest(String origin, String destination) {
		List<Flight> originFlights = flightService.getFlightsByOrigin(origin);
		List<Tree> allItineraries = new ArrayList<Tree>();
		System.out.println("AllIts");
		for (int i = 0; i < originFlights.size(); i++) {
			Tree t = getItineraries(originFlights.get(i), destination, null);
			// Null check is here to remove origin itineraries that are not possible to make it to destination
			if (t != null)
			{
				allItineraries.add(t);
			}
		}
		
		System.out.println("AllIts");
		if (allItineraries.size() == 0)
		{
			return new ArrayList<Flight>();
		}
		List<HashMap<List<Flight>, Long>> shortestItineraries = new ArrayList<HashMap<List<Flight>, Long>>();
		Integer shortestTimeIndex = 0;
		Long shortestTime = Long.MAX_VALUE;
		for(int i = 0; i < allItineraries.size(); i++)
		{
			shortestItineraries.add(findShortest(allItineraries.get(i)));
			System.out.println(shortestItineraries.get(i));
			if (shortestItineraries.get(i).entrySet().iterator().next().getValue() < shortestTime)
			{
				shortestTimeIndex = i;
				shortestTime = shortestItineraries.get(i).entrySet().iterator().next().getValue();
			}
		}
		
		System.out.println(shortestTimeIndex);
		return shortestItineraries.get(shortestTimeIndex).entrySet().iterator().next().getKey();
	}
	
	public List<ItineraryDto> getItineraries(String origin, String destination) {
		List<Flight> originFlights = flightService.getFlightsByOrigin(origin);
		List<Tree> allItineraries = new ArrayList<Tree>();
		System.out.println("AllIts");
		for (int i = 0; i < originFlights.size(); i++) {
			Tree t = getItineraries(originFlights.get(i), destination, null);
			// Null check is here to remove origin itineraries that are not possible to make it to destination
			if (t != null)
			{
				allItineraries.add(t);
			}
		}
		
		System.out.println("AllIts");
		if (allItineraries.size() == 0)
		{
			return new ArrayList<ItineraryDto>();
		}
		
		List<Itinerary> itineraries = findAllItineraries(allItineraries);
		
		itineraries.sort(new Comparator<Itinerary>() {
			@Override
			public int compare(Itinerary o1, Itinerary o2) {
				Long timeTakenOne;
				if (o1.getItinerary().size() == 1)
				{
					Flight onlyFlight = o1.getItinerary().get(0);
					timeTakenOne = onlyFlight.getFlightTime();
				}
				else
				{
					Flight firstFlight = o1.getItinerary().get(0);
					Flight lastFlight = o1.getItinerary().get(o1.getItinerary().size() - 1);
					timeTakenOne = (lastFlight.getOffset() + lastFlight.getFlightTime()) - firstFlight.getOffset();
				}
				
				Long timeTakenTwo;
				if (o2.getItinerary().size() == 1)
				{
					Flight onlyFlight = o2.getItinerary().get(0);
					timeTakenTwo = onlyFlight.getFlightTime();
				}
				else
				{
					Flight firstFlight = o2.getItinerary().get(0);
					Flight lastFlight = o2.getItinerary().get(o2.getItinerary().size() - 1);
					timeTakenTwo = (lastFlight.getOffset() + lastFlight.getFlightTime()) - firstFlight.getOffset();
				}
				
				return Long.compare(timeTakenOne, timeTakenTwo);
			}
		});
		
		return itineraryMapper.toDtos(itineraries);
	}

	private Tree getItineraries(Flight flight, String destination, Tree t) {
		// Declare a new tree
		Tree newTree = new Tree();
		// Set flight currently looking at to data
		newTree.data = flight;
		newTree.parent = t;
		System.out.println("in recursion: " + flight);
		
		
		// If we are at destination
		if (flight.getDestination().toLowerCase().equals(destination.toLowerCase()))
		{
			System.out.println(newTree + " " + destination);
			System.out.println("should be over");
			return newTree;
		}
		else
		{
			System.out.println("NewTree: " + newTree);
			List<Flight> flightList = flightService.getDailyFlightList();
			boolean addToTree = false;
			for (int i = 0; i < flightList.size(); i++)
			{
				if ((flightList.get(i).getOffset() >= flight.getFlightTime() + flight.getOffset() + 1)
						&& flightList.get(i).getOrigin().toLowerCase().equals(flight.getDestination().toLowerCase()))
				{
					Tree t2 = getItineraries(flightList.get(i), destination, newTree);
					System.out.println(t2);
					if (t2 != null)
					{
						newTree.add(t2);
						addToTree = true;
					}
				}
			}
			
			if (addToTree)
			{
				return newTree;
			}
			else
			{
				return null;
			}
		}
		
	}
	
	private HashMap<List<Flight>, Long> findShortest(Tree t)
	{
		List<Tree> endNodes = getEndNodes(t);
		
		Long shortestTime = Long.MAX_VALUE;
		Integer shortestTimeIndex = 0;
		
		for (int i = 0; i < endNodes.size(); i++)
		{
			Long currentTime = (long) 0;
			Tree currentNode = endNodes.get(i);
			while (currentNode != null)
			{
				currentTime += currentNode.data.getFlightTime();
				currentNode = currentNode.parent;
			}
			if (currentTime < shortestTime)
			{
				shortestTime = currentTime;
				shortestTimeIndex = i;
			}
		}
		
		Tree currentNode = endNodes.get(shortestTimeIndex);
		HashMap<List<Flight>, Long> returnMap = new HashMap<List<Flight>, Long>();
		Stack<Flight> backwards = new Stack<Flight>();
		while(currentNode != null)
		{
			backwards.push(currentNode.data);
			currentNode = currentNode.parent;
		}
		
		List<Flight> returnList = new ArrayList<Flight>();
		while(backwards.isEmpty() == false)
		{
			returnList.add(backwards.pop());
		}
		
		returnMap.put(returnList, shortestTime);
		return returnMap;
		
	}
	
	private List<Itinerary> findAllItineraries(List<Tree> t)
	{
		List<Itinerary> allItineraries = new ArrayList<Itinerary>();
		for (int i = 0; i < t.size(); i++)
		{
			List<Tree> endNodes = getEndNodes(t.get(i));
			for (int j = 0; j < endNodes.size(); j++)
			{
				Itinerary itinerary = getItineraryFromEndNode(endNodes.get(j));
				if(itinerary != null)
				{
					allItineraries.add(itinerary);
				}
			}
		}
		return allItineraries;
	}
	
	private Itinerary getItineraryFromEndNode(Tree endNode)
	{
		Stack<Flight> backwardsItinerary = new Stack<Flight>();
		Set<String> originSet = new HashSet<String>();
		Tree currentNode = endNode;
		while (currentNode != null)
		{
			if (originSet.add(currentNode.data.getOrigin()) == false)
			{
				return null;
			}
			backwardsItinerary.add(currentNode.data);
			
			currentNode = currentNode.parent;
		}
		List<Flight> itineraryList = new ArrayList<Flight>();
		while(backwardsItinerary.isEmpty() == false)
		{
			itineraryList.add(backwardsItinerary.pop());
		}
		
		Itinerary itinerary = new Itinerary();
		itinerary.setItinerary(itineraryList);
		return itinerary;
	}
	
	private List<Tree> getEndNodes(Tree parentNode)
	{
		List<Tree> endNodes = new ArrayList<Tree>();
		Queue<Tree> queueNodes = new ArrayDeque<Tree>();
		queueNodes.add(parentNode);
		while(queueNodes.isEmpty() == false)
		{
			Tree queueTree = queueNodes.remove();
			if (queueTree.children.size() == 0)
			{
				endNodes.add(queueTree);
			}
			else
			{
				queueNodes.addAll(queueTree.children);
			}
		}
		return endNodes;
	}

	private class Tree {
		private Flight data;
		private Tree parent;
		private List<Tree> children;
		
		public Tree()
		{
			children = new ArrayList<Tree>();
		}
		
		public Tree add(Tree t)
		{
			children.add(t);
			return t;
		}

		@Override
		public String toString() {
			return "Tree [data=" + data + ", \n\tchildren=" + children + "]";
		}
		
		
	}

	

}
