package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CookingInfoDto;
import com.example.demo.dto.FoodInfoDto;
import com.example.demo.entity.CookOfferTblEntity;
import com.example.demo.entity.FoodTblEntity;
import com.example.demo.repository.CookingRepository;

@Service
public class CookingOfferService {

	@Autowired
	private CookingRepository cookingRepository;

	public  List<FoodInfoDto> getList(){
		List<CookingInfoDto> list = new ArrayList<CookingInfoDto>();
		 List<CookOfferTblEntity> tblList = cookingRepository.getList();


		 List<FoodTblEntity> resultList =  new ArrayList<FoodTblEntity>();
		 List<FoodInfoDto> resultListDto = new ArrayList<FoodInfoDto>();
		 for(int i=0;i<tblList.size();i++) {
			resultList.add(tblList.get(i).getFoodTbl());

		 }

		 for(FoodTblEntity entity:resultList) {

				FoodInfoDto dto = new FoodInfoDto();
				dto.setFoodName(entity.getFoodName());
				dto.setRegistDate(entity.getRegistDate());
				dto.setPictureName(entity.getRequestPicture());

				resultListDto.add(dto);


			}

		return resultListDto;
	}

}
