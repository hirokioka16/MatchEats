package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.CookingInfoDto;
import com.example.demo.dto.LoginInfoDto;
import com.example.demo.service.CookingOfferService;
import com.example.demo.service.PayService;

@Controller
public class ReactionController {
	
	@Autowired
	CookingOfferService cookOfferService;
	@Autowired
	HttpSession session;
	@Autowired
	private PayService payService;
	
	@RequestMapping(value= {"/reactionList"}, method=RequestMethod.GET)
	public String getList(Model model) {
		
		//userId取得
//		session.getAttribute("loginInfo");
//		int userId = loginInfo.getUserId();
		
		LoginInfoDto loginInfo  = (LoginInfoDto)session.getAttribute("loginInfo");
		
		List<CookingInfoDto> reactionList = cookOfferService.getReactionList(loginInfo.getUserId()); //.getReactionList(userId)
		
		model.addAttribute("reactionList",reactionList);
		
		return "reactionList";
	}
	
	@RequestMapping(value= {"/reject"}, method=RequestMethod.GET)
	public String reject(@RequestParam("offerId") int offerId) {
		
		cookOfferService.approvalOffer(offerId);
		
		return "redirect:/reactionList";
	}
		

}
