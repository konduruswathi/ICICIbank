package com.capgemini.icicibank.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.icicibank.exception.CustomerNotFoundException;
import com.capgemini.icicibank.exception.LowBalanceException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value=CustomerNotFoundException.class)
	public String handleError(HttpServletRequest request,CustomerNotFoundException  exception)
	{
		request.setAttribute("success",exception.toString());
		
		System.out.println(exception);
		
		return "success";
	}
	
	@ExceptionHandler(value=LowBalanceException.class)
	public String handleErrors(HttpServletRequest request,LowBalanceException ex)
	{
		request.setAttribute("success", ex.toString());
		System.out.println(ex);
		return "success";
		
	}
}
