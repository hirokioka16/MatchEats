package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AssessmentInfoDto;
import com.example.demo.dto.DeliveryInfoDto;
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

	//料理の回収完了登録をする
	public void cookCollection(int offerId,int adminId,Date date) {

		AdminTblEntity entity = new AdminTblEntity();
		entity.setAdminId(adminId);

		historyRepository.cookCollectionUpdate(entity,offerId,date);
		cookRepository.changeApprovalDeliveryStatus("2",offerId);
	}

	//自分が配達する料理の一覧
	public List<DeliveryInfoDto> mydeliverylist(int adminId) {

		AdminTblEntity adminEntity = new AdminTblEntity();
		adminEntity.setAdminId(adminId);

		List<HistoryTblEntity> HistoryList = historyRepository.getDeleveryUserInfo(adminEntity);
		List<DeliveryInfoDto> resultList = new ArrayList<DeliveryInfoDto>();

		for(HistoryTblEntity entity: HistoryList) {

			DeliveryInfoDto dto = new DeliveryInfoDto();
			dto.setHistoryId(entity.getHistoryId());
			dto.setUserName(entity.getRequestUser().getUserName());
			dto.setRequestDay(entity.getRecoveryDate());
			dto.setAddress(entity.getRequestUser().getUserAdress());

			resultList.add(dto);
		}

		return resultList;
	}

	//historyIdから料理ユーザー情報を取得する
	public DeliveryInfoDto getDeliveryInfo(int historyId) {

		DeliveryInfoDto dto = new DeliveryInfoDto();

		HistoryTblEntity entity = historyRepository.getOne(historyId);

		dto.setHistoryId(entity.getHistoryId());
		dto.setUserName(entity.getRequestUser().getUserName());
		dto.setRequestDay(entity.getRecoveryDate());
		dto.setAddress(entity.getRequestUser().getUserAdress());

		return dto;
	}

	//料理の配達完了登録をする
	public void deliveryComplete(int historyId,Date date) {

		historyRepository.cookDeliveryUpdate(historyId,date);
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

	public List<HistoryInfoDto> getHistory(){

		List<HistoryInfoDto> list = new ArrayList<HistoryInfoDto>();
		List<HistoryTblEntity> entityList= historyRepository.findAll();

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

	public HistoryInfoDto getHistoryInfo(int historyId) {

		HistoryTblEntity en = historyRepository.getOne(historyId);
		HistoryInfoDto dto = new HistoryInfoDto();

		dto.setCookUserName(en.getCookOfferUser().getUserName());
		dto.setCookUserMail(en.getCookOfferUser().getUserMail());


		return dto;
	}

	public List<HistoryInfoDto> getAll(){

		List<HistoryInfoDto> allList = new ArrayList<HistoryInfoDto>();
		List<HistoryTblEntity> entityList = historyRepository.findAll();

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

			allList.add(dto);
		}

		return allList;


	}

}

