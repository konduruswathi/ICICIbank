package com.capgemini.icicibank.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.icicibank.exception.LowBalanceException;
import com.capgemini.icicibank.exception.UserNotFoundException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value=UserNotFoundException.class)
	public String handleError(HttpServletRequest request,UserNotFoundException  exception)
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
