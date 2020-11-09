package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.FoodInfoDto;
import com.example.demo.dto.GenreInfoDto;
import com.example.demo.entity.FoodTblEntity;
import com.example.demo.entity.GenreTblEntity;
import com.example.demo.entity.UserTblEntity;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.GenreRepository;



@Service
public class FoodService {

	@Autowired
	FoodRepository foodRepository;
	@Autowired
	GenreRepository genreRepository;

	public List<GenreInfoDto> getGenre(){

		List<GenreInfoDto> list = new ArrayList<GenreInfoDto>();
		List<GenreTblEntity> entityList = genreRepository.findAll();


		for(GenreTblEntity entity : entityList ) {

			GenreInfoDto dto = new GenreInfoDto();

			dto.setGenreId(entity.getGenreId());
			dto.setGenreName(entity.getGenreName());

			list.add(dto);
		}

		return list;
	}

	public void insert(FoodInfoDto dto) {

		FoodTblEntity foodEntity = change(dto);
		foodRepository.saveAndFlush(foodEntity);

	}

	//dtoの値をentityに入れるメソッド
	private FoodTblEntity change(FoodInfoDto dto) {

		FoodTblEntity foodEntity = new FoodTblEntity();

		foodEntity.setFoodName(dto.getFoodName());
		foodEntity.setRequestOutline(dto.getRequestOutline());
		foodEntity.setRegistDate(dto.getRegistDate());
		foodEntity.setEatFlag(dto.getEatFlag());
		foodEntity.setRequestPicture(dto.getRequestPicture().getOriginalFilename());

		UserTblEntity userEntity = new UserTblEntity();
		//テスト用の値
		userEntity.setUserId(1);

		GenreTblEntity genreEntiry = new GenreTblEntity();
		genreEntiry.setGenreId(dto.getGenreId());


		foodEntity.setUserTbl(userEntity);
		foodEntity.setGenreTbl(genreEntiry);
		return foodEntity;

	}

	//全ユーザーの食べたい物をDBから取得する
	public List<FoodInfoDto> getAllList(){

		List<FoodInfoDto> list = new ArrayList<FoodInfoDto>();
		List<FoodTblEntity> entityList = foodRepository.getAllList();

		for(FoodTblEntity entity:entityList) {

			FoodInfoDto dto = new FoodInfoDto();
			dto.setRequestId(entity.getRequestId());
			dto.setFoodName(entity.getFoodName());
			dto.setRegistDate(entity.getRegistDate());
			dto.setPictureName(entity.getRequestPicture());

			list.add(dto);
		}

		return list;


	}

}
