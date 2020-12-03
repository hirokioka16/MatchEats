package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.FoodInfoDto;
import com.example.demo.dto.GenreInfoDto;
import com.example.demo.dto.LoginInfoDto;
import com.example.demo.form.FoodForm;
import com.example.demo.service.FoodService;

@Controller
public class UserTopController {

	@Autowired
	FoodService foodService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value= {"/menu"}, method=RequestMethod.GET)
	public String top(Model model) {
		
		//料理のジャンルをDBから取得
		List<GenreInfoDto> list = foodService.getGenre();
		
		List<FoodInfoDto> firstList = foodService.topDetailFoodList(list.get(0).getGenreId());
		List<FoodInfoDto> secondList = foodService.topDetailFoodList(list.get(1).getGenreId());
		List<FoodInfoDto> thirdList = foodService.topDetailFoodList(list.get(2).getGenreId());
		List<FoodInfoDto> fourthList = foodService.topDetailFoodList(list.get(3).getGenreId());
		
		model.addAttribute("firstList",firstList);
		model.addAttribute("secondList",secondList);
		model.addAttribute("thirdList",thirdList);
		model.addAttribute("fourthList",fourthList);
		
		boolean loginFlg = false;
		LoginInfoDto loginInfo  = (LoginInfoDto)session.getAttribute("loginInfo");
		if(loginInfo !=null) {
			loginFlg = true;
		}
		model.addAttribute("loginFlg",loginFlg);
		return "top";
	}
}
