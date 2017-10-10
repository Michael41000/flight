package com.cooksys.service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.entity.Flight;
import com.cooksys.repository.FlightRepository;

@Service
public class ItineraryService {

	@Autowired
	FlightRepository flightrepository;

	
	public List<Flight> getFastest(String origin, String destination) {
		List<Flight> originFlights = flightrepository.findAllByOriginIgnoreCaseAndActiveTrue(origin);
		List<Tree> allItineraries = new ArrayList<Tree>();
		System.out.println("AllIts");
		for (int i = 0; i < originFlights.size(); i++) {
			Tree t = getItineraries(originFlights.get(i), destination, null);
			if (t != null)
			{
				allItineraries.add(t);
			}
		}
		
		System.out.println("AllIts");
		for(int i = 0; i < allItineraries.size(); i++)
		{
			System.out.println(i + " " + allItineraries.get(i));
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
			List<Flight> flightList = flightrepository.findAllByActiveTrueOrderByOffset();
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
		List<Tree> endNodes = new ArrayList<Tree>();
		Queue<Tree> queueNodes = new ArrayDeque<Tree>();
		queueNodes.add(t);
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

	class Tree {
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
