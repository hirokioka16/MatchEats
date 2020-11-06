package com.example.demo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.FoodInfoDto;
import com.example.demo.dto.GenreInfoDto;
import com.example.demo.form.FoodForm;
import com.example.demo.repository.FoodRepository;
import com.example.demo.service.FoodService;

@Controller
@RequestMapping(value= {"/inputfood"})
public class InputFoodListController {

	@Autowired
	FoodService foodService;
	@Autowired
	HttpSession session;
	@Autowired
	FoodRepository foodRepository;

	@RequestMapping(value= {"/input"}, method=RequestMethod.GET)
	public String input(@ModelAttribute("FoodForm")FoodForm form,Model model) {

		//料理のジャンルをDBから取得
		List<GenreInfoDto> list = foodService.getGenre();
		model.addAttribute("list",list);

		FoodInfoDto foodInfoDto = new FoodInfoDto();
		model.addAttribute("foodInfoDto",foodInfoDto);

		return "input_foodlist";
	}

	@RequestMapping(value= {"/confirm"}, method=RequestMethod.POST)
	public String confirm(@Validated @ModelAttribute("FoodForm")FoodForm form,BindingResult result,Model model) throws IllegalStateException, IOException {
		String url = null;
		//入力エラーをチェック
		if(result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for(ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			List<GenreInfoDto> list = foodService.getGenre();
			model.addAttribute("validationError", errorList);
			model.addAttribute("list",list);
			url = "input_foodlist";
		//画像が選択されていない場合
		}else if (form.getRequestPicture().isEmpty()) {
			List<GenreInfoDto> list = foodService.getGenre();
			model.addAttribute("list",list);
			url = "input_foodlist";
		}else{

			StringBuffer data = new StringBuffer();
	        String base64 = new String(Base64.encodeBase64(form.getRequestPicture().getBytes()),"ASCII");
	        data.append("data:image/jpeg;base64,");
	        data.append(base64);

	        model.addAttribute("image",data.toString());
	        model.addAttribute("genreName",getGenreName(Integer.parseInt(form.getGenreId())) );
			//formの値をdtoにいれるメソッドを呼んでいる
			FoodInfoDto dto = getCreateDto(form);
			session.setAttribute("foodInfDto", dto);
			url = "confirm_foodlist_input";
		}
		return url;
	}
	@RequestMapping(value= {"/insert"}, method=RequestMethod.POST)
	public String insert() throws java.text.ParseException {

		FoodInfoDto dto = (FoodInfoDto)session.getAttribute("foodInfDto");
		dto.setRegistDate(getNowDate());
		foodService.insert(dto);
		//画像の保存先
//		File destination = new File("/Users/hiroikeshouta/Desktop/upimg" + "/" + dto.getRequestPicture().getOriginalFilename());
		//画像保存処理
//		form.getRequestPicture().transferTo(destination);
//		form.setFileName(form.getRequestPicture().getOriginalFilename());
		session.removeAttribute("foodInfDto");
		return "redirect:/inputfood/complete";
	}

	@RequestMapping(value= {"/complete"}, method=RequestMethod.GET)
	public String complete() {

		return "complete_foodlist_input";
	}


	@RequestMapping(value= {"/test"}, method=RequestMethod.GET)
	public String test() {
		List<FoodRepository> list = foodRepository.testFind();
		return "complete_foodlist_input";
	}





	//formの値をdtoに入れているメソッド
	private FoodInfoDto getCreateDto(FoodForm form){
		FoodInfoDto dto = new FoodInfoDto ();
		dto.setFoodName(form.getFoodName());
		dto.setRequestOutline(form.getRequestOutline());
		dto.setGenreId(Integer.parseInt(form.getGenreId()));
		if(form.getEatFlag()==null) {
			dto.setEatFlag("0");
		}else {
			dto.setEatFlag(form.getEatFlag());
		}
		dto.setRequestPicture(form.getRequestPicture());
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
	//ジャンルIDからジャンル名を取得する
	public String getGenreName(int genreId) {
		List<GenreInfoDto> list = foodService.getGenre();
		String genreName="";
		for(int i=0;i<list.size();i++){
			if(list.get(i).getGenreId() == genreId) {
				genreName = list.get(i).getGenreName();
				break;
			}
		}
		return genreName;
	}
}
