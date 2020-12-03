package com.example.demo.dto;

import java.util.Date;

import com.example.demo.entity.TransferId;

public class TransferInfoDto {

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
