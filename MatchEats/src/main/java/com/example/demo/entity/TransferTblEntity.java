package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 
@Table(name="transfer")
public class TransferTblEntity implements Serializable{
	
	@EmbeddedId
    private TransferId transferId;
	
	private Integer price;
	
	private Date transferDate;
	
	private boolean acceptFlag;
	
	private Date acceptDate;

	

	public TransferId getTransferId() {
		return transferId;
	}

	public void setTransferId(TransferId transferId) {
		this.transferId = transferId;
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

	

	public boolean isAcceptFlag() {
		return acceptFlag;
	}

	public void setAcceptFlag(boolean acceptFlag) {
		this.acceptFlag = acceptFlag;
	}

	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}
	
	

}
