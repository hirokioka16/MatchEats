package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value= {"/adminmenu"})
public class AdminMenuController {

	@RequestMapping(value= {"/menu"}, method=RequestMethod.POST)
	public String adminMenu() {
		return "adminMenu";

	}


}
