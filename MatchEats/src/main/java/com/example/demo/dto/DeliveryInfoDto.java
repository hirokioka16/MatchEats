package com.example.demo.dto;

import java.util.Date;

public class DeliveryInfoDto {
	
	private int offerId;
	
	private String userName;
	
	private Date requestDay;
	
	private Date requestApprovalDay;
	
	private String address;
	
	private String content;

	

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getRequestDay() {
		return requestDay;
	}

	public void setRequestDay(Date requestDay) {
		this.requestDay = requestDay;
	}

	public Date getRequestApprovalDay() {
		return requestApprovalDay;
	}

	public void setRequestApprovalDay(Date requestApprovalDay) {
		this.requestApprovalDay = requestApprovalDay;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
