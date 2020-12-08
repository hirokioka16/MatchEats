package com.example.demo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.example.demo.dto.CookingInfoDto;
import com.example.demo.dto.FoodInfoDto;
import com.example.demo.dto.GenreInfoDto;
import com.example.demo.dto.LoginInfoDto;
import com.example.demo.form.CookingForm;
import com.example.demo.service.CookingOfferService;
import com.example.demo.service.FoodService;
import com.example.demo.service.UserServise;

@Controller
public class CookingOfferController {

	@Autowired
	FoodService foodService;
	@Autowired
	CookingOfferService cookingOfferService;
	@Autowired
	HttpSession session;
	@Autowired
	UserServise userSercvice;
	@Autowired
	MailTest02Application mail;
	

	@RequestMapping(value= {"/inputoffer/input"}, method=RequestMethod.GET)
	public String input(@RequestParam("requestId") String requestId, @ModelAttribute("CookingForm")CookingForm form,Model model) {


		
		//オファーを送る食べたいものの情報を再取得
		int r_Id = Integer.parseInt(requestId);
		FoodInfoDto dto = foodService.getUdFoodList(Integer.parseInt(requestId));
		
		String genre = foodService.getGenreName(dto.getGenreId());
		

		session.setAttribute("requestId", r_Id);

		model.addAttribute("genre",genre);
		model.addAttribute("dto",dto);

		return "input_offer";
	}

	@RequestMapping(value= {"/inputoffer/confirm"}, method=RequestMethod.POST)
	public String confirm (@Validated @ModelAttribute("CookingForm")CookingForm form,BindingResult result,Model model) throws IllegalStateException, IOException {

		//画面の遷移先を保持する文字型変数
		String url = null;

		//入力エラーをチェック
			if(result.hasErrors()) {
					List<String> errorList = new ArrayList<String>();
					for(ObjectError error : result.getAllErrors()) {
						errorList.add(error.getDefaultMessage());
					}
					model.addAttribute("validationError", errorList);
					
					int requestId = (int)session.getAttribute("requestId");
					FoodInfoDto dto = foodService.getUdFoodList(requestId);
					String genre = foodService.getGenreName(dto.getGenreId());
					
					model.addAttribute("genre",genre);
					model.addAttribute("dto",dto);
					//エラーあり入力画面に戻す
					url = "input_offer";
				}else {

					//入力内容に問題がない時の処理
					CookingInfoDto dto = getCreateDto(form);

					//sessionよりLoginInfoを取得
					LoginInfoDto loginInfo = (LoginInfoDto)session.getAttribute("loginInfo");
					//userIdを取り出しDtoに格納
					dto.setUserId(String.valueOf(loginInfo.getUserId()));

					//本番用に変えた
					

					session.setAttribute("CookingInfoDto", dto);

					url="confirm_offer";
				}
		return url;

	}

	@RequestMapping(value= {"/inputoffer/insert"}, method=RequestMethod.POST)
	public String insert() throws java.text.ParseException {

		CookingInfoDto dto = (CookingInfoDto)session.getAttribute("CookingInfoDto");
		dto.setOfferDate(getNowDate());
		int requestId = (int)session.getAttribute("requestId");
		dto.setRequestId(String.valueOf(requestId));
		dto.setDeliveryFlg(false);
		dto.setReactionStatus("0");

		cookingOfferService.insert(dto);
		
		//料理制作をリクエストした人にオファーが来たとメールを送る
		FoodInfoDto foodDto = foodService.getUdFoodList(requestId);
		mail.sendMail(foodDto.getUserMail(),"調理オファーが届きました!",foodDto.getUserName()+"様が登録していた料理リクエストにオファーが届きました");
		

		session.removeAttribute("CookingInfoDto");
		return "redirect:/inputoffer/complete";
	}

	//オファー登録完了
	@RequestMapping(value= {"/inputoffer/complete"}, method=RequestMethod.GET)
	public String complete() {


		session.removeAttribute("requestId");

		return "complete_offer_input";

	}



	//オファー送信履歴
	@RequestMapping(value= {"cookingoffer/history"}, method=RequestMethod.GET)
	public String offerHistory(Model model) {

		LoginInfoDto loginInfo = (LoginInfoDto) session.getAttribute("loginInfo");

		//int userId = loginInfo.getUserId();
		//int userId = 1;

		List<CookingInfoDto> list = cookingOfferService.getOfferHistory(loginInfo.getUserId());

		model.addAttribute("list",list);

		return "offer_history";

	}

	//オファー取り消し確認
	@RequestMapping(value= {"cookingoffer/deleteconfirm"}, method=RequestMethod.POST)
	public String  deleteConfirm(@RequestParam("offerId") String offerId,Model model) {

		//削除するオファーの情報を再取得
		CookingInfoDto cookdto = cookingOfferService.getOfferInfo(Integer.parseInt(offerId));

		model.addAttribute("cookdto",cookdto);


		return "offer_delete_confirm";

	}

	//オファー取り消し
	@RequestMapping(value= {"cookingoffer/delete"},method=RequestMethod.POST)
	public String delete(@RequestParam("offerId") String offerId,Model model) {

		cookingOfferService.delete(offerId);

		return "redirect:/cookingoffer/deletecomplete";

	}

	//オファー取り消し完了
	@RequestMapping(value= {"/cookingoffer/deletecomplete"}, method=RequestMethod.GET)
	public String deletecomplete() {

		return "complete_delete_offer";
	}



	//formで入力した値をdtoに挿入するメソッド
	public CookingInfoDto getCreateDto(CookingForm form) {

		CookingInfoDto dto = new CookingInfoDto();

		dto.setPrice(form.getPrice() );
		dto.setOfferComment(form.getOfferComment());

		return dto;

	}


	//現在時刻を登録する
	public Date getNowDate() throws java.text.ParseException {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		//String型の日付をDate型に変更している
		SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
		Date now = d1.parse(strDate);

		return now;
	}


}
