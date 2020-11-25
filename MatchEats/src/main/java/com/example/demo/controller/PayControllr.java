package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.form.StripeForm;
import com.example.demo.service.PayService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;



@Controller
public class PayControllr {
	
	@Autowired
	private PayService payService;
	
	   @RequestMapping(value = {"/pay"},method=RequestMethod.GET)
	   public String checkout(Model model) {

		
		//料理名、料理の説明、金額をDBからとってくる
		   
		
		model.addAttribute("amount", 100);//in cents
		model.addAttribute("foodName", "テスト商品名");
		model.addAttribute("content", "テスト料理説明");
        model.addAttribute("stripePublicKey", "");
        model.addAttribute("currency", "jpy");
	       return "stripe_checkout";
	   }
	   
	   @RequestMapping(value = {"/charge"},method=RequestMethod.POST)
	    public String charge(StripeForm stripeForm, Model model)throws StripeException {
	    	stripeForm.setDescription("Example charge");
	        Charge charge = payService.charge(stripeForm);
	       
	        return "pay_complete";
	    }
	   
	   @ExceptionHandler(StripeException.class)
	    public String handleError(Model model, StripeException ex) {
	        model.addAttribute("error", ex.getMessage());
	        return "pay_complete";
	    }
}
