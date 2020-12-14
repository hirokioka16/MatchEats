package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.CookingInfoDto;
import com.example.demo.form.StripeForm;
import com.example.demo.service.CookingOfferService;
import com.example.demo.service.PayService;
import com.stripe.exception.StripeException;



@Controller
public class PayControllr {

	@Autowired
	private PayService payService;
	@Autowired
	MailTest02Application mail;
	@Autowired
	CookingOfferService cookOfferService;


	   @RequestMapping(value = {"/pay"},method=RequestMethod.GET)
	   public String payConfirm(@RequestParam("offerId") int offerId,Model model) {

		   CookingInfoDto dto = payService.getFoodInfo(offerId);

		   	dto.setOfferId(offerId);
		   	model.addAttribute("stripePublicKey", "pk_test_51HZSIrJjnozX4HQuaoN1pAVnhJ3pwgaEytjzaPwWIbgK5edPnoTMkELL69n40RGw6mdOW6RIqFjIGfsQ6WVos4XB00TEoEImT5");
	        model.addAttribute("currency", "jpy");
			model.addAttribute("dto", dto);
		return "detail_pay";
	   }

	   @RequestMapping(value = {"/charge"},method=RequestMethod.POST)
	    public String charge(StripeForm stripeForm,@RequestParam("amount") int price,@RequestParam("offerId") int offerId,Model model)throws StripeException {
	    	stripeForm.setDescription("Example charge");
	        payService.charge(stripeForm,offerId);
	      //料理オファーをした人に承認されましたと通知メールを送る
			CookingInfoDto dto = cookOfferService.getOfferInfo(offerId);
			mail.sendMail(dto.getUserMail(),"料理オファー結果の通知","おめでとうございます" + dto.getUserName()+"様の料理オファーが承認されました!料理完成の時間に合わせて配達依頼をしてください");

	        return "pay_complete";
	    }

	   @ExceptionHandler(StripeException.class)
	    public String handleError(Model model, StripeException ex) {
	        model.addAttribute("error", ex.getMessage());
	        return "pay_complete";
	    }
}
