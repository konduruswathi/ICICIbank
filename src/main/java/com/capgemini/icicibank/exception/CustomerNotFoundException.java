package com.capgemini.icicibank.exception;

public class CustomerNotFoundException extends RuntimeException{
public CustomerNotFoundException(String message) {
	super(message);
}
}
