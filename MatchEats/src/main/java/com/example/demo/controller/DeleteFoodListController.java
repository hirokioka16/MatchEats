package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.FoodInfoDto;
import com.example.demo.service.FoodService;

@Controller
@RequestMapping(value= {"/deletefood"})
public class DeleteFoodListController {

	@Autowired
	FoodService foodService;
	@Autowired
	HttpSession session;

	@RequestMapping(value= {"/confirm"},method=RequestMethod.GET)
	public String confirmDelete(@RequestParam("requestId") String requestId, Model model) {

		FoodInfoDto dto = foodService.getUdFoodList(Integer.parseInt(requestId));

		String genre = foodService.getGenreName(dto.getGenreId());

		model.addAttribute("dto",dto);
		model.addAttribute("genre",genre);

		return "confirm_delete_food";

	}


	@RequestMapping(value= {"/delete"},method=RequestMethod.POST)
	public String delete(@RequestParam("requestId") String requestId, Model model) {

		foodService.delete(requestId);

		return "redirect:/deletefood/complete";
	}

	@RequestMapping(value= {"/complete"}, method=RequestMethod.GET)
	public String complete(){

		return "complete_foodlist_delete";
	}

}
