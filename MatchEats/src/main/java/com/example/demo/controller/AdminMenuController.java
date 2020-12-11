package com.example.demo.controller;

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

import com.example.demo.dto.TransferInfoDto;
import com.example.demo.service.TransferService;
import com.example.demo.service.UserServise;

@Controller
@RequestMapping(value= {"/adminmenu"})
public class AdminMenuController {

	@Autowired
	TransferService transferService;
	@Autowired
	UserServise userService;
	@Autowired
	HttpSession session;



	@RequestMapping(value= {"/menu"}, method=RequestMethod.GET)
	public String adminMenu() {

		return "adminMenu";

	}


	//振込申請一覧
	@RequestMapping(value= {"/transfer"},method=RequestMethod.GET)
	public String  getTransferlist(Model model) {

		List<TransferInfoDto> list = transferService.getTransferList();
		model.addAttribute("list",list);

		return "transferlist";
	}

	//申請確認
	@RequestMapping(value= {"/transfer/confirmapproval"},method=RequestMethod.POST)
	public String confirmApproval(@RequestParam("id") int  transferId,Model model) {

			TransferInfoDto dto = transferService.getInfo(transferId);

			model.addAttribute("transferId",transferId);
			model.addAttribute("transferDto",dto);


		return "confirm_approval";

	}

	//承認登録
	@RequestMapping(value= {"/transfer/insertapproval"},method=RequestMethod.POST)
	public String insertApproval(@RequestParam("transferId") int  transferId) throws java.text.ParseException {

		Date now = getNowDate();

		transferService.insertApproval(now,transferId);

		return "redirect:/adminmenu/transfer";
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
