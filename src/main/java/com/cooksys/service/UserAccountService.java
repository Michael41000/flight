package com.cooksys.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.entity.UserAccount;
import com.cooksys.repository.UserAccountRepository;

@Service
public class UserAccountService {
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	public List<UserAccount> getUsers()
	{
		return userAccountRepository.findAll();
	}
	
	public UserAccount createUser(UserAccount userAccount)
	{
		UserAccount newUserAccount = userAccount;
		userAccountRepository.save(userAccount);
		return newUserAccount;
	}
	
	public UserAccount getUser(String username)
	{
		return userAccountRepository.findByUsernameIgnoreCase(username);
	}

	public boolean checkUserCredentials(String username, UserAccount userAccount) {
		UserAccount user = userAccountRepository.findByUsernameIgnoreCase(username);
		
		System.out.println(user);
		System.out.println(userAccount);
		if (user != null && userAccount != null && userAccount.getUsername() != null && userAccount.getPassword() != null
				&& userAccount.getUsername().toLowerCase().equals(user.getUsername().toLowerCase())
				&& userAccount.getPassword().equals(user.getPassword()))
		{
			return true;
		}
		return false;
	}

	public boolean checkUsernameAvailable(String username) {
		return userAccountRepository.findByUsernameIgnoreCase(username) == null ? true : false;
	}

}
