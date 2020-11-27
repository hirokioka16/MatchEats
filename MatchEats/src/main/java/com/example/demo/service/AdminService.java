package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AdminInfoDto;
import com.example.demo.entity.AdminTblEntity;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public AdminInfoDto login(String mail,String pass)throws Exception{
		AdminInfoDto adminInfo = null;

		AdminTblEntity entity = adminRepository.login(mail,pass);

		if(entity != null) {
			adminInfo = new AdminInfoDto();
			adminInfo.setAdminId(entity.getAdminId());
			adminInfo.setEmail(entity.getAdminMail());
		}

		return adminInfo;
	}

}


