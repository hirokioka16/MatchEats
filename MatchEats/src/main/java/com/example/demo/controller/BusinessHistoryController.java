package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.CookingInfoDto;
import com.example.demo.dto.EatHistoryDetailDto;
import com.example.demo.dto.FoodInfoDto;
import com.example.demo.dto.HistoryInfoDto;
import com.example.demo.service.CookingOfferService;
import com.example.demo.service.FoodService;
import com.example.demo.service.HistoryService;
import com.example.demo.service.UserServise;

@Controller
public class BusinessHistoryController {

	@Autowired
	HistoryService historyService;
	@Autowired
	CookingOfferService cookService;
	@Autowired
	FoodService foodService;
	@Autowired
	UserServise userService;

	@RequestMapping(value={"/businesshistory"},method=RequestMethod.GET)
	public String getHistory(Model model) {

		List<HistoryInfoDto> list = historyService.getHistory();
		List<EatHistoryDetailDto> historyList = new ArrayList();


		for(HistoryInfoDto dto : list) {
			EatHistoryDetailDto historyDto = new EatHistoryDetailDto();

			historyDto.setHistoryId(dto.getHistoryId());

			CookingInfoDto offerDto = cookService.getOfferInfo(dto.getOfferId());
			FoodInfoDto foodInfoDto = foodService.getUdFoodList(Integer.parseInt(offerDto.getRequestId()));

			String requestUsername = userService.getTrueName(foodInfoDto.getUserId());
			String cookerName = userService.getTrueName(Integer.parseInt(offerDto.getUserId()));

			historyDto.setRequestUsername(requestUsername);
			historyDto.setCookerName(cookerName);

			historyDto.setOfferDate(offerDto.getOfferDate());
			historyDto.setDeliveryCompleteDate(dto.getDeliveryCompleteDate());

			historyDto.setPrice(offerDto.getPrice());

			historyList.add(historyDto);

		}


		model.addAttribute("historyList",historyList);



		return "disp_all_history";

	}

}
