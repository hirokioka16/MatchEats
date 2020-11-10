package com.example.demo.form;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class UserForm {
	
	@NotBlank(message = "メールアドレスを入力してください")
	@Size(max = 100, message = "メールアドレスは100文字以内で入力してください")
	@Pattern(regexp = "[\\w\\-._]+@[\\w\\-._]+\\.[A-Za-z]+", message = "メールアドレスは半角英数字で入力してください")
	private String userMail;
	
	@NotBlank(message = "パスワードを入力してください")
	@Size(max = 20, message = "パスワードは20文字以内で入力してください")
	@Pattern(regexp = "[\\w\\-._]+", message = "パスワードは半角英数字で入力してください")
	private String userPass;
	
	@NotBlank(message = "ニックネームを入力してください")
	@Size(max = 40, message = "ニックネームは40文字以内で入力してください")
	private String nickName;
	
	@NotBlank(message = "名前を入力してください")
	@Size(max = 20, message = "名前は20文字以内で入力してください")
	private String userName;
	
	@NotBlank(message = "電話番号を入力してください")
	@Size(max = 11, message= "電話番号を正しく入力してください")
	@Pattern(regexp = "[\\d]+", message = "電話番号はハイフン抜きの半角数字で入力してください")
	private String userTel;
	
	@NotBlank(message = "郵便番号を入力してください")
	@Size(max = 7, message = "郵便番号を正しく入力してください")
	@Pattern(regexp = "[\\d]+", message = "郵便番号はハイフン抜きの半角数字で入力してください")
	private String postalCode;
	
	@NotBlank(message = "住所を入力してください")
	@Size(max = 100, message = "住所は100文字以内で入力してください")
	private String userAddres;
	
	@NotBlank(message = "誕生年を選択してください")
	private String year;
	@NotBlank(message = "誕生月を選択してください")
	private String month;
	@NotBlank(message = "誕生日を選択してください")
	private String day;
	
	
	@NotBlank(message = "カード名義を入力してください")
	@Size(max = 30, message = "カード名義は30文字以内で入力してください")
	private String cardName;
	
	@NotBlank(message = "クレジットカード番号を入力してください")
	@Size(max = 16, message = "クレジットカード番号は16文字以内で入力してください")
	@Pattern(regexp = "[\\d]+", message = "クレジットカード番号は半角数字で入力してください")
	private String userCard;
	
	@NotBlank(message = "有効期限年を選択してください")
	private String limitYear;
	@NotBlank(message = "有効期限月を選択してください")
	private String limitMonth;
	
	
	@NotBlank(message = "銀行名を入力してください")
	@Size(max = 30, message = "銀行名は30文字以内で入力してください")
	private String bankName;
	
	@NotBlank(message = "口座番号を入力してください")
	@Size(max = 7, message = "口座番号は7桁以内で入力してください")
	@Pattern(regexp = "[\\d]+", message = "口座番号は半角数字で入力してください")
	private String accountNumber;
	
	@NotBlank(message = "支店名を入力してください")
	@Size(max = 30, message = "支店名は30文字以内で入力してください")
	private String branchName;
	
	@NotBlank(message = "口座名義を入力してください")
	@Size(max = 30, message = "口座名義は30文字以内で入力してください")
	private String accountName;
	
	@NotBlank(message = "セキュリティーコードを入力してください")
	@Size(max = 3, message = "セキュリティーコードは3文字以内で入力してください")
	@Pattern(regexp = "[\\d]+", message = "セキュリティーコード半角数字で入力してください")
	private String secureCode;
	
	
	
	//getterとsetter

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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getUserAddres() {
		return userAddres;
	}

	public void setUserAddres(String userAddres) {
		this.userAddres = userAddres;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}


	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getUserCard() {
		return userCard;
	}

	public void setUserCard(String userCard) {
		this.userCard = userCard;
	}

	public String getLimitYear() {
		return limitYear;
	}

	public void setLimitYear(String limitYear) {
		this.limitYear = limitYear;
	}

	public String getLimitMonth() {
		return limitMonth;
	}

	public void setLimitMonth(String limitMonth) {
		this.limitMonth = limitMonth;
	}


	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getSecureCode() {
		return secureCode;
	}

	public void setSecureCode(String secureCode) {
		this.secureCode = secureCode;
	}
	
	
	
	

}
