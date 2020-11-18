package com.example.demo.controller;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.FoodInfoDto;
import com.example.demo.service.CookingOfferService;
import com.example.demo.service.UserServise;

@Controller
public class CookingListController {

@Autowired
private CookingOfferService cookService;

//リストを表示する
@GetMapping(value="/cooking/list")
public String displayList(Model model) {

	List<FoodInfoDto> CookingList=cookService.getList();

	//List<CookingInfoDto>cookinglist = FoodService.searchAll();
	//model.addAttribute("cokkinglist","cookinglist");

	model.addAttribute("CookingList", CookingList);
	return "my_cook_list";

}

//詳細画面を表示する

  @GetMapping("/user/detail")
  public String displayView(@PathVariable User id, Model model) {
    User user = UserServise.findById(id);
    model.addAttribute("userRequest", user);
    return "user/detail";
}

}
