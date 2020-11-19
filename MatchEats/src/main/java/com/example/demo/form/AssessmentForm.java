package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AssessmentForm {

	@NotBlank(message = "点数を選択して下さい")
	private int point;

	@NotBlank(message = "コメントを入力してください")
	@Size(max = 200, message = "コメントは200文字以内で入力してください")
	private String a_comment;



	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getA_comment() {
		return a_comment;
	}

	public void setA_comment(String a_comment) {
		this.a_comment = a_comment;
	}

}
