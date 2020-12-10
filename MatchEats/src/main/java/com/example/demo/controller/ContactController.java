

package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.example.demo.dto.ContactInfoDto;
import com.example.demo.dto.FoodInfoDto;
import com.example.demo.dto.GenreInfoDto;
import com.example.demo.dto.LoginInfoDto;
import com.example.demo.form.ContactForm;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactService;

@Controller
@RequestMapping(value= {"/inputContact"})
	public class ContactController{


		@Autowired
		HttpSession session;

		@Autowired
		ContactService contactService;

		@Autowired
		ContactRepository contactRepository;

		@RequestMapping(value= {"/contactlist"}, method=RequestMethod.GET)
		public String ContactList(Model model) {
			List<ContactInfoDto> contactlist =contactService .searchAll();
			model.addAttribute("contactlist",contactlist);
			return "contactlist";

		}



	//入力
		@RequestMapping(value= {"/input"},method=RequestMethod.GET)
		public String input(@ModelAttribute("ContactForm")ContactForm form,Model model) {

			LoginInfoDto loginInfo = (LoginInfoDto)session.getAttribute("loginInfo");

			if(loginInfo == null) {

				return "redirect:/login";
			}
			
			ContactInfoDto dto = new ContactInfoDto();
			try {
				dto =(ContactInfoDto)session.getAttribute("contactInfoDto");
			}catch (NullPointerException e){
			}
			if(dto != null) {
				form.setContent(dto.getContent());
			}
		return "input_contact";
		}
	//確認
		@RequestMapping(value= {"/confirm"}, method=RequestMethod.POST)
		public String confirm(@Validated @ModelAttribute("ContactForm")ContactForm form, BindingResult result,Model model) {
			  	String url = null;

			  //入力エラーをチェック
				if(result.hasErrors()) {
					List<String> errorList = new ArrayList<String>();
					for(ObjectError error : result.getAllErrors()) {
						errorList.add(error.getDefaultMessage());
					}
					
					url = "input_contact";
				}else {

			  	ContactInfoDto dto = getCreateDto(form);

			  	LoginInfoDto loginInfo = (LoginInfoDto)session.getAttribute("loginInfo");

			  	dto.setUserId(loginInfo.getUserId());

			  	session.setAttribute("contactInfoDto",dto);
			  	
			  	url = "confirm_contact";
				}
			  	

		return url;
		}

	//登録
		@RequestMapping(value= {"/insert"},method=RequestMethod.POST)
		public String insert() throws java.text.ParseException{

			ContactInfoDto dto =(ContactInfoDto)session.getAttribute("contactInfoDto");
			//dtoに現在時刻をセット
			dto.setContactDate(getNowDate());
			contactService.insert(dto);

			session.removeAttribute("contactInfoDto");
			return "redirect:/inputContact/complete";

		}
	//完了
		@RequestMapping(value= {"/complete"},method=RequestMethod.GET)
		public String complete() {
			return "complete_contact";
		}


	//formの入力内容をdtoに挿入
		public ContactInfoDto getCreateDto(ContactForm contactForm) {

			ContactInfoDto contactInfo = new ContactInfoDto();

			contactInfo.setContent(contactForm.getContent());


			return contactInfo;
		}
		//現在時刻を登録する
		public Date getNowDate() throws java.text.ParseException {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(date);
			//String型の日付をDate型に変更している
			SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
			Date now = d1.parse(strDate);

			return now;
		}
}
