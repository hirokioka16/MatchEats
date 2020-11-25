package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CookingInfoDto;
import com.example.demo.entity.CookOfferTblEntity;
import com.example.demo.entity.FoodTblEntity;
import com.example.demo.entity.UserTblEntity;
import com.example.demo.repository.CookingOfferRepository;

@Service
public class CookingOfferService {

	@Autowired
	CookingOfferRepository cookingOfferRepository;

	public void insert(CookingInfoDto dto) {

		CookOfferTblEntity offerEntity = change(dto);

		cookingOfferRepository.saveAndFlush(offerEntity);
	}
	
	
	//リアクション一覧を取得する
	public List<CookingInfoDto> getReactionList(int userId) {
		
		
		List<CookingInfoDto> reactionList = new ArrayList<CookingInfoDto>();
		List<CookOfferTblEntity> entityList = cookingOfferRepository.getReactionList(userId);
		
		for(CookOfferTblEntity entity:entityList) {
			
			CookingInfoDto dto = new CookingInfoDto();
			dto.setUserId(String.valueOf(entity.getUserTbl().getUserId()));
			dto.setUserName(entity.getUserTbl().getUserName());
			dto.setOfferId(String.valueOf(entity.getOfferId()));
			dto.setPrice(entity.getPrice());
			dto.setOfferComment(entity.getOfferComment());
			
			reactionList.add(dto);
		}
		
		return reactionList;
		
	}
	
	
	
	

	private CookOfferTblEntity change(CookingInfoDto dto) {

		CookOfferTblEntity cookOfferEntity = new CookOfferTblEntity();

			cookOfferEntity.setPrice(dto.getPrice());
			cookOfferEntity.setOfferComment(dto.getOfferComment());
			cookOfferEntity.setOfferDate(dto.getOfferDate());
			cookOfferEntity.setReactionStatus(dto.getReactionStatus());
			cookOfferEntity.setDeliveryFlg(dto.isDeliveryFlg());

			FoodTblEntity foodEntity = new FoodTblEntity();
			foodEntity.setRequestId(Integer.parseInt(dto.getRequestId()));
			cookOfferEntity.setFoodTbl(foodEntity);

			UserTblEntity userEntity = new UserTblEntity();
			userEntity.setUserId(Integer.parseInt(dto.getUserId()));
			cookOfferEntity.setUserTbl(userEntity);

		return cookOfferEntity;

	}



	//オファー送信履歴確認
	public List<CookingInfoDto> getOfferHistory(int userId){

		List<CookingInfoDto> list = new ArrayList<CookingInfoDto>();
		List<CookOfferTblEntity> entityList = cookingOfferRepository.getOfferHistory(userId);

		for(CookOfferTblEntity entity:entityList) {
			CookingInfoDto dto = new CookingInfoDto();
			dto.setOfferId(String.valueOf(entity.getOfferId()));
			dto.setRequestId(String.valueOf(entity.getFoodTbl().getRequestId()));
			dto.setUserId(String.valueOf(entity.getUserTbl().getUserId()));
			dto.setPrice(entity.getPrice());
			dto.setOfferComment(entity.getOfferComment());


			list.add(dto);
		}

		return list;

	}


	//オファー情報取得用メソッド
	public CookingInfoDto getOfferInfo(int offerId) {
		CookOfferTblEntity entity = cookingOfferRepository.getOne(offerId);
		CookingInfoDto dto = new CookingInfoDto();

		dto.setOfferId(String.valueOf(entity.getOfferId()));
		dto.setRequestId(String.valueOf(entity.getFoodTbl().getRequestId()));
		dto.setUserId(String.valueOf(entity.getUserTbl().getUserId()));
		dto.setPrice(entity.getPrice());
		dto.setOfferComment(entity.getOfferComment());
		dto.setOfferDate(entity.getOfferDate());
		dto.setDeliveryFlg(entity.getDeliveryFlg());
		dto.setDeliveryRequestDate(entity.getDeliveryRequestDate());
		dto.setReactionStatus(entity.getReactionStatus());
		dto.setReactionComment(entity.getReactionComment());
		dto.setReactionDate(entity.getReactionDate());

		return dto;

	}

	public void delete(String offerId) {

		CookOfferTblEntity offerEntity = new CookOfferTblEntity();
		offerEntity = cookingOfferRepository.getOne(Integer.parseInt(offerId));

		offerEntity.setReactionStatus("4");



		cookingOfferRepository.save(offerEntity);
	}

}
