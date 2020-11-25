package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="assessment")
public class AssessmentTblEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer assessmentId;

	@ManyToOne
	@JoinColumn(name="history_id")
	private HistoryTblEntity historyTbl;

	private String point;

	private String aComment;

	private Date assessmentDate;

	public Integer getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Integer assessmentId) {
		this.assessmentId = assessmentId;
	}

	public HistoryTblEntity getHistoryTbl() {
		return historyTbl;
	}

	public void setHistoryTbl(HistoryTblEntity historyTbl) {
		this.historyTbl = historyTbl;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getaComment() {
		return aComment;
	}

	public void setaComment(String aComment) {
		this.aComment = aComment;
	}

	public Date getAssessmentDate() {
		return assessmentDate;
	}

	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}








}
