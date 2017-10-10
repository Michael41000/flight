package com.cooksys.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public UserAccount createUser(@RequestBody UserAccount userAccount,
			HttpServletResponse response) {
		UserAccount user = userAccountService.createUser(userAccount);

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
	public boolean checkUser(@PathVariable String username, @RequestBody UserAccount userAccount)
	{
		return userAccountService.checkUserCredentials(username, userAccount);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/checkUsernameAvailable/{username}")
	public boolean checkUser(@PathVariable String username)
	{
		return userAccountService.checkUsernameAvailable(username);
	}

}
