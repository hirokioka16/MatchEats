package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.AdminInfoDto;
import com.example.demo.form.LoginForm;
import com.example.demo.service.AdminService;

@Controller
public class AdminLoginController {

	@Autowired
	HttpSession session;
	@Autowired
	AdminService adminService;

	@RequestMapping(value= {"/adminlogin"},method=RequestMethod.GET)
	public String adminLogin (@ModelAttribute("errMessage")String msg, @ModelAttribute("loginform")LoginForm loginform) {

		return "admin_login";
	}

	@RequestMapping(value= {"/adminauth"},method=RequestMethod.POST)
	public String adminAuth(RedirectAttributes redirectAttributes,LoginForm loginform)throws Exception{

		String url;
		String err="";

		AdminInfoDto adminInfo = null;
		adminInfo = adminService.login(loginform.getEmail(),loginform.getPass());

		if(adminInfo != null) {
			session.setAttribute("adminInfo",adminInfo);
			url="redirect:adminmenu";
		}else {
			err="メールアドレスまたはパスワードが間違っています";
			url = "redirect:adminlogin";
			redirectAttributes.addFlashAttribute("errMessage",err);
		}

		return url;
	}

	@RequestMapping(value= {"/adminlogout"},method=RequestMethod.GET)
	public String logout(Model model) {
		session.removeAttribute("adminInfo");
		return "redirect:adminlogin";
	}
}
