package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 
@Table(name="transfer")
public class TransferTblEntity implements Serializable{
	
	@Id
	private Integer transferId;
	
	@Id
	@OneToOne
    @JoinColumn(name="user_id")
	private UserTblEntity userTbl;
	
	private Integer price;
	
	private Date transferDate;
	
	private Integer acceptFlag;
	
	private Date acceptDate;

	public Integer getTransferId() {
		return transferId;
	}

	public void setTransferId(Integer transferId) {
		this.transferId = transferId;
	}

	public UserTblEntity getUserTbl() {
		return userTbl;
	}

	public void setUserTbl(UserTblEntity userTbl) {
		this.userTbl = userTbl;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public Integer getAcceptFlag() {
		return acceptFlag;
	}

	public void setAcceptFlag(Integer acceptFlag) {
		this.acceptFlag = acceptFlag;
	}

	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}
	
	

}
