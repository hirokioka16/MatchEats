

package com.example.demo.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.DeliveryController;
import com.example.demo.dto.CookingInfoDto;
import com.example.demo.dto.FoodInfoDto;
import com.example.demo.entity.CookOfferTblEntity;
import com.example.demo.entity.FoodTblEntity;
import com.example.demo.entity.UserTblEntity;
import com.example.demo.repository.CookingOfferRepository;
import com.example.demo.repository.CookingRepository;

@Service
public class CookingOfferService {

	@Autowired
	CookingRepository cookingRepository;

	@Autowired
	CookingOfferRepository cookingOfferRepository;


	public void insert(CookingInfoDto dto) {

		CookOfferTblEntity offerEntity = change(dto);

		cookingRepository.saveAndFlush(offerEntity);
	}


	//リアクション一覧を取得する
	public List<CookingInfoDto> getReactionList(int userId) {


		List<CookingInfoDto> reactionList = new ArrayList<CookingInfoDto>();
		List<CookOfferTblEntity> entityList = cookingOfferRepository.getReactionList(userId);

		for(CookOfferTblEntity entity:entityList) {

			CookingInfoDto dto = new CookingInfoDto();
			dto.setUserId(String.valueOf(entity.getUserTbl().getUserId()));
			dto.setUserName(entity.getUserTbl().getUserName());
			dto.setOfferId(entity.getOfferId());
			dto.setPrice(entity.getPrice());
			dto.setOfferComment(entity.getOfferComment());
			dto.setFoodName(entity.getFoodTbl().getFoodName());
			dto.setImgName(entity.getFoodTbl().getRequestPicture());
			dto.setCookContent(entity.getFoodTbl().getRequestOutline());
			
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
			dto.setOfferId(entity.getOfferId());
			dto.setRequestId(String.valueOf(entity.getFoodTbl().getRequestId()));
			dto.setUserId(String.valueOf(entity.getUserTbl().getUserId()));
			dto.setPrice(entity.getPrice());
			dto.setOfferComment(entity.getOfferComment());
			dto.setOfferDate(entity.getOfferDate());
			dto.setFoodName(entity.getFoodTbl().getFoodName());


			list.add(dto);
		}

		return list;

	}


	//オファー情報取得用メソッド
	public CookingInfoDto getOfferInfo(int offerId) {
		CookOfferTblEntity entity = cookingRepository.getOne(offerId);
		CookingInfoDto dto = new CookingInfoDto();

		dto.setOfferId(entity.getOfferId());
		dto.setRequestId(String.valueOf(entity.getFoodTbl().getRequestId()));
		dto.setUserId(String.valueOf(entity.getUserTbl().getUserId()));
		dto.setUserName(entity.getUserTbl().getUserName());
		dto.setUserMail(entity.getUserTbl().getUserMail());
		dto.setPrice(entity.getPrice());
		dto.setOfferComment(entity.getOfferComment());
		dto.setOfferDate(entity.getOfferDate());
		dto.setDeliveryFlg(entity.getDeliveryFlg());
		dto.setDeliveryRequestDate(entity.getDeliveryRequestDate());
		dto.setReactionStatus(entity.getReactionStatus());
		dto.setReactionComment(entity.getReactionComment());
		dto.setReactionDate(entity.getReactionDate());
		dto.setRequestUserName(entity.getFoodTbl().getUserTbl().getUserName());
		dto.setRequestUserMail(entity.getFoodTbl().getUserTbl().getUserMail());
		dto.setGenreName(entity.getFoodTbl().getGenreTbl().getGenreName());
		dto.setRequestOutline(entity.getFoodTbl().getRequestOutline());
		dto.setFoodName(entity.getFoodTbl().getFoodName());
		dto.setPictureName(entity.getFoodTbl().getRequestPicture());


		return dto;

	}

	public void delete(String offerId) {

		CookOfferTblEntity offerEntity = new CookOfferTblEntity();
		offerEntity = cookingRepository.getOne(Integer.parseInt(offerId));

		offerEntity.setReactionStatus("4");



		cookingRepository.save(offerEntity);
	}

	//調理リスト
	public  List<FoodInfoDto> getList(int userId){

		 List<CookOfferTblEntity> tblList =cookingRepository.getList(userId);

		 List<FoodInfoDto> resultListDto = new ArrayList<FoodInfoDto>();
		 for(CookOfferTblEntity entity:tblList) {
			 FoodInfoDto dto = new FoodInfoDto();
			 dto.setOfferId(entity.getOfferId());
			 dto.setRequestId(entity.getFoodTbl().getRequestId());
			 dto.setFoodName(entity.getFoodTbl().getFoodName());
			 dto.setRegistDate(entity.getFoodTbl().getRegistDate());
			 dto.setPictureName(entity.getFoodTbl().getRequestPicture());
			 dto.setUserId(entity.getUserTbl().getUserId());
			 dto.setUserName(entity.getUserTbl().getUserName());
			 
			 resultListDto.add(dto);
			 
		 }

		 return resultListDto;

    }
//リスト詳細
	public CookingInfoDto getdetail(int offerId){


		CookOfferTblEntity cookOfferTblEntity = cookingRepository.getOne(offerId);

		CookingInfoDto dto = new CookingInfoDto();

		dto.setOfferId(cookOfferTblEntity.getOfferId());
		dto.setUserName(cookOfferTblEntity.getUserTbl().getUserName());
		dto.setPictureName(cookOfferTblEntity.getFoodTbl().getRequestPicture());
		dto.setFoodName(cookOfferTblEntity.getFoodTbl().getFoodName());
		dto.setOfferDate(cookOfferTblEntity.getOfferDate());
		dto.setOfferComment(cookOfferTblEntity.getOfferComment());
		dto.setPoint(cookOfferTblEntity.getUserTbl().getAssessMent());
		dto.setGenreName(cookOfferTblEntity.getFoodTbl().getGenreTbl().getGenreName());
		dto.setRequestOutline(cookOfferTblEntity.getFoodTbl().getRequestOutline());
	



		return dto;
}
//配達以来をデータベースに登録する
	public void update(String offerId ) throws ParseException {

		CookOfferTblEntity offerEntity = new CookOfferTblEntity();
		offerEntity = cookingRepository.getOne(Integer.parseInt(offerId));


		boolean delivaryflg = true ;
		offerEntity.setDeliveryFlg(delivaryflg);
		DeliveryController del = new DeliveryController();

		cookingRepository.update(offerEntity.getOfferId(),del.getNowDate());


	}
	
	//リアクション拒否
	public void approvalOffer(int offerId) throws ParseException {
		
		DeliveryController del = new DeliveryController();
		cookingOfferRepository.rejectOffer(del.getNowDate(),offerId);
	}


}







