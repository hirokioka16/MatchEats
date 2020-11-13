//package com.example.demo.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.example.demo.dto.FoodInfoDto;
//import com.example.demo.service.DeliveryService;
//import com.example.demo.service.FoodService;
//
//@Controller
//@RequestMapping(value= {"/delivery"})
//public class DeliveryController {
//	
//	@Autowired
//	DeliveryService deliveryService;
//	
//	//配達リクエストされたやつ
//	@RequestMapping(value= {"/requestlist"}, method=RequestMethod.GET)
//	public String deliveryRequestList(Model model) {
//	
//		//delivery_flgが1のcookofferを持ってくる
//			
//		//List<UserInfoDto> list = foodService.getMyFoodList(1);
//		
//		//ユーザー名、リクエストした日、住所、リクエストの詳細を次の画面に持っていく
//		//model.addAttribute("list", list);
//		return "delivery_requestlist";
//	}
//	
//	//配達リクエストが承認されたやつ
//	@RequestMapping(value= {"/list"}, method=RequestMethod.GET)
//	public String deliveryList(Model model) {
//		
//		return "delivery_approval_list";
//	}
//	
//	@RequestMapping(value= {"/collectionconfirm"}, method=RequestMethod.POST)
//	public String correction(Model model) {
//		
//		return "delivery_approval_list_confirm";
//	}
//	
//	@RequestMapping(value= {"/collectionconfirm"}, method=RequestMethod.POST)
//	public String correctionInsert(Model model) {
//		
//		return "redirect:/delivery/collectioncomplete";
//	}
//	
//	@RequestMapping(value= {"/collectioncomplete"}, method=RequestMethod.GET)
//	public String correctionComplete() {
//		
//		return "delivery_approval_list_confirm";
//	}

//}
