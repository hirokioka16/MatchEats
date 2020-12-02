package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.TransferInfoDto;
import com.example.demo.service.TransferService;

@Controller
@RequestMapping(value= {"/adminmenu"})
public class AdminMenuController {

	@Autowired
	TransferService transferService;



	@RequestMapping(value= {"/menu"}, method=RequestMethod.POST)
	public String adminMenu() {
		return "adminMenu";

	}


	//振込申請確認
	@RequestMapping(value= {"/transfer"},method=RequestMethod.GET)
	public String  getTransferlist(Model model) {

		List<TransferInfoDto> list = transferService.getTransferList();
		model.addAttribute("list",list);

		return "transferlist";
	}


}
