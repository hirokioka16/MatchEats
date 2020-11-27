package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.CookingInfoDto;
import com.example.demo.dto.FoodInfoDto;
import com.example.demo.dto.LoginInfoDto;
import com.example.demo.service.CookingOfferService;

@Controller
public class CookingListController {


@Autowired
CookingOfferService cookOfferService;


@Autowired
HttpSession session;

	//リストを表示する
	@RequestMapping(value={"/cooking/list"},method=RequestMethod.GET)
	public String displayList(Model model) {
		LoginInfoDto user  = (LoginInfoDto) session.getAttribute("loginInfo" );
		List<FoodInfoDto> CookingList=cookOfferService.getList(user.getUserId());

		model.addAttribute("CookingList", CookingList);
		return "my_cook_list";

	}


	//詳細画面を表示する
	  @RequestMapping(value={"/cooking/detail"},method=RequestMethod.GET)
	  public String displayView(@RequestParam("requestId")String requestId,Model model) {

		  CookingInfoDto dto = cookOfferService.getdetail(Integer.parseInt(requestId));

		  model.addAttribute("detail",dto);
		return "my_cook_details";


	  }

	//データベースに配達依頼登録
	  @RequestMapping(value= {"/cookinglist/insert"},method=RequestMethod.POST)
	  public String update(@RequestParam("offerId") String offerId,Model model) {
		  cookOfferService.update(offerId);

		  return "redirect:/cookinglist/complete";
	  }

	  @RequestMapping(value= {"/cookinglist/complete"},method=RequestMethod.GET)
	  public String complete() {

		  return "complete_cooking";
	  }
}
