package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.AdminInfoDto;
import com.example.demo.dto.CookingInfoDto;
import com.example.demo.dto.DeliveryInfoDto;
import com.example.demo.dto.HistoryInfoDto;
import com.example.demo.service.CookingOfferService;
import com.example.demo.service.DeliveryService;
import com.example.demo.service.HistoryService;

@Controller
@RequestMapping(value= {"/delivery"})
public class DeliveryController {

	@Autowired
	DeliveryService deliveryService;

	@Autowired
	HistoryService historyService;

	@Autowired
	MailTest02Application mail;

	@Autowired
	CookingOfferService cookOfferService;

	@Autowired
	HttpSession session;

	//配達リクエストされたやつ
	@RequestMapping(value= {"/requestlist"}, method=RequestMethod.GET)
	public String deliveryRequestList(Model model) {

		AdminInfoDto adminInfo = (AdminInfoDto)session.getAttribute("adminInfo");
		if(adminInfo == null) {
			return "redirect:/adminlogin";
		}

		List<DeliveryInfoDto> list = deliveryService.getRequestList(true);
		if(list.size() == 0) {
			String nullMsg = "未承認の配達リクエストはありません。";
			model.addAttribute("nullMsg",nullMsg);
		}

		model.addAttribute("list", list);
		return "delivery_requestlist";
	}

	//配達リクエストの詳細
	@RequestMapping(value= {"/requestDetail"}, method=RequestMethod.POST)
	public String deliveryRequestDetail(@RequestParam("offerId") int offerId,Model model) {

		AdminInfoDto adminInfo = (AdminInfoDto)session.getAttribute("adminInfo");
		if(adminInfo == null) {
			return "redirect:/adminlogin";
		}

		DeliveryInfoDto dto = deliveryService.getDetailDeliveryRequest(offerId);
		model.addAttribute("dto", dto);
		return "detail_deliverylist";
	}

	//配達リクエスト承認処理
	@RequestMapping(value= {"/deliveryinsert"}, method=RequestMethod.POST)
	public String deliveryRequestDetail(@RequestParam("offerId") int offerId) throws ParseException {


		AdminInfoDto adminInfo = (AdminInfoDto)session.getAttribute("adminInfo");
		if(adminInfo == null) {
			return "redirect:/adminlogin";
		}

		deliveryService.deliveryRequesrInsert(offerId,getNowDate());

		//配達リクエストを承認したと通知メールを送る
		CookingInfoDto dto = cookOfferService.getOfferInfo(offerId);
		mail.sendMail(dto.getUserMail(),"配達通知", dto.getUserName()+"様の配達依頼を確認しましたので料理を回収しに向かいます");

		return "redirect:/delivery/deliverycomplete";
	}

	@RequestMapping(value= {"/deliverycomplete"}, method=RequestMethod.GET)
	public String deliveryComplete() {

		AdminInfoDto adminInfo = (AdminInfoDto)session.getAttribute("adminInfo");
		if(adminInfo == null) {
			return "redirect:/adminlogin";
		}

		return "delivery_request_complete";
	}



	//配達リクエストが承認されたやつ回収編
	@RequestMapping(value= {"/approvallist"}, method=RequestMethod.GET)
	public String deliveryList(Model model) {

		AdminInfoDto adminInfo = (AdminInfoDto)session.getAttribute("adminInfo");
		if(adminInfo == null) {
			return "redirect:/adminlogin";
		}

		//回収するやつ
		List<DeliveryInfoDto> list = deliveryService.getRequestAPProvalList("1");

		if(list.size()==0) {
			String nullMsg = "現在、回収する料理はありません。";
			model.addAttribute("nullMsg",nullMsg);
		}

		model.addAttribute("list", list);

		return "delivery_approval_list";
	}

