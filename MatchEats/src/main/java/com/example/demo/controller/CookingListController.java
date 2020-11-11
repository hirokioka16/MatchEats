package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.FoodInfoDto;
import com.example.demo.service.CookingOfferService;

@Controller
public class CookingListController {

@Autowired
private CookingOfferService cookService;

@GetMapping(value="/cooking/list")
public String displayList(Model model) {

	List<FoodInfoDto> CookingList=cookService.getList();

	//List<CookingInfoDto>cookinglist = FoodService.searchAll();
	//model.addAttribute("cokkinglist","cookinglist");

	model.addAttribute("CookingList", CookingList);
	return "my_cook_list";

}

}
