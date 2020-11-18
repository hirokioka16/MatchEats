package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.ContactInfoDto;
import com.example.demo.service.ContactService;

/**
 *
 * お問い合わせ一覧情報 Controller
 *
 */
@Controller
public class ContactController{

	/**
	 * お問い合わせ情報　Service
	 */
	@Autowired
	private ContactService contactService;

	/**
	 * お問い合わせ一覧画面を表示
	 * @param model
	 * @return　お問い合わせ一覧画面
	 */

	@RequestMapping(value= {"/contactlist"}, method=RequestMethod.GET)
	public String ContactList(Model model) {
		List<ContactInfoDto> contactlist =contactService .searchAll();
		model.addAttribute("contactlist",contactlist);
		return "contactlist";

	}


}
