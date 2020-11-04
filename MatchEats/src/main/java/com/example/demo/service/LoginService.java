package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginInfoDto;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	public LoginInfoDto login(String mail,String pass)throws Exception{
	LoginInfoDto loginInfoDto = null;

	userRepository entity = userRepository.login(mail,pass);

	if(entity != null) {
		loginInfoDto = new LoginInfoDto();
		loginInfoDto.setEmail(entity.getAdminEmail());
		loginInfoDto.setPass(entity.getAdminPass());
	}

	return loginInfoDto;
	}

}
