package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ContactForm {


	@NotBlank(message = "内容を入力してください")
	@Size(max = 200, message = "200文字以内で入力してください")
//	@Pattern(regexp = "[\\w\\-._]+@[\\w\\-._]+\\.[A-Za-z]+", message = "半角英数字で入力してください")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
	this.content = content;
	}
}
