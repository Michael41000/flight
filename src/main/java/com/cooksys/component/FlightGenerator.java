package com.cooksys.component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cooksys.entity.Flight;
import com.cooksys.pojo.Cities;

@Component
public class FlightGenerator {

	
	public List<Flight> generateNewFlightList() {
		
		List<Flight> result = new ArrayList<>();

		//for (int i = 0; i < 5; i++) {
		for (int i = 0; i < 20; i++) {
			int originIndex = ThreadLocalRandom.current().nextInt(0, 4);

			int destinationIndex = ThreadLocalRandom.current().nextInt(0, 4);

			while (destinationIndex == originIndex)
				destinationIndex = ThreadLocalRandom.current().nextInt(0, 4);

			String origin = Cities.values()[originIndex].getName();
			String destination = Cities.values()[destinationIndex].getName();
			int flightTime = ThreadLocalRandom.current().nextInt(1, 4);
			//int flightTime = ThreadLocalRandom.current().nextInt(1, 100);
			int offset = ThreadLocalRandom.current().nextInt(0, 10);

			Flight f = new Flight();
			f.setOrigin(origin);
			f.setDestination(destination);
			f.setFlightTime(flightTime);
			f.setOffset(offset);

			result.add(f);
		}
		
		
		return result;
	}

}
