package com.capgemini.icicibank.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.icicibank.entities.BankAccount;
import com.capgemini.icicibank.entities.Customer;
import com.capgemini.icicibank.exception.LowBalanceException;
import com.capgemini.icicibank.service.BankAccountService;

@Controller
public class BankAccountController {

	@Autowired
	private BankAccountService bankaccountService;
	
	@RequestMapping("/k")
	public String sayHello() {
		return "index";
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getHeaderPage()
	{
		return "header";
	}
	
	@RequestMapping(value ="/balance", method=RequestMethod.GET)
	public String getBalancePage(HttpSession session, HttpServletRequest request,Model model) {
		BankAccount account;
		session= request.getSession();
		Customer customer=(Customer) session.getAttribute("customer");
		System.out.println("balancd"+customer);
	   double balance= bankaccountService.getBalance(customer.getAccount().getAccountId());
	   System.out.println(balance);
		model.addAttribute("balance",balance);
		return "balance";
	}
	@RequestMapping(value="/fundTransferPage",method=RequestMethod.GET)
	public String getFundTransferPage() {
		return "transfer";
	}
	@RequestMapping(value="/transfer",method=RequestMethod.POST)
public String fundTransfer(HttpSession session,HttpServletRequest request,Model model,@RequestParam long fromAccount,@RequestParam long toAccount,@RequestParam double amount) throws LowBalanceException {
		Customer customer=(Customer) session.getAttribute("customer");
		bankaccountService.fundTransfer(fromAccount, toAccount, amount) ;
		session.setAttribute("customer", customer);
		request.setAttribute("success", true);
	return "success";
		
	}

}
