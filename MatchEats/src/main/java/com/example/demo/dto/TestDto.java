package com.example.demo.dto;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.entity.GenreTblEntity;
import com.example.demo.entity.UserTblEntity;

public class TestDto {
	
	private Integer genreId;
	
	private String genreName;
	
	
	private Integer userId;
	
	private String foodName;
	
	private String requestOutline;
	
	private Date registDate;
	
	
	private String eatFlag;
	
	private String requestPicture;

	public Integer getGenreId() {
		return genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
