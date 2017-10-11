package com.cooksys.mapper;


import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.dto.ItineraryDto;
import com.cooksys.entity.Itinerary;


@Mapper(componentModel="spring")
public interface ItineraryMapper {
	
	ItineraryDto toDto(Itinerary itinerary);
	
	Itinerary fromDto(ItineraryDto itineraryDto);
	
	List<ItineraryDto> toDtos(List<Itinerary> itineraries);
	
	List<Itinerary> fromDtos(List<ItineraryDto> itineraryDtos);
	
	

}
