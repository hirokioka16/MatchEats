package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CookingInfoDto;
import com.example.demo.entity.CookOfferTblEntity;
import com.example.demo.entity.HistoryTblEntity;
import com.example.demo.form.StripeForm;
import com.example.demo.repository.CookingOfferRepository;
import com.example.demo.repository.HistoryRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Service
public class PayService {
	
	@Autowired
	CookingOfferRepository cookingOfferRepository;
	
	@Autowired
	HistoryRepository historyRepository;
	
	public CookingInfoDto getFoodInfo(int offerId) {
		
		CookingInfoDto dto = new CookingInfoDto();
		CookOfferTblEntity entity = cookingOfferRepository.getOne(offerId);
		
		dto.setFoodName(entity.getFoodTbl().getFoodName());
		dto.setPrice(entity.getPrice());
		dto.setOfferComment(entity.getOfferComment());
		dto.setImgName(entity.getFoodTbl().getRequestPicture());
		dto.setCookContent(entity.getFoodTbl().getRequestOutline());
		
		return dto;
	}
	
	public void charge(StripeForm stripeForm,int offerId){
	Stripe.apiKey = "";

    Map<String, Object> chargeMap = new HashMap<String, Object>();
    chargeMap.put("amount", stripeForm.getAmount());
    chargeMap.put("description", stripeForm.getDescription());
    chargeMap.put("currency", "jpy");
    chargeMap.put("source", stripeForm.getStripeToken());
    
    CookingInfoDto dto = getFoodInfo(offerId);
    
    try {
		Charge.create(chargeMap);
		
	    //reaction_statusを"2"(決済済み)に設定するRepositoryを書く
	   cookingOfferRepository.rejectOffer(offerId);
	    
	} catch (StripeException e) {
		e.printStackTrace();
	}catch (Exception e) {
		e.printStackTrace();
	}

    
  
	}
	
	public HistoryTblEntity change(CookingInfoDto dto) {
		
		HistoryTblEntity en = new HistoryTblEntity();
		CookOfferTblEntity cook = new CookOfferTblEntity();
		
		cook.setOfferId(dto.getOfferId());
		
		en.setCookOfferTbl(cook);
		
		return en;
	}
}
