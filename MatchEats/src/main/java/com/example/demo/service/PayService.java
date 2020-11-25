package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.form.StripeForm;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Service
public class PayService {
	
	public Charge charge(StripeForm stripeForm)throws StripeException{
	Stripe.apiKey = "";

    Map<String, Object> chargeMap = new HashMap<String, Object>();
    chargeMap.put("amount", stripeForm.getAmount());
    chargeMap.put("description", stripeForm.getDescription());
    chargeMap.put("currency", "jpy");
    chargeMap.put("source", stripeForm.getStripeToken());
    
    //reaction_statusを"3"(決済済み)に設定するRepositoryを書く
    
    
    return Charge.create(chargeMap);
	}
}
