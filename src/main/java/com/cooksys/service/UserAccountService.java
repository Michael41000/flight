package com.cooksys.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.dto.Credential;
import com.cooksys.dto.ItineraryCredentialDto;
import com.cooksys.dto.ItineraryDto;
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
	
	public List<UserAccount> getUsers()
	{
		return userAccountRepository.findAll();
	}
	
	public UserAccount createUser(Credential credential)
	{
		UserAccount newUserAccount = new UserAccount();
		newUserAccount.setUsername(credential.getUsername());
		newUserAccount.setPassword(credential.getPassword());
		userAccountRepository.save(newUserAccount);
		return newUserAccount;
	}
	
	public UserAccount getUser(String username)
	{
		return userAccountRepository.findByUsernameIgnoreCase(username);
	}

	public boolean checkUserCredentials(String username, Credential credential) {
		UserAccount user = userAccountRepository.findByUsernameIgnoreCase(username);
		
		System.out.println(user);
		System.out.println(credential);
		if (user != null && credential != null && credential.getUsername() != null && credential.getPassword() != null
				&& credential.getUsername().toLowerCase().equals(user.getUsername().toLowerCase())
				&& credential.getPassword().equals(user.getPassword()))
		{
			return true;
		}
		return false;
	}

	public boolean checkUsernameAvailable(String username) {
		return userAccountRepository.findByUsernameIgnoreCase(username) == null ? true : false;
	}

	public boolean saveItinerary(ItineraryCredentialDto itineraryCredentialDto) {
		if (checkUserCredentials(itineraryCredentialDto.getCredential().getUsername(), itineraryCredentialDto.getCredential()))
		{
			UserAccount userVerified = getUser(itineraryCredentialDto.getCredential().getUsername());
			Itinerary itinerary = itineraryMapper.fromDto(itineraryCredentialDto.getItinerary());
			itineraryRepository.save(itinerary);
			userVerified.getUserItineraries().add(itinerary);
			userAccountRepository.save(userVerified);
			return true;
		}
		return false;
	}

	public List<ItineraryDto> getItinerary(String username) {
		UserAccount user = getUser(username);
		
		return itineraryMapper.toDtos(user.getUserItineraries());
	}
	

}
