package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.LoginInfoDto;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.form.UpdateUserForm;
import com.example.demo.form.UserForm;
import com.example.demo.service.UserServise;

@Controller
@RequestMapping(value = {"/updateUserInfo"})
public class UpdateUserEntry {
	
	@Autowired
	UserServise userService;
	@Autowired
	HttpSession session;
	@Autowired
	InputUserEntryController inputUserEntry;
	
	
	
	@RequestMapping(value= {"/input"}, method=RequestMethod.GET)
	public String inputUpdate(@ModelAttribute("updateUserForm")UpdateUserForm form,Model model){
		
		LoginInfoDto loginInfo  = (LoginInfoDto)session.getAttribute("loginInfo");
		UserInfoDto dto = userService.getUser(loginInfo.getUserId());
		
		form.setUserId(String.valueOf(dto.getUserId()));
		form.setUserName(dto.getUserName());
		form.setNickName(dto.getNickName());
		form.setUserMail(dto.getUserMail());
		form.setUserPass(dto.getUserPass());
		form.setPostalCode(dto.getPostalCode());
		form.setUserAddres(dto.getUserAddres());
		form.setUserTel(dto.getUserTel());
		
		//分解
		String birth = dto.getUserBirth();
		String[] birthList = birth.split("-");
		form.setYear(birthList[0]);
		form.setMonth(birthList[1]);
		form.setDay(birthList[2]);
		
		form.setUserCard(dto.getUserCard());
		form.setCardName(dto.getCardName());
		
		//分解
		String limit = dto.getLimitDate();
		String[] limitList = limit.split("");
		form.setLimitYear(limitList[0] + limitList[1] + limitList[2] + limitList[3]);
		form.setLimitMonth(limitList[4] + limitList[5]);
		
		form.setSecureCode(dto.getSecureCode());
		
		return "update_userInfo";
		
	
	}
	
	@RequestMapping(value= {"/confirm"}, method=RequestMethod.POST)
	public String confirm(@Validated @ModelAttribute("updateUserForm")UpdateUserForm form, BindingResult result, Model model) {
		String url = null;
		
		
		// 入力エラーをチェック
		if(result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for(ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			url = "update_userInfo";
		} else {
			String userBirth = form.getYear() + "-" + form.getMonth() + "-" + form.getDay();
			String limitDate = form.getLimitYear() + form.getLimitMonth();
			// formの値を入れるdtoに入れるメソッドを呼んでいる
			UserInfoDto dto = inputUserEntry.getUpdateuserDto(form);
			dto.setUserBirth(userBirth);
			dto.setLimitDate(limitDate);
			session.setAttribute("userInfoDto", dto);
			url = "confirm_userInfo";
			
		}
		
		return url;
		
		
	}
	
	@RequestMapping(value= {"/update"}, method=RequestMethod.POST)
	public String insert() throws java.text.ParseException {
		
		UserInfoDto dto = (UserInfoDto)session.getAttribute("userInfoDto");
		userService.update(dto);
		
		session.removeAttribute("userInfoDto");
		return "redirect:/updateUserInfo/complete";
			
	}
	
	@RequestMapping(value= {"/complete"}, method=RequestMethod.GET)
	public String complete() {
		return "complete_userInfo";
	}
	
	

}
