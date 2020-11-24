package com.example.demo.controller;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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
import com.example.demo.dto.LoginInfoDto;
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

		FoodInfoDto dto = new FoodInfoDto();
		String genreName="";

		//確認画面で戻るボタンを押下した時に前回までの入力内容を入力画面に反映させる処理
		try {
			dto = (FoodInfoDto)session.getAttribute("foodInfDto");
		}catch (NullPointerException e){
		}
		if(dto != null) {
			form.setFoodName(dto.getFoodName());
			form.setRequestOutline(dto.getRequestOutline());
			form.setGenreId(String.valueOf(dto.getGenreId()));
			form.setGenreName(getGenreName(dto.getGenreId()));
			//戻った場合画像を確認画面で保存した画像を消す処理
			File file = new File("/Users/hiroikeshouta/Desktop/img" + "/" + dto.getPictureName());
		    if (file.exists()){
		        if (file.delete()){
		          System.out.println("ファイルを削除しました");
		        }else{
		          System.out.println("ファイルの削除に失敗しました");
		        }
		    }else{
		        System.out.println("ファイルが見つかりません");
		      }
			session.removeAttribute("foodInfDto");
		}
		model.addAttribute("name",genreName);
		model.addAttribute("list",list);
		return "input_foodlist";
	}

	@RequestMapping(value= {"/confirm"}, method=RequestMethod.POST)
	public String confirm(@Validated @ModelAttribute("FoodForm")FoodForm form,BindingResult result,Model model) throws IllegalStateException, IOException {
		String url = null;
		
		if(form.getGenreId().equals("")) {
			form.setGenreId("1");
		}
		
		//入力エラーをチェック
		if(result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for(ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			List<GenreInfoDto> list = foodService.getGenre();
			model.addAttribute("validationError", errorList);
			model.addAttribute("list",list);
			form.setGenreName(getGenreName(Integer.parseInt(form.getGenreId())));
			url = "input_foodlist";
		//画像が選択されていない場合
		}else if (form.getRequestPicture().isEmpty() ){
			List<GenreInfoDto> list = foodService.getGenre();
			model.addAttribute("list",list);
			model.addAttribute("errImg","画像を選択してください");
			form.setGenreName(getGenreName(Integer.parseInt(form.getGenreId())));
			url = "input_foodlist";
		//画像以外が選択された時
		}else if (!form.getRequestPicture().getOriginalFilename().substring(form.getRequestPicture().getOriginalFilename().lastIndexOf(".")).equals(".png") && 
					!form.getRequestPicture().getOriginalFilename().substring(form.getRequestPicture().getOriginalFilename().lastIndexOf(".")).equals(".jpg") && 
					!form.getRequestPicture().getOriginalFilename().substring(form.getRequestPicture().getOriginalFilename().lastIndexOf(".")).equals(".jpeg")) {
			List<GenreInfoDto> list = foodService.getGenre();
			model.addAttribute("list",list);
			model.addAttribute("errImg","画像以外が選択されています");
			form.setGenreName(getGenreName(Integer.parseInt(form.getGenreId())));
			url = "input_foodlist";
		}else{

			StringBuffer data = new StringBuffer();
	        String base64 = new String(Base64.encodeBase64(form.getRequestPicture().getBytes()),"ASCII");
	        data.append("data:image/jpeg;base64,");
	        data.append(base64);

	        model.addAttribute("image",data.toString());
	        model.addAttribute("genreName",getGenreName(Integer.parseInt(form.getGenreId())) );
	       
	        //ファイルを保存するメソッド
	        form.setFileName(saveFile(form));
	        
			//formの値をdtoにいれるメソッドを呼んでいる
			FoodInfoDto dto = getCreateDto(form);
			session.setAttribute("foodInfDto", dto);
			url = "confirm_foodlist_input";
		}
		return url;
	}
	@RequestMapping(value= {"/insert"}, method=RequestMethod.POST)
	public String insert() throws java.text.ParseException, IllegalStateException, IOException {

		FoodInfoDto dto = (FoodInfoDto)session.getAttribute("foodInfDto");
		LoginInfoDto loginInfo  = (LoginInfoDto)session.getAttribute("loginInfo");
		dto.setUserId(loginInfo.getUserId());
		
		dto.setRegistDate(getNowDate());
		     		
		foodService.insert(dto);
		
		session.removeAttribute("foodInfDto");
		return "redirect:/inputfood/complete";
	}

	@RequestMapping(value= {"/complete"}, method=RequestMethod.GET)
	public String complete() {

		return "complete_foodlist_input";
	}




	//formの値をdtoに入れているメソッド
	public FoodInfoDto getCreateDto(FoodForm form){
		FoodInfoDto dto = new FoodInfoDto ();
		dto.setRequestId(form.getRequestId());
		dto.setFoodName(form.getFoodName());
		dto.setRequestOutline(form.getRequestOutline());
		dto.setGenreId(Integer.parseInt(form.getGenreId()));
		dto.setPictureName(form.getFileName());
		
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
    public static void makeDir(String dir){
        //Fileオブジェクトを生成する
        File f = new File(dir);

        if (!f.exists()) {
            //フォルダ作成実行
            f.mkdirs();
        }
    }
    
    public String saveFile(FoodForm form) {
    	
		//画像の名前が被らないように画像の名前の後にランダムな値を配置する 
    	String strDate = UUID.randomUUID().toString();
		
        int extension = form.getRequestPicture().getOriginalFilename().lastIndexOf(".");
        String resultName="";
        List<String> distinction = Arrays.asList(form.getRequestPicture().getOriginalFilename().split(""));
        for(int i = 0;i<distinction.size();i++) {
        	if(i == extension -1) {
        		resultName = resultName + distinction.get(i) + strDate;
        	}else {
        		resultName = resultName + distinction.get(i);
        	}
        }
      //画像の保存先
		File destination = new File("/Users/hiroikeshouta/Desktop/img" + "/" + resultName);
		makeDir(String.valueOf(destination));
		//画像保存処理
		try {
			form.getRequestPicture().transferTo(destination);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return resultName;
    }
}