package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DeliveryInfoDto;
import com.example.demo.dto.FoodInfoDto;
import com.example.demo.entity.CookOfferTblEntity;
import com.example.demo.entity.FoodTblEntity;
import com.example.demo.repository.CookingOfferRepository;

@Service
public class DeliveryService {
	
	@Autowired
	CookingOfferRepository cookRepository;

	//配達リクエストされた料理の一覧を取得
	public List<DeliveryInfoDto> getRequestList(boolean deliveryFlg){
		
		List<CookOfferTblEntity> entity = cookRepository.getDeliveryList(deliveryFlg);
		
		List<DeliveryInfoDto> list = new ArrayList<DeliveryInfoDto>();
		
		for(int i=0;i<entity.size();i++) {
			
			DeliveryInfoDto dto = new DeliveryInfoDto();
			
			dto.setOfferId(entity.get(i).getOfferId());
			dto.setUserName(entity.get(i).getUserTbl().getUserName());
			dto.setRequestDay(entity.get(i).getDeliveryRequestDate());
		
			list.add(dto);
		}	
		return list;
	}
	
	//配達リクエストで選択された料理の詳細取得
	public DeliveryInfoDto getDetailDeliveryRequest(int requestId) {
		
		CookOfferTblEntity entity = cookRepository.getOne(requestId);
		
		DeliveryInfoDto dto = new DeliveryInfoDto();
		
		dto.setOfferId(entity.getOfferId());
		dto.setUserName(entity.getUserTbl().getUserName());
		dto.setRequestApprovalDay(entity.getApprovalRequestDeliveryDate());
		dto.setAddress(entity.getUserTbl().getUserAdress());
		
		return dto;
	}
	
	//配達リクエスト承認処理
	public void deliveryRequesrInsert(int offerId,Date approvalDate) {
		
		cookRepository.setApprovalDate(approvalDate,offerId);
	}
	
	//配達リクエストが承認された料理の一覧
	public List<DeliveryInfoDto> getRequestAPProvalList(String status){
		
		List<CookOfferTblEntity> entity = cookRepository.getRequestAPProvalList(status);
		
		List<DeliveryInfoDto> list = new ArrayList<DeliveryInfoDto>();
		
		for(int i=0;i<entity.size();i++) {
			
			DeliveryInfoDto dto = new DeliveryInfoDto();
			
			dto.setOfferId(entity.get(i).getOfferId());
			dto.setUserName(entity.get(i).getUserTbl().getUserName());
			dto.setRequestApprovalDay(entity.get(i).getApprovalRequestDeliveryDate());
		
			list.add(dto);
		}	
		return list;
	}
	
}
	
	

