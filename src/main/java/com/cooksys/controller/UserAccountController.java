package com.cooksys.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.dto.Credential;
import com.cooksys.dto.ItineraryCredentialDto;
import com.cooksys.dto.ItineraryDto;
import com.cooksys.entity.Itinerary;
import com.cooksys.entity.UserAccount;
import com.cooksys.service.UserAccountService;

@RestController
@RequestMapping("users")
@CrossOrigin
public class UserAccountController {

	@Autowired
	private UserAccountService userAccountService;

	@RequestMapping(method=RequestMethod.GET)
	public List<UserAccount> getUsers() {
		return userAccountService.getUsers();
	}

	@RequestMapping(method=RequestMethod.POST)
	public UserAccount createUser(@RequestBody Credential credential,
			HttpServletResponse response) {
		UserAccount user = userAccountService.createUser(credential);

		response.setStatus(user != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);

		return user;
	}

	@RequestMapping(method=RequestMethod.GET, value="{username}")
	public UserAccount getUser(@PathVariable String username, HttpServletResponse response) {
		UserAccount user = userAccountService.getUser(username);

		response.setStatus(user != null ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NOT_FOUND);

		return user;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/checkUserCredentials/{username}")
	public boolean checkUser(@PathVariable String username, @RequestBody Credential credential)
	{
		return userAccountService.checkUserCredentials(username, credential);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/checkUsernameAvailable/{username}")
	public boolean checkUser(@PathVariable String username)
	{
		return userAccountService.checkUsernameAvailable(username);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/itineraries")
	public boolean saveItinerary(@RequestBody ItineraryCredentialDto itineraryCredentialDto)
	{
		return userAccountService.saveItinerary(itineraryCredentialDto);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/itineraries/{username}")
	public List<Itinerary> getItineraries(@PathVariable String username)
	{
		return userAccountService.getItineraries(username);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/itineraries/{id}")
	public boolean deleteItinerary(@PathVariable Long id, @RequestBody Credential credential)
	{
		return userAccountService.deleteItinerary(id, credential);
	}

}
