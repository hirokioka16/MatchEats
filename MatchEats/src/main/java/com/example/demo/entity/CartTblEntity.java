package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@Entity 
//@Table(name="cart")
public class CartTblEntity implements Serializable{
	
}
//	@Id
//	@ManyToOne
//    @JoinColumn(name="user_id")
//	private UserTblEntity userTbl;
//	
//	@ManyToOne
//    @JoinColumn(name="offer_id")
//	private CookOfferTblEntity CookOfferTbl;
//	
//	@ManyToMany
//    @JoinColumn(name="cooker_id")
//	private UserTblEntity cookUserTbl;
//
//	public UserTblEntity getUserTbl() {
//		return userTbl;
//	}
//
//	public void setUserTbl(UserTblEntity userTbl) {
//		this.userTbl = userTbl;
//	}
//
//	public CookOfferTblEntity getCookOfferTbl() {
//		return CookOfferTbl;
//	}
//
//	public void setCookOfferTbl(CookOfferTblEntity cookOfferTbl) {
//		CookOfferTbl = cookOfferTbl;
//	}
//
//	public UserTblEntity getCookUserTbl() {
//		return cookUserTbl;
//	}
//
//	public void setCookUserTbl(UserTblEntity cookUserTbl) {
//		this.cookUserTbl = cookUserTbl;
//	}
//	
//	
//	
//	
//
//}
