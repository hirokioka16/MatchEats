package com.example.demo.controller;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.LoginInfoDto;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.form.UserForm;
import com.example.demo.service.UserServise;

@Controller
public class MyPageController {
	
	@Autowired
	UserServise userService;
	@Autowired
	HttpSession session;
	
	@RequestMapping(value= {"/mypage"}, method=RequestMethod.GET)
	public String inputUpdate(Model model){
		
		LoginInfoDto loginInfo  = (LoginInfoDto)session.getAttribute("loginInfo");
		UserInfoDto dto = userService.getUser(loginInfo.getUserId());
		
		model.addAttribute("userInfoDto", dto);
		return "mypage";
		
	}
	
	@RequestMapping(value= {"/transferProcedures"}, method=RequestMethod.GET)
	public String transferProcedures(Model model){
		
		LoginInfoDto loginInfo  = (LoginInfoDto)session.getAttribute("loginInfo");
		UserInfoDto dto = userService.getUser(loginInfo.getUserId());
		
		model.addAttribute("userInfoDto", dto);
		return "sales_confirm";
		
	}
	
	@RequestMapping(value= {"/transferRegistration"}, method=RequestMethod.POST)
	public String transferRegistration(Model model) throws ParseException{
		
		LoginInfoDto loginInfo  = (LoginInfoDto)session.getAttribute("loginInfo");
		UserInfoDto dto = userService.getUser(loginInfo.getUserId());
		
		userService.insertTransfer(dto);
			
		return "redirect:/transferComplete";
		
	}
	
	@RequestMapping(value= {"/transferComplete"}, method=RequestMethod.GET)
	public String complete() {
		
		return "sales_complete";
	}
	
}
