package com.example.demo.dto;

import java.util.Date;

public class HistoryInfoDto {

	private int historyId;
	private int offerId;
	private int adminId;
	private Date recoveryDate;
	private Date deliveryCompleteDate;
	private int stateStatus;
	private int adminProfit;
	private int cookProfit;
	public int getHistoryId() {
		return historyId;
	}
	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public Date getRecoveryDate() {
		return recoveryDate;
	}
	public void setRecoveryDate(Date recoveryDate) {
		this.recoveryDate = recoveryDate;
	}
	public Date getDeliveryCompleteDate() {
		return deliveryCompleteDate;
	}
	public void setDeliveryCompleteDate(Date deliveryCompleteDate) {
		this.deliveryCompleteDate = deliveryCompleteDate;
	}
	public int getStateStatus() {
		return stateStatus;
	}
	public void setStateStatus(int stateStatus) {
		this.stateStatus = stateStatus;
	}
	public int getAdminProfit() {
		return adminProfit;
	}
	public void setAdminProfit(int adminProfit) {
		this.adminProfit = adminProfit;
	}
	public int getCookProfit() {
		return cookProfit;
	}
	public void setCookProfit(int cookProfit) {
		this.cookProfit = cookProfit;
	}
	public int getCookUserId() {
		return cookUserId;
	}
	public void setCookUserId(int cookUserId) {
		this.cookUserId = cookUserId;
	}
	public int getRequestUserId() {
		return requestUserId;
	}
	public void setRequestUserId(int requestUserId) {
		this.requestUserId = requestUserId;
	}
	private int cookUserId;
	private int requestUserId;

}
