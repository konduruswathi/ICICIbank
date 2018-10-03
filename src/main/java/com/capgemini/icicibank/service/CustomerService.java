package com.capgemini.icicibank.service;

import org.springframework.dao.DataAccessException;

import com.capgemini.icicibank.entities.Customer;

import com.capgemini.icicibank.exception.UserNotFoundException;


public interface CustomerService {
	public Customer authenticate(Customer customer) throws UserNotFoundException ;
	public Customer updateProfile(Customer customer);
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword);
	
}
