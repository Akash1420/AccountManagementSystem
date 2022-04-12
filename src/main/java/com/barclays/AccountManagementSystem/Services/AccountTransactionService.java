package com.barclays.AccountManagementSystem.Services;

import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import  java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.barclays.AccountManagementSystem.Entities.AccountTransaction;
import com.barclays.AccountManagementSystem.Entities.BankAccount;
import com.barclays.AccountManagementSystem.Repositories.AccountTransactionRepository;
import com.barclays.AccountManagementSystem.Repositories.BankAccountRepository;

@Repository
public class AccountTransactionService {
	
	@Autowired
	private AccountTransactionRepository accountTrRepo;
	
	@Autowired
	private BankAccountRepository bankAccountRepo;
	
	public AccountTransaction credit(AccountTransaction transaction) {
		Date currentDate = Calendar.getInstance().getTime();
		transaction.setDateTime(currentDate);
		
		transaction.setTransactionType("credit");
		transaction.setSubType("online");
		
		List<AccountTransaction> transList= accountTrRepo.findBydateTimeAndAccountNumber(currentDate, transaction.getAccountNumber());
		double sum=0;
		for(int i=0;i<transList.size();i++) {
			if(transList.get(i).getTransactionType().equals("debit")) {
			sum += transList.get(i).getBalance() ;
			}
		}
		
		if(sum+transaction.getBalance()>10000.0) {
			return null;
		}
		
		
		
		accountTrRepo.save(transaction);
		
		
		Optional<BankAccount> banAcc =  bankAccountRepo.findById(transaction.getAccountNumber()) ;
		BankAccount acc =banAcc.get();
		acc.setBalance(acc.getBalance()+transaction.getBalance());
		bankAccountRepo.save(acc);
		
		return transaction;
	}
	
	public AccountTransaction debit(AccountTransaction transaction) {
		Date currentDate = Calendar.getInstance().getTime();
		
		List<AccountTransaction> transList= accountTrRepo.findBydateTimeAndAccountNumber(currentDate, transaction.getAccountNumber());
		double sum=0;
		for(int i=0;i<transList.size();i++) {
			if(transList.get(i).getTransactionType().equals("debit")) {
				sum += transList.get(i).getBalance() ;
			}
		}
		
		if(sum+transaction.getBalance()>10000.0) {
			return null;
		}
		AccountTransaction newtransaction =new AccountTransaction();
		
		newtransaction.setAccountNumber(transaction.getSecondPartyAccount());
		newtransaction.setSecondPartyAccount(transaction.getAccountNumber());
		newtransaction.setDateTime(currentDate);
		newtransaction.setTransactionType("debit");
		newtransaction.setSubType("online");
		newtransaction.setBalance(transaction.getBalance());
		newtransaction.setReferenceNumber(transaction.getReferenceNumber());
		accountTrRepo.save(newtransaction);
		
		Optional<BankAccount> banAcc =  bankAccountRepo.findById(newtransaction.getAccountNumber()) ;
		BankAccount acc =banAcc.get();
		acc.setBalance(acc.getBalance()-transaction.getBalance());
		bankAccountRepo.save(acc);
		
		return transaction;
	}
	
	public AccountTransaction withdrawl(AccountTransaction transaction) {
		Date currentDate = Calendar.getInstance().getTime();
		transaction.setDateTime(currentDate);
		transaction.setTransactionType("withdrawl");
		transaction.setSubType("cash");
		accountTrRepo.save(transaction);
		
	

		Optional<BankAccount> banAcc = bankAccountRepo.findById(transaction.getAccountNumber());
		BankAccount acc = banAcc.get();
		acc.setBalance(acc.getBalance() - transaction.getBalance());
		bankAccountRepo.save(acc);

		return transaction;
	}
	
	public List<AccountTransaction> getByAccount(long accountNo) {
		return accountTrRepo.findByAccountNumber(accountNo);
	}
	
	public List<AccountTransaction> getMiniStatementByAccount(long accountNo) {
		List<AccountTransaction> accountTrans =accountTrRepo.findByAccountNumber(accountNo);
		
		Collections.sort(accountTrans, new Comparator<AccountTransaction>() {
			
			public int compare(AccountTransaction o1, AccountTransaction o2) {
				return o2.getDateTime().toString().compareTo(o1.getDateTime().toString());
			}
		});
		
		List<AccountTransaction> accountTransacn =new ArrayList();
		int size = accountTrans.size();
		
		if(size<=5) {
			return accountTrans;
		}
		else {
			for(int i=0;i<5;i++) {
				accountTransacn.add(accountTrans.get(i));
			}
			
			return accountTransacn;
		}
	}
	
	public List<AccountTransaction> getByDate(String startdate, String endDate, long account) throws ParseException{
		List<AccountTransaction> transList = accountTrRepo.findByAccountNumber(account);
		List<AccountTransaction> transListwithDate = accountTrRepo.findByAccountNumber(account);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<transList.size();i++) {
			String newdate = transList.get(i).getDateTime().toString().substring(0, 10);
			if(newdate.compareTo(startdate)>=0&&newdate.compareTo(endDate)<=0) {
				transListwithDate.add(transList.get(i));
			}
			
		}
		
		return transListwithDate;
		
		
	}
	
//	public void getCsvFormat(long accountNo){
//		List<AccountTransaction> accountTrans = accountTrRepo.findByAccountNumber(accountNo);
//		
//		Collections.sort(accountTrans, new Comparator<AccountTransaction>() {
//			
//			public int compare(AccountTransaction o1, AccountTransaction o2) {
//				return o2.getDateTime().toString().compareTo(o1.getDateTime().toString());
//			}
//		});
//		
//		List<AccountTransaction> accountTransacn =new ArrayList();
//		int size = accountTrans.size();
//		
//		if(size<=5) {
//			
//		}
//		else {
//			for(int i=0;i<5;i++) {
//				accountTransacn.add(accountTrans.get(i));
//			}
//			
//		}
//		StringBuilder stringBuilder = new StringBuilder();
//		
//		for(int i = 0; i < accountTransacn.size(); i++){
//			stringBuilder.append(accountTransacn.get(i).getTransactionId()).append(",").append(accountTransacn.get(i).getReferenceNumber()).append(",").append(accountTransacn.get(i).getDateTime()).append(",").append(accountTransacn.get(i).getSubType()).append(",").append(accountTransacn.get(i).getBalance()).append(",").append(accountTransacn.get(i).getAccountNumber()).append("\n");
//		}
//		
//		try (FileWriter fileWriter = new FileWriter("D:\\hello.csv")) {
//			fileWriter.write(stringBuilder.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	

}
