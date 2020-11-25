package com.example.demo.controller;


import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.FoodInfoDto;
import com.example.demo.dto.GenreInfoDto;
import com.example.demo.form.FoodForm;
import com.example.demo.service.FoodService;

@Controller
@RequestMapping(value= {"/updatefoodlist"})
public class UpdateFoodListController {

	@Autowired
	FoodService foodService;
	@Autowired
	HttpSession session;
	@Autowired
	InputFoodListController inputFoodList;
	
	@RequestMapping(value= {"/input"}, method=RequestMethod.POST)
	public String inputUpdate(@RequestParam("requestId") String requestId,@ModelAttribute("FoodForm")FoodForm form,Model model) {
		
		List<GenreInfoDto> list = foodService.getGenre();
		
		FoodInfoDto dto = new FoodInfoDto();

		//確認画面で戻るボタンを押下した時に前回までの入力内容を入力画面に反映させる処理
		try {
			dto = (FoodInfoDto)session.getAttribute("foodInfDto");
		}catch (NullPointerException e){
			e.printStackTrace();
		}
		if(dto == null) {
			dto = foodService.getUdFoodList(Integer.parseInt(requestId));
			form.setRequestId(dto.getRequestId());
			form.setFoodName(dto.getFoodName());
			form.setRequestOutline(dto.getRequestOutline());
			form.setGenreId(String.valueOf(dto.getGenreId()));
			form.setEatFlag(dto.getEatFlag());
			form.setFileName(dto.getPictureName());
			form.setGenreName(inputFoodList.getGenreName(dto.getGenreId()));
			
			model.addAttribute("list",list);
		}else {
			form.setRequestId(dto.getRequestId());
			form.setFoodName(dto.getFoodName());
			form.setRequestOutline(dto.getRequestOutline());
			form.setGenreId(String.valueOf(dto.getGenreId()));
			form.setEatFlag(dto.getEatFlag());
			form.setFileName(dto.getPictureName());
			form.setGenreName(inputFoodList.getGenreName(dto.getGenreId()));
			
			model.addAttribute("list",list);
			
		}
			
		

		
		return "update_foodlist";
	}
	
	@RequestMapping(value= {"/confirm"}, method=RequestMethod.POST)
	public String confirm(@Validated @ModelAttribute("FoodForm")FoodForm form,BindingResult result,Model model){
		String url = null;	
		//入力エラーをチェック
				if(result.hasErrors()) {
					List<String> errorList = new ArrayList<String>();
					for(ObjectError error : result.getAllErrors()) {
						errorList.add(error.getDefaultMessage());
					}
					List<GenreInfoDto> list = foodService.getGenre();
					model.addAttribute("validationError", errorList);
					model.addAttribute("list",list);
					form.setGenreName(inputFoodList.getGenreName(Integer.parseInt(form.getGenreId())));
					url = "update_foodlist";
				}else{				       					
			        model.addAttribute("genreName",inputFoodList.getGenreName(Integer.parseInt(form.getGenreId())) );
					//formの値をdtoにいれるメソッドを呼んでいる
					FoodInfoDto dto = inputFoodList.getCreateDto(form);
					session.setAttribute("foodInfDto", dto);
					url = "confirm_foodlist_update";	
				}
				return url;
	}
	@RequestMapping(value= {"/update"}, method=RequestMethod.POST)
	public String update(){
		
		FoodInfoDto dto = (FoodInfoDto)session.getAttribute("foodInfDto");
		foodService.update(dto);
		session.removeAttribute("foodInfDto");
		
		return "redirect:/updatefoodlist/complete";
	}
	@RequestMapping(value= {"/complete"}, method=RequestMethod.GET)
	public String complete(){
		
		return "complete_foodlist_update";
	}
}
