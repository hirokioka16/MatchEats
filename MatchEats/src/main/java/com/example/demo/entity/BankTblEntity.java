package com.example.demo.entity;

import java.io.Serializable;

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

	@Id
	@OneToOne
    @JoinColumn(name="userId")
	private UserTblEntity userTbl;
	
	private String bankName;
	
	private String branchName;
	
	private Integer accountNumber;
	
	private Integer accountName;
	
	
}
