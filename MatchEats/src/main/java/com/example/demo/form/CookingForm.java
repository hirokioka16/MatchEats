package com.example.demo.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CookingForm {


	@NotNull(message = "金額を入力してください")
	@Min(0)
	private  int price;

	@NotBlank(message = "オファーコメントを入力してください")
	@Size(max=200,message = "オファーコメントは200文字以内で入力してください")
	private String offerComment;



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

}
