package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.FoodInfoDto;
import com.example.demo.dto.GenreInfoDto;
import com.example.demo.dto.LoginInfoDto;
import com.example.demo.service.FoodService;

@Controller
public class EachGenreFoodList {

	@Autowired
	FoodService foodService;

	@Autowired
	HttpSession session;

	@RequestMapping(value= {"/eachgenre"}, method=RequestMethod.POST)
	public String top(@RequestParam("genreId") int genreId,Model model) {

		//料理のジャンルをDBから取得
				List<GenreInfoDto> list = foodService.getGenre();

				List<FoodInfoDto> firstList = foodService.topDetailFoodList(list.get(genreId).getGenreId());

				boolean loginFlg = false;
				LoginInfoDto loginInfo  = (LoginInfoDto)session.getAttribute("loginInfo");
				if(loginInfo !=null) {
					loginFlg = true;
				}
				model.addAttribute("loginFlg",loginFlg);
				model.addAttribute("firstList",firstList);

		return "each_genre_foodlist";
	}
}
