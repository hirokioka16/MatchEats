package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CookingInfoDto;
import com.example.demo.entity.CookOfferTblEntity;
import com.example.demo.repository.CookingOfferRepository;

@Service
public class CookingOfferService {

	@Autowired
	CookingOfferRepository cookingOfferRepository;

	public void insert(CookingInfoDto dto) {

		CookOfferTblEntity offerEntity = change(dto);

		cookingOfferRepository.saveAndFlush(offerEntity);
	}

	private CookOfferTblEntity change(CookingInfoDto dto) {

		CookOfferTblEntity cookOfferEntity = new CookOfferTblEntity();
			cookOfferEntity.setPrice(dto.getPrice());
			cookOfferEntity.setOfferComment(dto.getOfferComment());
			cookOfferEntity.setOfferDate(dto.getOfferDate());
			cookOfferEntity.setReactionStatus(dto.getReactionStatus());
			cookOfferEntity.setDeliveryFlg(dto.isDeliveryFlg());

		return cookOfferEntity;

	}

/**
	public List<CookingInfoDto> getOfferHistory(int userId){

		CookOfferTblEntity entity = CookingOfferRepository.getOfferHistory(userId);
		CookingInfoDto dto = new CookingInfoDto();

		dto.setOfferId(entity.getOfferId());
		dto.setRequestId(entity.getFoodTbl().getRequestId());
		dto.setUserId(entity.getUserTbl().getUserId());
		dto.setPrice(entity.getPrice());


	}
**/
}
