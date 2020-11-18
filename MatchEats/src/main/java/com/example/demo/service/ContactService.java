package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ContactInfoDto;
import com.example.demo.entity.ContactsTblEntity;
import com.example.demo.entity.UserTblEntity;
import com.example.demo.repository.ContactRepository;


@Service
public class ContactService {

	@Autowired
	ContactRepository contactRepository;

	public void insert(ContactInfoDto dto) {

		ContactsTblEntity contactEntity = change(dto);

		contactRepository.saveAndFlush(contactEntity);

	}

		private ContactsTblEntity change(ContactInfoDto dto) {

			ContactsTblEntity contactEntity = new ContactsTblEntity();
				contactEntity.setContent(dto.getContent());
				contactEntity.setContactDate(dto.getContactDate());
				UserTblEntity userEntity = new UserTblEntity();
	            userEntity.setUserId(dto.getUserId());
	            contactEntity.setUserTbl(userEntity);

			return contactEntity;
		}
}
