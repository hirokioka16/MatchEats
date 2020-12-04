package com.example.demo.dto;

import java.util.Date;

public class AdminHistoryDto {

	private String foodName;
	private String cookUserName;
	private String requestUserName;
	private Date RecoveryDate;
	private Date DeliveryCompleteDate;
    private int price;
    private int adminProfit;
    private int cookerProfit;
    private String stateStatus;

    public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}


	public String getStateStatus() {
		return stateStatus;
	}
	public void setStateStatus(String stateStatus) {
		this.stateStatus = stateStatus;
	}
	public String getCookUserName() {
		return cookUserName;
	}
	public void setCookUserName(String cookUserName) {
		this.cookUserName = cookUserName;
	}
	public String getRequestUserName() {
		return requestUserName;
	}
	public void setRequestUserName(String requestUserName) {
		this.requestUserName = requestUserName;
	}
	public Date getRecoveryDate() {
		return RecoveryDate;
	}
	public void setRecoveryDate(Date recoveryDate) {
		RecoveryDate = recoveryDate;
	}
	public Date getDeliveryCompleteDate() {
		return DeliveryCompleteDate;
	}
	public void setDeliveryCompleteDate(Date deliveryCompleteDate) {
		DeliveryCompleteDate = deliveryCompleteDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAdminProfit() {
		return adminProfit;
	}
	public void setAdminProfit(int adminProfit) {
		this.adminProfit = adminProfit;
	}
	public int getCookerProfit() {
		return cookerProfit;
	}
	public void setCookerProfit(int cookerProfit) {
		this.cookerProfit = cookerProfit;
	}

}
