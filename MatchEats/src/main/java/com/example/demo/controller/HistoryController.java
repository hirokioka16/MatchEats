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

@Controller
public class HistoryController {

	@Autowired
	HistoryService historyService;
	@Autowired
	CookingOfferService cookService;
	@Autowired
	FoodService foodService;
	@Autowired
	HttpSession session;


	//食事履歴一覧
	@RequestMapping(value= {"history/list"},method=RequestMethod.GET)
	public String getFoodList(Model model) {

		LoginInfoDto loginInfo = (LoginInfoDto)session.getAttribute("loginInfo");

		//int userId = loginInfo.getUserId();

		//テスト用
		int userId = 1;
		int offerId = 1;

		List<HistoryInfoDto> list = historyService.getFoodlist(userId);
		List<EatHistoryDetailDto> eatList = new ArrayList();

		for(HistoryInfoDto dto : list) {
			EatHistoryDetailDto eatDto = new EatHistoryDetailDto();

			eatDto.setHistoryId(dto.getHistoryId());

			//食べものの名前とリクエスト日時を取得したい
			//オファーIDをキーにしてオファーテーブルからリクエストIDを取得
			//リクエストIDをキーに食べたいものテーブルから情報取得、各値をセット
			CookingInfoDto cookDto = cookService.getOfferInfo(dto.getOfferId());
			FoodInfoDto foodInfoDto = foodService.getUdFoodList(Integer.parseInt(cookDto.getRequestId()));


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

		return "eat_history";

	}



	//履歴詳細
	@RequestMapping(value={"history/detail"},method=RequestMethod.POST)
	public String detailFood (@RequestParam("historyId") int historyId, @ModelAttribute("AssessmentForm")AssessmentForm form,Model model) {


		HistoryInfoDto dto = historyService.getEatInfo(historyId);

		session.setAttribute("historyId", historyId);

		model.addAttribute("dto",dto);


		return "history_detail";
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
