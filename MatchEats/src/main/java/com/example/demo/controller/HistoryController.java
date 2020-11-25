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

import com.example.demo.dto.AssessmentInfoDto;
import com.example.demo.dto.CookingInfoDto;
import com.example.demo.dto.EatHistoryDetailDto;
import com.example.demo.dto.FoodInfoDto;
import com.example.demo.dto.HistoryInfoDto;
import com.example.demo.dto.LoginInfoDto;
import com.example.demo.form.AssessmentForm;
import com.example.demo.service.CookingOfferService;
import com.example.demo.service.FoodService;
import com.example.demo.service.HistoryService;
import com.example.demo.service.UserServise;

@Controller
public class HistoryController {

	@Autowired
	HistoryService historyService;
	@Autowired
	CookingOfferService cookService;
	@Autowired
	FoodService foodService;
	@Autowired
	UserServise userService;
	@Autowired
	HttpSession session;


	//食事・調理履歴一覧
	@RequestMapping(value= {"history/list"},method=RequestMethod.GET)
	public String getFoodList(Model model) {

		LoginInfoDto loginInfo = (LoginInfoDto)session.getAttribute("loginInfo");

		//int userId = loginInfo.getUserId();

		//テスト用
		int userId = 1;

		//食事履歴

		List<HistoryInfoDto> list = historyService.getFoodlist(userId);
		List<EatHistoryDetailDto> eatList = new ArrayList();

		for(HistoryInfoDto dto : list) {
			EatHistoryDetailDto eatDto = new EatHistoryDetailDto();

			eatDto.setHistoryId(dto.getHistoryId());

			//オファーIDをキーにしてオファーテーブルからリクエストIDを取得
			//リクエストIDをキーに食べたいものテーブルから情報取得、各値をセット
			CookingInfoDto offerDto = cookService.getOfferInfo(dto.getOfferId());
			FoodInfoDto foodInfoDto = foodService.getUdFoodList(Integer.parseInt(offerDto.getRequestId()));


			eatDto.setFoodName(foodInfoDto.getFoodName());
			eatDto.setRequestDate(foodInfoDto.getRegistDate());
			eatDto.setPrice(dto.getCookProfit()+dto.getAdminProfit());

			int state = dto.getStateStatus();
			switch(state) {
				case 0:
					eatDto.setStateStatus("未回収（調理中）");
					break;
				case 1:
					eatDto.setStateStatus("配達中");
					eatDto.setRecoveryDate(dto.getRecoveryDate());
					break;
				case 2:
					eatDto.setStateStatus("配達完了");
					eatDto.setDeliveryCompleteDate(dto.getDeliveryCompleteDate());
					break;
			}


			eatList.add(eatDto);
		}

		model.addAttribute("eatList",eatList);


		//調理履歴
		List<HistoryInfoDto> cList = historyService.getCookList(userId);
		List<EatHistoryDetailDto> cookList = new ArrayList();

		for(HistoryInfoDto cDto : list) {

			EatHistoryDetailDto cookDto = new EatHistoryDetailDto();
			cookDto.setHistoryId(cDto.getHistoryId());


			CookingInfoDto offerDto = cookService.getOfferInfo(cDto.getOfferId());
			FoodInfoDto foodInfoDto = foodService.getUdFoodList(Integer.parseInt(offerDto.getRequestId()));

			cookDto.setFoodName(foodInfoDto.getFoodName());
			cookDto.setOfferDate(offerDto.getOfferDate());

			cookDto.setProfit(cDto.getCookProfit());

			int state = cDto.getStateStatus();
			switch(state) {
			case 0:
				cookDto.setStateStatus("未回収（調理中）");
				break;
			case 1:
				cookDto.setStateStatus("配達中");
				cookDto.setRecoveryDate(cDto.getRecoveryDate());
				break;
			case 2:
				cookDto.setStateStatus("配達完了");
				cookDto.setDeliveryCompleteDate(cDto.getDeliveryCompleteDate());
				break;
		}

		cookList.add(cookDto);

		}

		model.addAttribute("cookList",cookList);




		return "eat_history";

	}



