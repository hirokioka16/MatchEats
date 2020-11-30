package com.example.demo.controller;

import java.text.ParseException;
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
	@Autowired
	MailTest02Application mail;
	
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
	public String reject(@RequestParam("offerId") int offerId) throws ParseException {
		
		cookOfferService.approvalOffer(offerId);
		//料理オファーをした人に拒否られましたと通知メールを送る
		CookingInfoDto dto = cookOfferService.getOfferInfo(offerId);
		mail.sendMail(dto.getUserMail(),"料理オファー結果の通知","残念ですが" + dto.getUserName()+"様の料理オファーが承認されませんでした");
		
		return "redirect:/reactionList";
	}
		

}
