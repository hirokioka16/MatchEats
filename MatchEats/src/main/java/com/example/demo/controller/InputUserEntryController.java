package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.example.demo.dto.FoodInfoDto;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.form.UserForm;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServise;


@Controller
@RequestMapping(value= {"/inputUserEntry"})
public class InputUserEntryController {
	
	@Autowired
	UserServise userService;
	@Autowired
	HttpSession session;
	@Autowired
	UserRepository userRepository;
	
	
	@RequestMapping(value= {"/input"},  method=RequestMethod.GET)
	public String input(@ModelAttribute("UserForm")UserForm form, Model model) {
		
		return "input_userEntry";
	}
	
	
	@RequestMapping(value= {"/confirm"}, method=RequestMethod.POST)
	public String confirm(@Validated @ModelAttribute("UserForm")UserForm form, BindingResult result, Model model) {
		String url = null;
		String userBirth = form.getYear() + "-" + form.getMonth() + "-" + form.getDay();
		String limitDate = form.getLimitYear() + form.getLimitMonth();
		
		// 入力エラーをチェック
		if(result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for(ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
		
		}
			
			
			// formの値を入れるdtoに入れるメソッドを呼んでいる
			UserInfoDto dto = getInsertUserDto(form);
			dto.setUserBirth(userBirth);
			dto.setLimitDate(limitDate);
			session.setAttribute("userInfoDto", dto);
			
			url = "confirm_userEntry";
		
		
		return url;
	}
	
	
	@RequestMapping(value= {"/insert"}, method=RequestMethod.POST)
	public String insert() throws java.text.ParseException {
		
		UserInfoDto dto = (UserInfoDto)session.getAttribute("userInfoDto");
		userService.insert(dto);
		
		session.removeAttribute("userInfoDto");
		return "redirect:/inputUserEntry/complete";
				
	
	}
	
	
	@RequestMapping(value= {"/complete"}, method=RequestMethod.GET)
	public String complete() {
		return "complete_user_entry";
	}
	
	
	public UserInfoDto getUserDto(UserForm userForm) {
		
		UserInfoDto userInfo = new UserInfoDto();
		
		userInfo.setUserId(Integer.parseInt(userForm.getUserId()));
		userInfo.setAccountName(userForm.getAccountName());
		userInfo.setAccountNumber(userForm.getAccountNumber());
		userInfo.setUserName(userForm.getUserName());
		userInfo.setNickName(userForm.getNickName());
		userInfo.setUserMail(userForm.getUserMail());
		userInfo.setUserPass(userForm.getUserPass());
		userInfo.setPostalCode(userForm.getPostalCode());
		userInfo.setUserAddres(userForm.getUserAddres());
		userInfo.setUserTel(userForm.getUserTel());
		userInfo.setCardName(userForm.getCardName());
		userInfo.setSecureCode(userForm.getSecureCode());
		userInfo.setUserCard(userForm.getUserCard());
		userInfo.setBankName(userForm.getBankName());
		userInfo.setBranchName(userForm.getBranchName());
		
		
		return userInfo;
		
		
		
	}
	
public UserInfoDto getInsertUserDto(UserForm userForm) {
		
		UserInfoDto userInfo = new UserInfoDto();
		
		userInfo.setAccountName(userForm.getAccountName());
		userInfo.setAccountNumber(userForm.getAccountNumber());
		userInfo.setUserName(userForm.getUserName());
		userInfo.setNickName(userForm.getNickName());
		userInfo.setUserMail(userForm.getUserMail());
		userInfo.setUserPass(userForm.getUserPass());
		userInfo.setPostalCode(userForm.getPostalCode());
		userInfo.setUserAddres(userForm.getUserAddres());
		userInfo.setUserTel(userForm.getUserTel());
		userInfo.setCardName(userForm.getCardName());
		userInfo.setSecureCode(userForm.getSecureCode());
		userInfo.setUserCard(userForm.getUserCard());
		userInfo.setBankName(userForm.getBankName());
		userInfo.setBranchName(userForm.getBranchName());
		
		
		return userInfo;
		
		
		
	}
	
	
	
}
		


