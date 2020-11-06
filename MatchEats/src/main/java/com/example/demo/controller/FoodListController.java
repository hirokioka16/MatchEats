package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FoodListController {

	@RequestMapping(value= {"/allfoodlist"}, method=RequestMethod.GET)
	public String getAllList() {

		return "allfoodlist";
	}

	@RequestMapping(value= {"/detailfoodlist"}, method=RequestMethod.GET)
	public String detail() {

		return "detailfoodlist";

	}

	@RequestMapping(value= {"/seachfoodlist"}, method=RequestMethod.GET)
	public String seach() {

		return "seachfoodlist";

	}
}
