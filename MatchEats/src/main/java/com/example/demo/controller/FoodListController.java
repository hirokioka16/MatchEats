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
import com.example.demo.service.FoodService;

@Controller
public class FoodListController {
	@Autowired
	FoodService foodService;
	@Autowired
	HttpSession session;



	@RequestMapping(value= {"/allfoodlist"}, method=RequestMethod.GET)
	public String getAllList(Model model) {

		List<FoodInfoDto> list = foodService.getAllList();

		model.addAttribute("list",list);

		return "allfoodlist";
	}

	@RequestMapping(value= {"/detailfoodlist"}, method=RequestMethod.POST)
	public String detail(@RequestParam("requestId") String requestId,Model model) {

		//エラーは広池プロのやつとマージすると消えます
		FoodInfoDto dto = foodService.getUdFoodList(Integer.parseInt(requestId));

		model.addAttribute("dto",dto);


		return "detailfoodlist";

	}

	@RequestMapping(value= {"/seachfoodlist"}, method=RequestMethod.GET)
	public String seach() {

		return "seachfoodlist";

	}
}
