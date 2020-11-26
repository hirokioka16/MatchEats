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
import com.example.demo.service.PayService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;



@Controller
public class PayControllr {
	
	@Autowired
	private PayService payService;
	
	
	   @RequestMapping(value = {"/pay"},method=RequestMethod.GET)
	   public String payConfirm(@RequestParam("offerId") int offerId,Model model) {
		
		   CookingInfoDto dto = payService.getFoodInfo(offerId);
		   
		   	dto.setOfferId(offerId);
		   	model.addAttribute("stripePublicKey", "");
	        model.addAttribute("currency", "jpy");
			model.addAttribute("dto", dto);
		return "detail_pay";
	   }
	   
	   @RequestMapping(value = {"/charge"},method=RequestMethod.POST)
	    public String charge(StripeForm stripeForm,@RequestParam("amount") int price,@RequestParam("offerId") int offerId,Model model)throws StripeException {
	    	stripeForm.setDescription("Example charge");
	        payService.charge(stripeForm,offerId);
	       
	        return "pay_complete";
	    }
	   
	   @ExceptionHandler(StripeException.class)
	    public String handleError(Model model, StripeException ex) {
	        model.addAttribute("error", ex.getMessage());
	        return "pay_complete";
	    }
}
