package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.DeliveryController;
import com.example.demo.dto.CookingInfoDto;
import com.example.demo.dto.LoginInfoDto;
import com.example.demo.entity.CookOfferTblEntity;
import com.example.demo.entity.HistoryTblEntity;
import com.example.demo.entity.UserTblEntity;
import com.example.demo.form.StripeForm;
import com.example.demo.repository.CookingOfferRepository;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.repository.UserRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Service
public class PayService {

	@Autowired
	CookingOfferRepository cookingOfferRepository;

	@Autowired
	HistoryRepository historyRepository;
	
	@Autowired
	HttpSession session;
	
	



	public CookingInfoDto getFoodInfo(int offerId) {

		CookingInfoDto dto = new CookingInfoDto();
		CookOfferTblEntity entity = cookingOfferRepository.getOne(offerId);

		dto.setFoodName(entity.getFoodTbl().getFoodName());
		dto.setPrice(entity.getPrice());
		dto.setOfferComment(entity.getOfferComment());
		dto.setImgName(entity.getFoodTbl().getRequestPicture());
		dto.setCookContent(entity.getFoodTbl().getRequestOutline());
		dto.setUserId(String.valueOf(entity.getUserTbl().getUserId()));
		dto.setOfferDate(entity.getOfferDate());
		dto.setFoodName(entity.getFoodTbl().getFoodName());
		dto.setUserName(entity.getUserTbl().getUserName());
		dto.setUserMail(entity.getUserTbl().getUserMail());

		return dto;
	}

	public void charge(StripeForm stripeForm,int offerId){
		Stripe.apiKey = "sk_test_51HZSIrJjnozX4HQuaJEH2KwtoFV15jNAuHzoMFjAD33VhGCo4bBRHML5c6u29yqwVxkhYEU1SI56nFmoVj5zv7wk00EEGDaZ1p";

	    Map<String, Object> chargeMap = new HashMap<String, Object>();
	    chargeMap.put("amount", stripeForm.getAmount());
	    chargeMap.put("description", stripeForm.getDescription());
	    chargeMap.put("currency", "jpy");
	    chargeMap.put("source", stripeForm.getStripeToken());

	    CookingInfoDto dto = getFoodInfo(offerId);
	    HistoryTblEntity en = change(dto);
	    CookOfferTblEntity cook = new CookOfferTblEntity();
	    cook.setOfferId(offerId);
	    en.setCookOfferTbl(cook);

    try {
		Charge.create(chargeMap);

	    //reaction_statusを"2"(決済済み)に設定するRepositoryを書く
		DeliveryController del = new DeliveryController();
	    cookingOfferRepository.approvalOffer(del.getNowDate(),offerId);
	    historyRepository.saveAndFlush(en);
	   

	} catch (StripeException e) {
		e.printStackTrace();
	}catch (Exception e) {
		e.printStackTrace();
	}
	}


	public HistoryTblEntity change(CookingInfoDto dto) {

		HistoryTblEntity en = new HistoryTblEntity();
		UserTblEntity cookUser = new UserTblEntity();
		UserTblEntity requestUser = new UserTblEntity();

		int adminProfit = dto.getPrice() / 10;
		int cookProfit = dto.getPrice() - adminProfit;
		cookUser.setUserId(Integer.parseInt(dto.getUserId()));
		LoginInfoDto loginInfo  = (LoginInfoDto)session.getAttribute("loginInfo");
		requestUser.setUserId(loginInfo.getUserId());

		en.setAdminProfit(adminProfit);
		en.setCookProfit(cookProfit);
		en.setCookOfferUser(cookUser);
		en.setRequestUser(requestUser);
		en.setStateStatus(0);


		return en;
	}
}
