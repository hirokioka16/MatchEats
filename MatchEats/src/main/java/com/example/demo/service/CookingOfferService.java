package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CookingInfoDto;
import com.example.demo.entity.CookOfferTblEntity;
import com.example.demo.repository.CookingRepository;

@Service
public class CookingOfferService {

	@Autowired
	private CookingRepository cookingRepository;

	public  List<CookingInfoDto> getList(){
		List<CookingInfoDto> list = new ArrayList<CookingInfoDto>();
		 List<CookOfferTblEntity> tblList = cookingRepository.getList();


		return null;
	}

}
