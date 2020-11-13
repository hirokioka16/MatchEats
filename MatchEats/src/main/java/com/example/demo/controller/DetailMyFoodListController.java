package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.FoodInfoDto;
import com.example.demo.service.FoodService;
@Controller
public class DetailMyFoodListController {
	
	@Autowired
	FoodService foodService; 
	@Autowired
	HttpSession session;
	
	@RequestMapping(value= {"/foodlist"}, method=RequestMethod.GET)
	public String getUserList(Model model) {
		
		session.removeAttribute("foodInfDto");
		//session.getAttribute("loginInfo",loginInfo.getUserId)
		//int userId =
		
		//テスト用の値
		List<FoodInfoDto> list = foodService.getMyFoodList(1);
		model.addAttribute("list", list);
		return "my_food_userlist";
	}

}
