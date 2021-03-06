package com.example.demo.controller;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.FoodInfoDto;
import com.example.demo.form.CookingForm;
import com.example.demo.form.UserForm;
import com.example.demo.form.BackForm;
import com.example.demo.service.FoodService;

@Controller
public class FoodListController {
	@Autowired
	FoodService foodService;
	@Autowired
	HttpSession session;




	@RequestMapping(value= {"/detailfoodlist"}, method=RequestMethod.GET)
	public String detail(@RequestParam("requestId") int requestId,@ModelAttribute("BackForm")BackForm backForm,@ModelAttribute("errMsg") String errMsg,Model model) {

		//エラーは広池プロのやつとマージすると消えます
		FoodInfoDto dto = foodService.getUdFoodList(requestId);

		model.addAttribute("dto",dto);


		return "detailfoodlist";

	}

	@RequestMapping(value= {"/seachfoodlist"}, method=RequestMethod.GET)
	public String seach(@RequestParam("keyword") String keyword,@ModelAttribute("BackForm")BackForm backForm,Model model) {

		session.removeAttribute("CookingInfoDto");
		List<FoodInfoDto> list = null;
		list = foodService.search(keyword);

		if(list.size()==0) {
			String nullMsg = "該当するものが見つかりませんでした。";
			model.addAttribute("nullMsg",nullMsg);
		}

		model.addAttribute("list",list);
		model.addAttribute("keyword",keyword);

		return "seachfoodlist";

	}
}
