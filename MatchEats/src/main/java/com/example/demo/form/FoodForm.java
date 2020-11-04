package com.example.demo.form;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class FoodForm {
	
	@NotBlank(message = "料理名を入力してください")
	@Size(max=50,message = "料理名は50文字以内に入力してください")
	private String foodName;
	@NotBlank(message = "料理の説明を入力してください")
	@Size(max=200,message = "料理の説明は200文字以内に入力してください")
	private String requestOutline;
	@NotBlank(message = "料理のジャンルを選択してください")
	private String genreId;
//	@NotBlank(message = "受付状況を選択してください")
//	@Size(max=1,message = "受付状況を選択してください")
	private String eatFlag;
	
	private MultipartFile requestPicture;
	
	private String fileName;
	
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
	public String getGenreId() {
		return genreId;
	}
	public void setGenreId(String genreId) {
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	

}
