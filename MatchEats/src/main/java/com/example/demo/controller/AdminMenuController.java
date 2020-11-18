package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value= {"/adminMenu"})
public class AdminMenuController {
	
	@RequestMapping(value= {"/menu"}, method=RequestMethod.GET)
	public String adminMenu() {
		return "adminMenu";
		
	}
	

}
