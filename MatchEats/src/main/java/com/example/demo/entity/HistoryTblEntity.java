package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="history")
public class HistoryTblEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer historyId;

	@ManyToOne
    @JoinColumn(name="offer_id")
	private CookOfferTblEntity CookOfferTbl;

	@ManyToOne
	@JoinColumn(name="admin_id",nullable = true)
	private AdminTblEntity adminTbl;

	@Column(nullable = true)
	private Date recoveryDate;

	@Column(nullable = true)
	private Date deliveryCompleteDate;

	private  Integer stateStatus;

	private Integer adminProfit;

	private Integer cookProfit;

	@ManyToOne
    @JoinColumn(name="cookuser_id")
	private UserTblEntity cookOfferUser;

	@ManyToOne
    @JoinColumn(name="requestuser_id")
	private UserTblEntity requestUser;

	public Integer getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}

	public CookOfferTblEntity getCookOfferTbl() {
		return CookOfferTbl;
	}

	public void setCookOfferTbl(CookOfferTblEntity cookOfferTbl) {
		CookOfferTbl = cookOfferTbl;
	}

	public AdminTblEntity getAdminTbl() {
		return adminTbl;
	}

	public void setAdminTbl(AdminTblEntity adminTbl) {
		this.adminTbl = adminTbl;
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

	public Integer getStateStatus() {
		return stateStatus;
	}

	public void setStateStatus(Integer stateStatus) {
		this.stateStatus = stateStatus;
	}

	public Integer getAdminProfit() {
		return adminProfit;
	}

	public void setAdminProfit(Integer adminProfit) {
		this.adminProfit = adminProfit;
	}

	public Integer getCookProfit() {
		return cookProfit;
	}

	public void setCookProfit(Integer cookProfit) {
		this.cookProfit = cookProfit;
	}

	public UserTblEntity getCookOfferUser() {
		return cookOfferUser;
	}

	public void setCookOfferUser(UserTblEntity cookOfferUser) {
		this.cookOfferUser = cookOfferUser;
	}

	public UserTblEntity getRequestUser() {
		return requestUser;
	}

	public void setRequestUser(UserTblEntity requestUser) {
		this.requestUser = requestUser;
	}






}
