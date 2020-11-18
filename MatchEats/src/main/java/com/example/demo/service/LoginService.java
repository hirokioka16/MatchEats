
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginInfoDto;
import com.example.demo.entity.UserTblEntity;
import com.example.demo.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	public LoginInfoDto login(String mail,String userid)throws Exception{
	LoginInfoDto loginInfoDto = null;

	UserTblEntity entity = userRepository.login(mail,userid);

	if(entity != null) {
		loginInfoDto = new LoginInfoDto();
		loginInfoDto.setEmail(entity.getUserMail());
		loginInfoDto.setUserId(entity.getUserId());
	}

	return loginInfoDto;
	}

}