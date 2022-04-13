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
import java.util.Random;
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
		Random rand =new Random();
		long transactionId =rand.nextLong((long)1e10, (long)1e11);
		transaction.setTransactionId(transactionId);
		Date currentDate = Calendar.getInstance().getTime();
		transaction.setDateTime(currentDate);
		
		transaction.setTransactionType("credit");
		transaction.setSubType("online");
		
		List<AccountTransaction> transList= accountTrRepo.findByAccountNumber( transaction.getAccountNumber());
		double sum=0;
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

		String date1 = format1.format(currentDate);            

	
		for(int i=0;i<transList.size();i++) {
			String da=date1;
			String da1=transList.get(i).getDateTime().toString().substring(0, 10);
			
			if(transList.get(i).getTransactionType().equals("debit")&&da.equals(da1)) {
			sum += transList.get(i).getBalance() ;
			
			}
		}
		System.out.println(sum);
		
		
		if(sum+transaction.getBalance()>100000.0) {
			System.out.println();
			System.out.println("transaction Not APplicable, Todays limit exceed");
			System.out.println();
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
		Random rand =new Random();
		long transactionId =rand.nextLong((long)1e10, (long)1e11);
		transaction.setTransactionId(transactionId);
		Date currentDate = Calendar.getInstance().getTime();
		
		List<AccountTransaction> transList= accountTrRepo.findByAccountNumber( transaction.getAccountNumber());
		double sum=0;
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

		String date1 = format1.format(currentDate); 
		for(int i=0;i<transList.size();i++) {
			String da=date1;
			String da1=transList.get(i).getDateTime().toString().substring(0, 10);
			if(transList.get(i).getTransactionType().equals("debit")&&da.equals(da1)) {
				sum += transList.get(i).getBalance() ;
			}
		}
		
		if(sum+transaction.getBalance()>100000.0) {
			
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
		newtransaction.setTransactionId(transactionId);
		accountTrRepo.save(newtransaction);
		
		Optional<BankAccount> banAcc =  bankAccountRepo.findById(newtransaction.getAccountNumber()) ;
		BankAccount acc =banAcc.get();
		acc.setBalance(acc.getBalance()-transaction.getBalance());
		bankAccountRepo.save(acc);
		
		return transaction;
	}
	
	public AccountTransaction withdrawl(AccountTransaction transaction) {
		Random rand =new Random();
		long transactionId =rand.nextLong((long)1e10, (long)1e11);
		transaction.setTransactionId(transactionId);
		Date currentDate = Calendar.getInstance().getTime();
		transaction.setDateTime(currentDate);
		transaction.setTransactionType("withdrawl");
		transaction.setSubType("cash");
		List<AccountTransaction> transList= accountTrRepo.findByAccountNumber( transaction.getAccountNumber());
		double sum=0;
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

		String date1 = format1.format(currentDate); 
		for(int i=0;i<transList.size();i++) {
			String da=date1;
			String da1=transList.get(i).getDateTime().toString().substring(0, 10);
			if(transList.get(i).getTransactionType().equals("debit")&&da.equals(da1)) {
				sum += transList.get(i).getBalance() ;
			}
		}
		
		if(sum+transaction.getBalance()>100000.0) {
			System.out.println("daily limit exhausted");
			return null;
		}
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
		List<AccountTransaction> transListwithDate = new ArrayList<>();
		
		for(int i=0;i<transList.size();i++) {
			String newdate = transList.get(i).getDateTime().toString().substring(0, 10);
			System.out.println(newdate+" "+startdate+" "+endDate);
			int f=0;
			for(int j=0;j<newdate.length();j++) {
				if(newdate.charAt(j)!='-') {
					
				}
				else if((int)newdate.charAt(j)<(int)startdate.charAt(j)&&(int)newdate.charAt(j)>(int)endDate.charAt(j)) {
					f=1;
				}
				
			}
			if(f==0)
			transListwithDate.add(transList.get(i));
			
			System.out.println(newdate.length()+ " "+startdate.length()+""+endDate.length());
		}
		System.out.println(transListwithDate.size()+ " ");
		StringBuilder csvGenerate =new StringBuilder();
		csvGenerate.append("transactionId").append(",").append("referenceNumber").append(",").append("transactionType").append(",").append("subType").append(",").append("balance").append(",").append("accountNumber").append(",").append("Date").append("\n");
		for(int i=0;i<transListwithDate.size();i++) {
			csvGenerate.append(transListwithDate.get(i).getTransactionId()).append(",").append(transListwithDate.get(i).getReferenceNumber()).append(",").append(transListwithDate.get(i).getTransactionType()).append(",").append(transListwithDate.get(i).getSubType()).append(",").append(transListwithDate.get(i).getBalance()).append(",").append(transListwithDate.get(i).getAccountNumber()).append(",").append(transListwithDate.get(i).getDateTime()).append("\n");
		}
		
		try(FileWriter writer = new FileWriter("G:\\transaction.csv")){
			writer.write(csvGenerate.toString());
		}catch(Exception e) {
			
		}
		
		return transListwithDate;
		
		
	}
	
	

}
