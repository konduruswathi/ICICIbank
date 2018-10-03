package com.capgemini.icicibank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.icicibank.exception.LowBalanceException;
import com.capgemini.icicibank.repository.BankAccountRepository;
import com.capgemini.icicibank.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountRepository bankAccountRepository;
	
	@Override
	public double getBalance(long accountId) {
		return bankAccountRepository.getBalance(accountId);
	}

	@Override
	public double withdraw(long accountId, double amount)  {
		double balance = bankAccountRepository.getBalance(accountId);
		if (balance != -1) {
			if (balance - amount >= 0) {
				bankAccountRepository.updateBalance(accountId, balance - amount);
				return bankAccountRepository.getBalance(accountId);
			}
		}
		return balance;
	}

	@Override
	public double deposit(long accountId, double amount) {
		double balance = bankAccountRepository.getBalance(accountId);
		if (balance != -1) {
			bankAccountRepository.updateBalance(accountId, balance + amount);
			return bankAccountRepository.getBalance(accountId);
		}
		return balance;
	}

	@Override
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) throws LowBalanceException{
		try {
			double balance = withdraw(fromAcc, amount);
		
		if (balance != -1) {
			if (deposit(toAcc, amount) == -1) {
				return false;
			}
			return true;
		}
		return false;
		}catch(DataAccessException e) {
			LowBalanceException l=new LowBalanceException("insufficient balance");
			l.initCause(e);
			throw l;
		}
		
	
	}

}
