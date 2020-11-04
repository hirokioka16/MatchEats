package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name="users")
public class UserTblEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	private String userName;
	
	private String nickname;
	
	private String userMail;
	
	private String userPass;
	
	private String postalCode;
	
	private String userAddres;
	
	private String userTel;
	
	private Date userBirth;
	
	private String userCard;
	
	private String cardName;
	
	private String secureCode;
	
	private String limitDate;
	
	private String accountType;
	
	private Integer sales;
	
	private float assessment;
	
	private String iconPass;
	
	@OneToMany(mappedBy="userTbl")
	private List<FoodTblEntity> foodUser;
	
	

	public List<FoodTblEntity> getFoodUser() {
		return foodUser;
	}

	public void setFoodUser(List<FoodTblEntity> foodUser) {
		this.foodUser = foodUser;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickname;
	}

	public void setNickName(String nickName) {
		this.nickname = nickName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getUserAdress() {
		return userAddres;
	}

	public void setUserAdress(String userAddres) {
		this.userAddres = userAddres;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public Date getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserCard() {
		return userCard;
	}

	public void setUserCard(String userCard) {
		this.userCard = userCard;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getSecureCode() {
		return secureCode;
	}

	public void setSecureCode(String secureCode) {
		this.secureCode = secureCode;
	}

	public String getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public float getAssessMent() {
		return assessment;
	}

	public void setAssessMent(float assessment) {
		this.assessment = assessment;
	}

	public String getIconPass() {
		return iconPass;
	}

	public void setIconPass(String iconPass) {
		this.iconPass = iconPass;
	}
	
	
	
	

}
