package com.capgemini.icicibank.service;

import com.capgemini.icicibank.exception.LowBalanceException;

public interface BankAccountService {
	public double getBalance(long accountId) ;
	public double withdraw(long accountId, double amount);
	public double deposit(long accountId, double amount) ;
	public boolean fundTransfer(long fromAcc, long toAcc, double amount)throws LowBalanceException;
}
