package com.example.demo.dto;

import java.util.Date;

public class CookingInfoDto {

	private String offerId;
	private String requestId;
	private String userId;
	private int price;
	private String offerComment;
	private Date offerDate;
	private boolean deliveryFlg;
	private Date deliveryRequestDate;
	private String reactionStatus;
	private String reactionComment;
	private Date reactionDate;

	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getOfferComment() {
		return offerComment;
	}
	public void setOfferComment(String offerComment) {
		this.offerComment = offerComment;
	}
	public Date getOfferDate() {
		return offerDate;
	}
	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}
	public boolean isDeliveryFlg() {
		return deliveryFlg;
	}
	public void setDeliveryFlg(boolean deliveryFlg) {
		this.deliveryFlg = deliveryFlg;
	}
	public Date getDeliveryRequestDate() {
		return deliveryRequestDate;
	}
	public void setDeliveryRequestDate(Date deliveryRequestDate) {
		this.deliveryRequestDate = deliveryRequestDate;
	}
	public String getReactionStatus() {
		return reactionStatus;
	}
	public void setReactionStatus(String reactionStatus) {
		this.reactionStatus = reactionStatus;
	}
	public String getReactionComment() {
		return reactionComment;
	}
	public void setReactionComment(String reactionComment) {
		this.reactionComment = reactionComment;
	}
	public Date getReactionDate() {
		return reactionDate;
	}
	public void setReactionDate(Date reactionDate) {
		this.reactionDate = reactionDate;
	}





}
