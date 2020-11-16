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
		//session.getAttribute("loginInfo", loginInfo);
		 List<CookOfferTblEntity> tblList = cookingRepository.getList(1);


		 List<FoodTblEntity> resultList =  new ArrayList<FoodTblEntity>();
		 List<FoodInfoDto> resultListDto = new ArrayList<FoodInfoDto>();
		 List<Integer> offerId = new ArrayList<Integer>();
		 for(int i=0;i<tblList.size();i++) {
			resultList.add(tblList.get(i).getFoodTbl());
			offerId.add(tblList.get(i).getOfferId());

		 }

		 for(int y=0;y<resultList.size();y++) {
				FoodInfoDto dto = new FoodInfoDto();
				dto.setOfferId(offerId.get(y));
				dto.setRequestId(resultList.get(y).getRequestId());
				dto.setFoodName(resultList.get(y).getFoodName());
				dto.setRegistDate(resultList.get(y).getRegistDate());
				dto.setPictureName(String.valueOf(resultList.get(y).getRequestPicture()));

				resultListDto.add(dto);


			}

		return resultListDto;
	}

}
