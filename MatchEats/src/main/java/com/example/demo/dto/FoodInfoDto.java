package com.example.demo.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;



public class FoodInfoDto {

	private String foodName;
	private String requestOutline;
	private Date registDate;
	private int genreId;
	private String eatFlag;
	private MultipartFile requestPicture;


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
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	public String getEatFlag() {
		return eatFlag;
	}
	public void setEatFlag(String eatFlag) {
		this.eatFlag = eatFlag;
	}
	public MultipartFile getRequestPicture() {
		return requestPicture;
	}
	public void setRequestPicture(MultipartFile requestPicture) {
		this.requestPicture = requestPicture;
	}



}