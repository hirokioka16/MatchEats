package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 
@Table(name="eatlist")
public class FoodTblEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer requestId;
	@OneToOne
    @JoinColumn(name="userId")
	private UserTblEntity userTbl;
	
	private String foodName;
	
	private String requestOutline;
	
	private Date registDate;
	
	@OneToOne
    @JoinColumn(name="genreId")
	private GenreTblEntity genreTbl;
	
	private String eatFlag;
	
	private String requestPicture;

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public UserTblEntity getUserTbl() {
		return userTbl;
	}

	public void setUserTbl(UserTblEntity userTbl) {
		this.userTbl = userTbl;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getRequestOutline() {
		return requestOutline;
	}

	public void setRequestOutline(String requestOutline) {
		this.requestOutline = requestOutline;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public GenreTblEntity getGenreTbl() {
		return genreTbl;
	}

	public void setGenreTbl(GenreTblEntity genreTbl) {
		this.genreTbl = genreTbl;
	}

	public String getEatFlag() {
		return eatFlag;
	}

	public void setEatFlag(String eatFlag) {
		this.eatFlag = eatFlag;
	}

	public String getRequestPicture() {
		return requestPicture;
	}

	public void setRequestPicture(String requestPicture) {
		this.requestPicture = requestPicture;
	}
	
	
	
	

}
