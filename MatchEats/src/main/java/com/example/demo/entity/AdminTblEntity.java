package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name="admin")
public class AdminTblEntity implements Serializable{

	
	@Id
	private Integer adminId;
	
	private String adminName;
	
	private String adminMail;
	
	private String adminPass;
	
	@OneToMany(mappedBy="adminTbl")
	private List<HistoryTblEntity> historyList;
	
	


	public List<HistoryTblEntity> getHistoryList() {
		return historyList;
	}

	public void setHistoryList(List<HistoryTblEntity> historyList) {
		this.historyList = historyList;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminMail() {
		return adminMail;
	}

	public void setAdminMail(String adminMail) {
		this.adminMail = adminMail;
	}

	public String getAdminPass() {
		return adminPass;
	}

	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}
	
	
}
