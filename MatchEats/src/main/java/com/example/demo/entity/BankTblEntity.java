package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 
@Table(name="bankaccount")
public class BankTblEntity implements Serializable{


	
	@EmbeddedId
    private BankUserId BankUserId;
	
	private String bankName;
	
	private String branchName;
	
	private String accountNumber;
	
	private String accountName;



	public BankUserId getBankUserId() {
		return BankUserId;
	}

	public void setBankUserId(BankUserId bankUserId) {
		BankUserId = bankUserId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	
	
	
}
