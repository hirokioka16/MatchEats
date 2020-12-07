package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.LoginInfoDto;
import com.example.demo.service.UserServise;

@Controller
@RequestMapping(value = {"/deleteUserEntry"})
public class DeleteUserEntryController {

	@Autowired
	UserServise userService;
	@Autowired
	HttpSession session;
	@Autowired
	InputUserEntryController inputUserEntry;



	@RequestMapping(value= {"/confirm"}, method=RequestMethod.GET)
	public String confirm() {

		return "confirm_userDelete";
	}


	@RequestMapping(value= {"/delete"}, method=RequestMethod.POST)
	public String delete() throws java.text.ParseException {

		//本番
		LoginInfoDto loginInfo = (LoginInfoDto)session.getAttribute("loginInfo");

		userService.delete(loginInfo.getUserId());
		session.removeAttribute("loginInfo");

		return "redirect:/deleteUserEntry/complete";


	}


	@RequestMapping(value= {"/complete"}, method=RequestMethod.GET)
	public String complete() {
		return "complete_userDelete";
	}



}




