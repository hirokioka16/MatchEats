package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
private CookingOfferService cookService;

@Autowired
CookingOfferService cookingOfferService;

@Autowired
HttpSession session;

//リストを表示する
@GetMapping(value="/cooking/list")
public String displayList(Model model) {
	LoginInfoDto user  = (LoginInfoDto) session.getAttribute("loginInfo" );
	List<FoodInfoDto> CookingList=cookService.getList(user.getUserId());


	model.addAttribute("CookingList", CookingList);
	return "my_cook_list";

}

//詳細画面を表示する

  @GetMapping("/user/detail")
  public String displayView(@RequestParam("requestId")String requestId,Model model) {

	  CookingInfoDto dto = cookService.getdetail(Integer.parseInt(requestId));

	  model.addAttribute("detail",dto);
	return "my_cook_details";


  }

//データベースに配達依頼登録
  @RequestMapping(value= {"cookinglist/insert"},method=RequestMethod.POST)
  public String update(@RequestParam("offerId") String offerId,Model model) {

	  cookingOfferService.update(offerId);



	  return "my_cookinglist_complete";
  }
}
