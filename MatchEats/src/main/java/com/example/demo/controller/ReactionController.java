package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.CookingInfoDto;
import com.example.demo.service.CookingOfferService;

@Controller
public class ReactionController {
	
	@Autowired
	CookingOfferService cookOfferService;
	@Autowired
	HttpSession session;
	
	@RequestMapping(value= {"/reactionList"}, method=RequestMethod.GET)
	public String getList(Model model) {
		
		//userId取得
//		session.getAttribute("loginInfo");
//		int userId = loginInfo.getUserId();
		
		
		
	
		List<CookingInfoDto> reactionList = cookOfferService.getReactionList(1); //.getReactionList(userId)
		model.addAttribute("reactionList",reactionList);
		
		return "reactionList";
	}

}