	//食事履歴詳細
	@RequestMapping(value={"history/eatdetail"},method=RequestMethod.POST)
	public String detailEat (@RequestParam("historyId") int historyId, @ModelAttribute("AssessmentForm")AssessmentForm form,Model model) {


		HistoryInfoDto dto = historyService.getEatInfo(historyId);
		session.setAttribute("historyId", historyId);

		EatHistoryDetailDto eatDto = new EatHistoryDetailDto();
		eatDto.setHistoryId(historyId);


		//オファーIDをとりだす
		//オファーの情報取ってくる

		CookingInfoDto offerDto = cookService.getOfferInfo(dto.getOfferId());

		FoodInfoDto foodDto = foodService.getUdFoodList(Integer.parseInt(offerDto.getRequestId()));
		eatDto.setFoodName(foodDto.getFoodName());

		//→作る人のuserIdをとる
		//userIdをキーにnicknameをとる
		String nickName = userService.getNickName(offerDto.getUserId());
		eatDto.setNickName(nickName);

		eatDto.setRequestOutline(foodDto.getRequestOutline());
		eatDto.setOfferComment(offerDto.getOfferComment());

		eatDto.setRequestDate(foodDto.getRegistDate());
		eatDto.setPrice(offerDto.getPrice());


		int state = dto.getStateStatus();
		switch(state) {
			case 0:
				eatDto.setStateStatus("未回収（調理中）");
				break;
			case 1:
				eatDto.setStateStatus("配達中");
				eatDto.setRecoveryDate(dto.getRecoveryDate());
				break;
			case 2:
				eatDto.setStateStatus("配達完了");
				eatDto.setDeliveryCompleteDate(dto.getDeliveryCompleteDate());
				break;
		}




		model.addAttribute("eatDto",eatDto);


		return "history_detail";
	}

	//調理履歴詳細
	@RequestMapping(value={"history/cookdetail"},method=RequestMethod.POST)
	public String detailCook (@RequestParam("historyId") int historyId,Model model) {

		HistoryInfoDto dto = historyService.getEatInfo(historyId);
		session.setAttribute("historyId", historyId);

		EatHistoryDetailDto cookDto = new EatHistoryDetailDto();
		cookDto.setHistoryId(historyId);

		CookingInfoDto offerDto = cookService.getOfferInfo(dto.getOfferId());


		//食べものの名称
		FoodInfoDto foodDto = foodService.getUdFoodList(Integer.parseInt(offerDto.getRequestId()));
		cookDto.setFoodName(foodDto.getFoodName());

		//食事する人のニックネーム
		//String nickName = userService.getNickName(String.valueOf(foodDto.getUserId()));
		//cookDto.setNickName(nickName);

		//リクエスト概要
		cookDto.setRequestOutline(foodDto.getRequestOutline());

		//オファーコメント
		cookDto.setOfferComment(offerDto.getOfferComment());

		//提示金額
		cookDto.setPrice(offerDto.getPrice());
		//利益
		cookDto.setProfit(dto.getCookProfit());
		//状態
		//回収日時
		//配達完了日時
		int state = dto.getStateStatus();
		switch(state) {
			case 0:
				cookDto.setStateStatus("未回収（調理中）");
				break;
			case 1:
				cookDto.setStateStatus("配達中");
				cookDto.setRecoveryDate(dto.getRecoveryDate());
				break;
			case 2:
				cookDto.setStateStatus("配達完了");
				cookDto.setDeliveryCompleteDate(dto.getDeliveryCompleteDate());
				break;
		}


		model.addAttribute("cookDto",cookDto);


		return "history_cookdetail";

	}



	//評価入力確認
	@RequestMapping(value="history/assessmentconfirm",method=RequestMethod.POST)
	public String confirm (@Validated @ModelAttribute("AssessmentForm")AssessmentForm form,BindingResult result,Model model) throws IllegalStateException, IOException {

		//画面の遷移先を保持する文字型変数
				String url = null;

				//入力エラーをチェック
					if(result.hasErrors()) {
							List<String> errorList = new ArrayList<String>();
							for(ObjectError error : result.getAllErrors()) {
								errorList.add(error.getDefaultMessage());
							}
							model.addAttribute("validationError", errorList);
							//エラーあり入力画面に戻す
							url = "history_detail";
						}else {

							AssessmentInfoDto dto = getCreateDto(form);

							dto.setHistoryId((int) session.getAttribute("historyId"));

							session.setAttribute("AssessmentInfoDto", dto);

							url="confirm_assessment";

						}

		return url;

	}

	@RequestMapping(value={"history/insertassessment"},method=RequestMethod.POST)
	public String insert() throws java.text.ParseException {

		AssessmentInfoDto dto = (AssessmentInfoDto)session.getAttribute("AssessmentInfoDto");
		//時間をセット
		dto.setAssessmentDate(getNowDate());

		historyService.insert(dto);

		session.removeAttribute("AssessmentInfoDto");



		return "redirect:/history/complete";

	}

	@RequestMapping(value= {"/history/complete"},method=RequestMethod.GET)
	public String complete() {

		return "complete_assessment_insert";

	}




	//formで入力した値をdtoに挿入するメソッド
	public AssessmentInfoDto getCreateDto(AssessmentForm form) {

		AssessmentInfoDto dto = new AssessmentInfoDto();

		dto.setPoint(form.getPoint());
		dto.setAssessmentComment(form.getA_comment());


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
