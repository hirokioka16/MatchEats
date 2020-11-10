	package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.LoginInfoDto;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;


public class LoginServlet {

	@Autowired
	LoginService loginService;
	@Autowired
	HttpSession session;

	@RequestMapping(value= {"/login"},method=RequestMethod.GET)
	public String login(@ModelAttribute("errMessage")String msg, @ModelAttribute("loginform")LoginForm loginform) {

		return "login";
	}

	@RequestMapping(value= {"/auth"},method=RequestMethod.POST)
	public String auth(RedirectAttributes redirectAttributes,LoginForm loginform)throws Exception{

		String url;
		String err="";

		LoginInfoDto loginInfo = null;
		loginInfo = loginService.login(loginform.getEmail(),loginform.getPass());

		if(loginInfo !=null) {
			session.setAttribute("loginInfo", loginInfo);
			url = "redirect:menu";
		}else{
			err = "メールアドレスまたはパスワードが間違っています";
			url = "redirect:login";
			redirectAttributes.addFlashAttribute("errMessage",err);
		}

		return url;
}
}
