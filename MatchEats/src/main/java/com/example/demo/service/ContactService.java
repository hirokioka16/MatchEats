package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ContactInfoDto;
import com.example.demo.entity.ContactsTblEntity;
import com.example.demo.entity.UserTblEntity;
import com.example.demo.repository.ContactRepository;

/**
 *
 * お問い合わせ情報 Service
 *
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class ContactService {

	/**
	 * お問い合わせ情報 Repository
	 */
	@Autowired
	 ContactRepository contactRepository;

	/**
	 * お問い合わせ情報 全検索
	 * @return 検索結果
	 */
	public List<ContactInfoDto> searchAll(){

		List<ContactsTblEntity> list = contactRepository.getAll();

		List<ContactInfoDto> dtoList = new ArrayList<ContactInfoDto>();

		for(ContactsTblEntity entity:list) {

			ContactInfoDto dto = new ContactInfoDto();
			dto.setContactId(String.valueOf(entity.getContactId()));
			dto.setUserMail(entity.getUserTbl().getUserMail());
			dto.setContent(entity.getContent());
			dto.setContactDate(entity.getContactDate());
			dto.setUserId(entity.getUserTbl().getUserId());

			dtoList.add(dto);

		}



		return dtoList;
	}


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





