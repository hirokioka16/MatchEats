package com.example.demo.dto;

import java.util.Date;

public class EatHistoryDetailDto {

	private int historyId;
	private String foodName;
	private String nickName;
	private String requestOutline;
	private String offerComment;
	private Date requestDate;
	private int price;
	private int profit;
	private String stateStatus;
	private Date recoveryDate;
	private Date deliveryCompleteDate;

	public int getHistoryId() {
		return historyId;
	}
	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRequestOutline() {
		return requestOutline;
	}
	public void setRequestOutline(String requestOutline) {
		this.requestOutline = requestOutline;
	}
	public String getOfferComment() {
		return offerComment;
	}
	public void setOfferComment(String offerComment) {
		this.offerComment = offerComment;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getProfit() {
		return profit;
	}
	public void setProfit(int profit) {
		this.profit = profit;
	}
	public String getStateStatus() {
		return stateStatus;
	}
	public void setStateStatus(String stateStatus) {
		this.stateStatus = stateStatus;
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



}
