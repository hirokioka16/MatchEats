package com.example.demo.dto;

import java.util.Date;

public class AssessmentInfoDto {

	private int assessmentId;
	private int historyId;
	private int point;
	private String assessmentComment;
	private Date assessmentDate;
	public int getAssessmentId() {
		return assessmentId;
	}
	public void setAssessmentId(int assessmentId) {
		this.assessmentId = assessmentId;
	}
	public int getHistoryId() {
		return historyId;
	}
	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getAssessmentComment() {
		return assessmentComment;
	}
	public void setAssessmentComment(String assessmentComment) {
		this.assessmentComment = assessmentComment;
	}
	public Date getAssessmentDate() {
		return assessmentDate;
	}
	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}


}
