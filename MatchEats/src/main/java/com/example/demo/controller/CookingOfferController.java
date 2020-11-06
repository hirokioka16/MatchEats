package com.example.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.form.CookingForm;

@Controller
@RequestMapping(value= {"/offerinput"})
public class CookingOfferController {

	@Autowired
	HttpSession session;

	@RequestMapping(value= {"/input"}, method=RequestMethod.GET)
	public String input(@ModelAttribute("CookingForm")CookingForm form,Model model) {

		return "input_offer";
	}

	@RequestMapping(value= {"/confirm"}, method=RequestMethod.POST)
	public String input (@Validated @ModelAttribute("CookingForm")CookingForm form,BindingResult result) throws IllegalStateException, IOException {
		return null;

	}


}
