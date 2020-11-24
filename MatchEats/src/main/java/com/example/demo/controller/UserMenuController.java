package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.FoodTblEntity;
import com.example.demo.service.FoodService;

@Controller
public class UserMenuController {

	@Autowired
	FoodService foodService;


	  // このURLにアクセスした際の動作
	  @RequestMapping(value= {"/menu"})
	  public String list(Model model){
	    List<FoodTblEntity> foodlist = foodService.searchAll();
	    model.addAttribute("foodlist", foodlist);
	    return "menu";
	  }
}
