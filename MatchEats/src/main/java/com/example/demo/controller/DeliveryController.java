package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.DeliveryInfoDto;
import com.example.demo.dto.FoodInfoDto;
import com.example.demo.service.DeliveryService;
import com.example.demo.service.FoodService;

@Controller
@RequestMapping(value= {"/delivery"})
public class DeliveryController {
	
	@Autowired
	DeliveryService deliveryService;
	
	//配達リクエストされたやつ
	@RequestMapping(value= {"/requestlist"}, method=RequestMethod.GET)
	public String deliveryRequestList(Model model) {
		
		List<DeliveryInfoDto> list = deliveryService.getRequestList(true);
		
		model.addAttribute("list", list);
		return "delivery_requestlist";
	}
	
	//配達リクエストの詳細
	@RequestMapping(value= {"/requestDetail"}, method=RequestMethod.POST)
	public String deliveryRequestDetail(@RequestParam("requestId") int requestId,Model model) {
	
		DeliveryInfoDto dto = deliveryService.getDetailDeliveryRequest(requestId);
		model.addAttribute("dto", dto);
		return "detail_deliverylist";
	}
	
	//配達リクエスト承認処理
	@RequestMapping(value= {"/deliveryinsert"}, method=RequestMethod.POST)
	public String deliveryRequestDetail(@RequestParam("requestId") int requestId) throws ParseException {
		
		boolean approval_delivery_flg = true;
		deliveryService.deliveryRequesrInsert(requestId,getNowDate(),approval_delivery_flg);
		
		return "redirect:/delivery/deliverycomplete";
	}
	
	@RequestMapping(value= {"/deliverycomplete"}, method=RequestMethod.GET)
	public String deliveryComplete() {
		
		return "delivery_request_complete";
	}
	
	
	
	//配達リクエストが承認されたやつ
	@RequestMapping(value= {"/approvallist"}, method=RequestMethod.GET)
	public String deliveryList(Model model) {
		
		List<DeliveryInfoDto> list = deliveryService.getRequestAPProvalList();
		
		model.addAttribute("list", list);
		
		return "delivery_approval_list";
	}
	
	@RequestMapping(value= {"/collectionconfirm"}, method=RequestMethod.POST)
	public String correction(Model model) {
		
		return "delivery_approval_list_confirm";
	}
	
	@RequestMapping(value= {"/collectioninsert"}, method=RequestMethod.POST)
	public String correctionInsert(Model model) {
		
		return "redirect:/delivery/collectioncomplete";
	}
	
	@RequestMapping(value= {"/collectioncomplete"}, method=RequestMethod.GET)
	public String correctionComplete() {
		
		return "delivery_approval_list_confirm";
	}
	
	//現在時刻(秒まで)取得
	public Date getNowDate() throws java.text.ParseException {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String strDate = dateFormat.format(date);
		//String型の日付をDate型に変更している
		SimpleDateFormat d1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date now = d1.parse(strDate);

		return now;
	}

}
