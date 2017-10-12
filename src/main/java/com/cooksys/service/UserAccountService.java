package com.cooksys.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.dto.Credential;
import com.cooksys.dto.ItineraryCredentialDto;
import com.cooksys.dto.ItineraryDto;
import com.cooksys.entity.Flight;
import com.cooksys.entity.Itinerary;
import com.cooksys.entity.UserAccount;
import com.cooksys.mapper.ItineraryMapper;
import com.cooksys.repository.ItineraryRepository;
import com.cooksys.repository.UserAccountRepository;

@Service
public class UserAccountService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private ItineraryMapper itineraryMapper;

	@Autowired
	private ItineraryRepository itineraryRepository;

	public List<UserAccount> getUsers() {
		return userAccountRepository.findAll();
	}

	public UserAccount createUser(Credential credential) {
		UserAccount newUserAccount = new UserAccount();
		newUserAccount.setUsername(credential.getUsername());
		newUserAccount.setPassword(credential.getPassword());
		userAccountRepository.save(newUserAccount);
		return newUserAccount;
	}

	public UserAccount getUser(String username) {
		return userAccountRepository.findByUsernameIgnoreCase(username);
	}

	public boolean checkUserCredentials(String username, Credential credential) {
		UserAccount user = userAccountRepository.findByUsernameIgnoreCase(username);

		System.out.println(user);
		System.out.println(credential);
		if (user != null && credential != null && credential.getUsername() != null && credential.getPassword() != null
				&& credential.getUsername().toLowerCase().equals(user.getUsername().toLowerCase())
				&& credential.getPassword().equals(user.getPassword())) {
			return true;
		}
		return false;
	}

	public boolean checkUsernameAvailable(String username) {
		return userAccountRepository.findByUsernameIgnoreCase(username) == null ? true : false;
	}

	public boolean saveItinerary(ItineraryCredentialDto itineraryCredentialDto) {
		if (checkUserCredentials(itineraryCredentialDto.getCredential().getUsername(),
				itineraryCredentialDto.getCredential())) {
			UserAccount userVerified = getUser(itineraryCredentialDto.getCredential().getUsername());
			Itinerary itinerary = itineraryMapper.fromDto(itineraryCredentialDto.getItinerary());
			itineraryRepository.save(itinerary);
			userVerified.getUserItineraries().add(itinerary);
			userAccountRepository.save(userVerified);
			return true;
		}
		return false;
	}

	public List<ItineraryDto> getItineraries(String username) {
		UserAccount user = getUser(username);
		List<Itinerary> userItineraries = user.getUserItineraries();
		userItineraries.sort(new Comparator<Itinerary>() {
			@Override
			public int compare(Itinerary o1, Itinerary o2) {
				return o2.getTimeAdded().compareTo(o1.getTimeAdded());
			}
		});
		return itineraryMapper.toDtos(userItineraries);
	}

	public boolean deleteItinerary(Long id, Credential credential) {
		if (checkUserCredentials(credential.getUsername(), credential)) {
			UserAccount userVerified = getUser(credential.getUsername());
			List<Itinerary> userItineraries = userVerified.getUserItineraries();
			for (int i = 0; i < userItineraries.size(); i++) {
				if (Long.compare(userItineraries.get(i).getId(), id) == 0) {
					System.out.println("Got it");
					userItineraries.remove(i);
					itineraryRepository.delete(id);
					break;
				}
			}
			userVerified.setUserItineraries(userItineraries);
			userAccountRepository.save(userVerified);
			return true;
		}
		return false;
	}

	public List<ItineraryDto> getItinerariesByOriginStartsWith(String username, String origin) {
		List<ItineraryDto> userItineraries = getItineraries(username);

		List<ItineraryDto> originItineraries = new ArrayList<ItineraryDto>();
		for (int i = 0; i < userItineraries.size(); i++) {
			List<Flight> currentIninerary = userItineraries.get(i).getItinerary();
			String currentOrigin = currentIninerary.get(0).getOrigin();
			if (currentOrigin.toLowerCase().startsWith(origin.toLowerCase())) {
				originItineraries.add(userItineraries.get(i));
			}
		}

		return originItineraries;
	}

	public List<ItineraryDto> getItinerariesByDestinationStartsWith(String username, String destination) {
		List<ItineraryDto> userItineraries = getItineraries(username);

		List<ItineraryDto> destinationItineraries = new ArrayList<ItineraryDto>();
		for (int i = 0; i < userItineraries.size(); i++) {
			List<Flight> currentIninerary = userItineraries.get(i).getItinerary();
			String currentDestination = currentIninerary.get(currentIninerary.size() - 1).getDestination();
			if (currentDestination.toLowerCase().startsWith(destination.toLowerCase())) {
				destinationItineraries.add(userItineraries.get(i));
			}
		}

		return destinationItineraries;
	}

	public List<ItineraryDto> getItinerariesByOriginStartsWithAndDestinationStartsWith(String username, String origin,
			String destination) {
		List<ItineraryDto> userItineraries = getItineraries(username);

		List<ItineraryDto> originDestinationItineraries = new ArrayList<ItineraryDto>();
		for (int i = 0; i < userItineraries.size(); i++) {
			List<Flight> currentIninerary = userItineraries.get(i).getItinerary();
			String currentOrigin = currentIninerary.get(0).getOrigin();
			String currentDestination = currentIninerary.get(currentIninerary.size() - 1).getDestination();
			if (currentOrigin.toLowerCase().startsWith(origin.toLowerCase()) && currentDestination.toLowerCase().startsWith(destination.toLowerCase())) {
				originDestinationItineraries.add(userItineraries.get(i));
			}
		}

		return originDestinationItineraries;
	}

}
