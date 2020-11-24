package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AssessmentInfoDto;
import com.example.demo.dto.HistoryInfoDto;
import com.example.demo.entity.AdminTblEntity;
import com.example.demo.entity.AssessmentTblEntity;
import com.example.demo.entity.HistoryTblEntity;
import com.example.demo.repository.AssessmentRepository;
import com.example.demo.repository.CookingOfferRepository;
import com.example.demo.repository.HistoryRepository;

@Service
public class HistoryService {

	@Autowired
	HistoryRepository historyRepository;
	@Autowired
	AssessmentRepository assessmentRepository;
	@Autowired
	CookingOfferRepository cookRepository;

	//食事履歴取得
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

	//調理履歴取得
	public List<HistoryInfoDto> getCookList(int userId){

		List<HistoryInfoDto> list = new ArrayList<HistoryInfoDto>();
		List<HistoryTblEntity> entityList = historyRepository.getCookList(userId);


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

	//評価登録メソッド
	public void insert(AssessmentInfoDto dto) {

		AssessmentTblEntity assessmentEntity = change(dto);

		assessmentRepository.saveAndFlush(assessmentEntity);
	}
	
	//料理の回収登録をする
	public void cookCollection(int offerId,int adminId) {
		
		AdminTblEntity entity = new AdminTblEntity();
		entity.setAdminId(adminId);
		
		historyRepository.cookCollectionUpdate(entity,offerId);
		cookRepository.changeApprovalDeliveryStatus("2",offerId);
	}

	private AssessmentTblEntity change(AssessmentInfoDto dto) {

		AssessmentTblEntity assessmentEntity = new AssessmentTblEntity();

		HistoryTblEntity historyEntity = new HistoryTblEntity();
		historyEntity.setHistoryId(dto.getHistoryId());
		assessmentEntity.setHistoryTbl(historyEntity);

		assessmentEntity.setPoint(String.valueOf(dto.getPoint()));
		assessmentEntity.setaComment(dto.getAssessmentComment());
		assessmentEntity.setAssessmentDate(dto.getAssessmentDate());

		return assessmentEntity;

	}


}

