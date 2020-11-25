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
@Table(name="contacts")
public class ContactsTblEntity implements Serializable{

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer contactId;

	@ManyToOne
    @JoinColumn(name="user_id")
	private UserTblEntity userTbl;

	private String content;

	private Date contactDate;


	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public UserTblEntity getUserTbl() {
		return userTbl;
	}

	public void setUserTbl(UserTblEntity userTbl) {
		this.userTbl = userTbl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getContactDate() {
		return contactDate;
	}

	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}
}