	//配達する料理を回収するかの確認画面
	@RequestMapping(value= {"/collectionconfirm"}, method=RequestMethod.POST)
	public String correction(@RequestParam("offerId") int offerId,Model model) {

		AdminInfoDto adminInfo = (AdminInfoDto)session.getAttribute("adminInfo");
		if(adminInfo == null) {
			return "redirect:/adminlogin";
		}

		DeliveryInfoDto dto = deliveryService.getDetailDeliveryRequest(offerId);
		model.addAttribute("dto", dto);

		return "delivery_approval_list_confirm";
	}

	//回収完了登録
	@RequestMapping(value= {"/collectioninsert"}, method=RequestMethod.POST)
	public String correctionInsert(@RequestParam("offerId") int offerId) throws ParseException {

		AdminInfoDto adminInfo = (AdminInfoDto)session.getAttribute("adminInfo");
		if(adminInfo == null) {
			return "redirect:/adminlogin";
		}

		//sessionからadminIdをとってくるそして入れる
		int adminId = 1;
		historyService.cookCollection(offerId,adminId,getNowDate());

		//料理を回収したと通知メールを送る
		CookingInfoDto dto = cookOfferService.getOfferInfo(offerId);
		mail.sendMail(dto.getRequestUserMail(),"料理回収通知",dto.getRequestUserName()+"様に配達する料理を回収しましたのでもうしばらくお待ちください");

		return "redirect:/delivery/collectioncomplete";
	}

	@RequestMapping(value= {"/collectioncomplete"}, method=RequestMethod.GET)
	public String correctionComplete() {
		AdminInfoDto adminInfo = (AdminInfoDto)session.getAttribute("adminInfo");
		if(adminInfo == null) {
			return "redirect:/adminlogin";
		}

		return "food_collection_complete";
	}

	//配達リクエストが承認されたやつ配達編
	@RequestMapping(value= {"/mydeliverylist"}, method=RequestMethod.GET)
	public String mydeliverylist(Model model) {

		AdminInfoDto adminInfo = (AdminInfoDto)session.getAttribute("adminInfo");
		if(adminInfo == null) {
			return "redirect:/adminlogin";
		}


		//配達するやつ
		List<DeliveryInfoDto> list = historyService.mydeliverylist(adminInfo.getAdminId());

		if(list.size()==0) {
			String nullMsg = "現在、配達する料理はありません。";
			model.addAttribute("nullMsg",nullMsg);
		}

		model.addAttribute("list", list);

		return "my_delivery_list";
	}

	@RequestMapping(value= {"/confirm"}, method=RequestMethod.POST)
	public String confirm(@RequestParam("historyId") int historyId,Model model) {

		AdminInfoDto adminInfo = (AdminInfoDto)session.getAttribute("adminInfo");
		if(adminInfo == null) {
			return "redirect:/adminlogin";
		}

		DeliveryInfoDto dto = historyService.getDeliveryInfo(historyId);

		model.addAttribute("dto", dto);

		return "confirm_delivery";
	}

	@RequestMapping(value= {"/insert"}, method=RequestMethod.POST)
	public String insert(@RequestParam("historyId") int historyId) throws ParseException {

		AdminInfoDto adminInfo = (AdminInfoDto)session.getAttribute("adminInfo");
		if(adminInfo == null) {
			return "redirect:/adminlogin";
		}

		historyService.deliveryComplete(historyId, getNowDate());

		//制作者に配達完了したと通知メールを送る
		HistoryInfoDto dto = historyService.getHistoryInfo(historyId);
		mail.sendMail(dto.getCookUserMail(),"配達完了通知", dto.getCookUserName()+"様の料理を配達完了しました");

		return "redirect:/delivery/complete";
	}

	@RequestMapping(value= {"/complete"}, method=RequestMethod.GET)
	public String complete(Model model) {

		AdminInfoDto adminInfo = (AdminInfoDto)session.getAttribute("adminInfo");
		if(adminInfo == null) {
			return "redirect:/adminlogin";
		}

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
