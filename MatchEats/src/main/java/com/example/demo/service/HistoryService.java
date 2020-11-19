package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.HistoryInfoDto;
import com.example.demo.entity.HistoryTblEntity;
import com.example.demo.repository.HistoryRepository;

@Service
public class HistoryService {

	@Autowired
	HistoryRepository historyRepository;


	public List<HistoryInfoDto> getFoodlist(int userId){

		List<HistoryInfoDto> list = new ArrayList<HistoryInfoDto>();
		List<HistoryTblEntity> entityList= historyRepository.getFoodList(userId);

		for(HistoryTblEntity entity:entityList) {

			HistoryInfoDto dto = new HistoryInfoDto();
			dto.setHistoryId(entity.getHistoryId());
			dto.setOfferId(entity.getCookOfferTbl().getOfferId());

			if(entity.getAdminTbl() != null) {
				dto.setAdminId(entity.getAdminTbl().getAdminId());
			}
			dto.setRecoveryDate(entity.getRecoveryDate());
			dto.setDeliveryCompleteDate(entity.getDeliveryCompleteDate());
			dto.setStateStatus(entity.getStateStatus());
			dto.setAdminProfit(entity.getAdminProfit());
			dto.setCookProfit(entity.getCookProfit());
			dto.setCookUserId(entity.getCookOfferUser().getUserId());
			dto.setRequestUserId(entity.getRequestUser().getUserId());

			list.add(dto);
		}

		return list;

	}


	//食べたいものの名称を取得するメソッド
	public String backFoodName(int offerId) {

		String foodName = historyRepository.backFoodName(offerId);

		return foodName;

	}

	//履歴の詳細を取得
	public HistoryInfoDto getEatInfo(int offerId) {

		HistoryTblEntity entity = historyRepository.getOne(offerId);
		HistoryInfoDto dto = new HistoryInfoDto();

		dto.setHistoryId(entity.getHistoryId());
		dto.setOfferId(entity.getCookOfferTbl().getOfferId());

		if(entity.getAdminTbl() != null) {
			dto.setAdminId(entity.getAdminTbl().getAdminId());
		}
		dto.setRecoveryDate(entity.getRecoveryDate());
		dto.setDeliveryCompleteDate(entity.getDeliveryCompleteDate());
		dto.setStateStatus(entity.getStateStatus());
		dto.setAdminProfit(entity.getAdminProfit());
		dto.setCookProfit(entity.getCookProfit());
		dto.setCookUserId(entity.getCookOfferUser().getUserId());
		dto.setRequestUserId(entity.getRequestUser().getUserId());



		return dto;
	}
}

