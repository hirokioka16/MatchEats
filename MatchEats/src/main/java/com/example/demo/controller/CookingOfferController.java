package com.example.demo.controller;

import java.io.IOException;
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

import com.example.demo.dto.FoodInfoDto;
import com.example.demo.dto.GenreInfoDto;
import com.example.demo.form.CookingForm;
import com.example.demo.service.FoodService;

@Controller
@RequestMapping(value= {"/offerinput"})
public class CookingOfferController {

	@Autowired
	FoodService foodService;
	@Autowired
	HttpSession session;

	@RequestMapping(value= {"/input"}, method=RequestMethod.POST)
	public String input(@RequestParam("requestId") String requestId, @ModelAttribute("CookingForm")CookingForm form,Model model) {

		//オファーを送る食べたいものの情報を再取得
		FoodInfoDto dto = foodService.getUdFoodList(Integer.parseInt(requestId));

		model.addAttribute("dto",dto);

		return "input_offer";
	}

	@RequestMapping(value= {"/confirm"}, method=RequestMethod.POST)
	public String confirm (@Validated @ModelAttribute("CookingForm")CookingForm form,BindingResult result,Model model) throws IllegalStateException, IOException {

		//入力エラーをチェック
				if(result.hasErrors()) {
					List<String> errorList = new ArrayList<String>();
					for(ObjectError error : result.getAllErrors()) {
						errorList.add(error.getDefaultMessage());
					}
					List<GenreInfoDto> list = foodService.getGenre();
					model.addAttribute("validationError", errorList);
					model.addAttribute("list",list);

				}else {

				}












		return null;

	}


}
