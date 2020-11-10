package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserInfoDto;
import com.example.demo.entity.BankTblEntity;
import com.example.demo.entity.UserTblEntity;
import com.example.demo.repository.UserRepository;

@Service
public class UserServise {
	
	
	@Autowired
	UserRepository userRepository;
	
	
	public void insert(UserInfoDto dto) {
		
		UserTblEntity userEntity = change(dto);
		///BankTblEntity bankEntity = change2(dto);
		userRepository.saveAndFlush(userEntity);
		///userRepository.saveAndFlush(entity)
		
	}
	
	
	// dtoの値をentityに入れるメソッド
	private UserTblEntity change(UserInfoDto dto) {
		
		UserTblEntity userEntity = new UserTblEntity();
		    userEntity.setUserName(dto.getUserName());
		    userEntity.setNickName(dto.getNickName());
		    userEntity.setUserMail(dto.getUserMail());
		    userEntity.setUserPass(dto.getUserPass());
		    userEntity.setPostalCode(dto.getPostalCode());
			userEntity.setUserAdress(dto.getUserAddres());
			userEntity.setUserTel(dto.getUserTel());
			
			//日付変換
			try {
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date userBirth = sdFormat.parse(dto.getUserBirth());
				userEntity.setUserBirth(userBirth);
			
			} catch (ParseException e) {
					e.printStackTrace();
			}

			userEntity.setUserCard(dto.getUserCard());
			userEntity.setCardName(dto.getCardName());
			userEntity.setLimitDate(dto.getLimitDate());
			userEntity.setSecureCode(dto.getSecureCode());
			userEntity.setAccountType("0");
			userEntity.setSales(0);
			userEntity.setAssessMent(0);
			
		
		
			
			
			return userEntity;
		
		
	}
	
	private BankTblEntity change2(UserInfoDto dto) {
		BankTblEntity bankEntity = new BankTblEntity();		
		bankEntity.setBankName(dto.getBankName());
		bankEntity.setAccountNumber(dto.getAccountNumber());
		bankEntity.setBranchName(dto.getBranchName());
		bankEntity.setAccountName(dto.getAccountName());
		
		return bankEntity;
		
	}

}
