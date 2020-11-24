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
import com.example.demo.service.HistoryService;

@Controller
@RequestMapping(value= {"/delivery"})
public class DeliveryController {
	
	@Autowired
	DeliveryService deliveryService;
	
	@Autowired
	HistoryService historyService;
	
	//配達リクエストされたやつ
	@RequestMapping(value= {"/requestlist"}, method=RequestMethod.GET)
	public String deliveryRequestList(Model model) {
		
		List<DeliveryInfoDto> list = deliveryService.getRequestList(true);
		
		model.addAttribute("list", list);
		return "delivery_requestlist";
	}
	
	//配達リクエストの詳細
	@RequestMapping(value= {"/requestDetail"}, method=RequestMethod.POST)
	public String deliveryRequestDetail(@RequestParam("offerId") int offerId,Model model) {
	
		DeliveryInfoDto dto = deliveryService.getDetailDeliveryRequest(offerId);
		model.addAttribute("dto", dto);
		return "detail_deliverylist";
	}
	
	//配達リクエスト承認処理
	@RequestMapping(value= {"/deliveryinsert"}, method=RequestMethod.POST)
	public String deliveryRequestDetail(@RequestParam("requestId") int requestId) throws ParseException {
		
		deliveryService.deliveryRequesrInsert(requestId,getNowDate());
		
		return "redirect:/delivery/deliverycomplete";
	}
	
	@RequestMapping(value= {"/deliverycomplete"}, method=RequestMethod.GET)
	public String deliveryComplete() {
		
		return "delivery_request_complete";
	}
	
	
	
	//配達リクエストが承認されたやつ回収編
	@RequestMapping(value= {"/approvallist"}, method=RequestMethod.GET)
	public String deliveryList(Model model) {
		
		//回収するやつ
		List<DeliveryInfoDto> list = deliveryService.getRequestAPProvalList("1");
			
		model.addAttribute("list", list);
		
		return "delivery_approval_list";
	}
	
	//配達する料理を回収するかの確認画面
	@RequestMapping(value= {"/collectionconfirm"}, method=RequestMethod.POST)
	public String correction(@RequestParam("offerId") int offerId,Model model) {
		
		DeliveryInfoDto dto = deliveryService.getDetailDeliveryRequest(offerId);
		model.addAttribute("dto", dto);
		
		return "delivery_approval_list_confirm";
	}
	
	//回収完了登録
	@RequestMapping(value= {"/collectioninsert"}, method=RequestMethod.POST)
	public String correctionInsert(@RequestParam("offerId") int offerId) {
		
		//sessionからadminIdをとってくるそして入れる
		int adminId = 1;
		historyService.cookCollection(offerId,adminId);
		
		return "redirect:/delivery/collectioncomplete";
	}
	
	@RequestMapping(value= {"/collectioncomplete"}, method=RequestMethod.GET)
	public String correctionComplete() {
		
		return "food_collection_complete";
	}
	
	//配達リクエストが承認されたやつ配達編
		@RequestMapping(value= {"/mydeliverylist"}, method=RequestMethod.GET)
		public String mydeliverylist(Model model) {
			
		int adminId = 1; 
		//配達するやつ
		List<DeliveryInfoDto> list = ;
		
		return "my_delivery_list";
	}
	
		@RequestMapping(value= {"/confirm"}, method=RequestMethod.POST)
		public String confirm(Model model) {
			
			return "confirm_delivery";
		}
		
		@RequestMapping(value= {"/insert"}, method=RequestMethod.POST)
		public String insert() {
			
			return "redirect:/delivery/complete";
		}
		
		@RequestMapping(value= {"/complete"}, method=RequestMethod.GET)
		public String complete(Model model) {
			
			return "delivery_complete";
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
