package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cookoffer")
public class CookOfferTblEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer offerId;

	@ManyToOne
    @JoinColumn(name="request_id")
	private FoodTblEntity foodTbl;

	@ManyToOne
    @JoinColumn(name="user_id")
	private UserTblEntity userTbl;


	private Integer price;

	private String offerComment;

	private Date offerDate;

	private boolean deliveryFlg;

	private Date deliveryRequestDate;

	private String reactionStatus;

	private String reactionComment;

	private Date reactionDate;

	private Date approvalRequestDeliveryDate;

	private String approvalDeliveryStatus;
	
	@OneToMany(mappedBy="CookOfferTbl")
	private List<HistoryTblEntity> offerList;
	
	
	public List<HistoryTblEntity> getOfferList() {
		return offerList;
	}

	public void setOfferList(List<HistoryTblEntity> offerList) {
		this.offerList = offerList;
	}

	public Date getApprovalRequestDeliveryDate() {
		return approvalRequestDeliveryDate;
	}

	public void setApprovalRequestDeliveryDate(Date approvalRequestDeliveryDate) {
		this.approvalRequestDeliveryDate = approvalRequestDeliveryDate;
	}

	public Integer getOfferId() {
		return offerId;
	}

	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}

	public FoodTblEntity getFoodTbl() {
		return foodTbl;
	}

	public void setFoodTbl(FoodTblEntity foodTbl) {
		this.foodTbl = foodTbl;
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

	public boolean getDeliveryFlg() {
		return deliveryFlg;
	}

	public void setDeliveryFlg(boolean b) {
		this.deliveryFlg = b;
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

	public String getApprovalDeliveryStatus() {
		return approvalDeliveryStatus;
	}

	public void setApprovalDeliveryStatus(String approvalDeliveryStatus) {
		this.approvalDeliveryStatus = approvalDeliveryStatus;
	}













}
