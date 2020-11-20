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

	//
	public List<Integer> getAllRequestId(){

		List<Integer> list = new ArrayList<Integer>();
		List<FoodTblEntity> entityList = foodRepository.findAll();

		for(FoodTblEntity entity : entityList ) {

			list.add(entity.getRequestId());
		}
		return list;
	}
	//ジャンルを取得する
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
	//自分の投稿した食べたい物リストを表示する
	public List<FoodInfoDto> getMyFoodList(int userId){

		List<FoodInfoDto> list = new ArrayList<FoodInfoDto>();
		List<FoodTblEntity> entityList = foodRepository.getMyFoodList(userId);

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
	//自分が投稿した食べたい物リストを修正する
	public void update(FoodInfoDto dto) {

		FoodTblEntity foodEntity = new FoodTblEntity();

		foodEntity.setRequestId(dto.getRequestId());
		foodEntity.setFoodName(dto.getFoodName());
		foodEntity.setRequestOutline(dto.getRequestOutline());
		foodEntity.setEatFlag(dto.getEatFlag());
		GenreTblEntity genreEntiry = new GenreTblEntity();
		genreEntiry.setGenreId(dto.getGenreId());

		foodEntity.setGenreTbl(genreEntiry);

		foodRepository.update(foodEntity.getFoodName(), foodEntity.getRequestOutline(),foodEntity.getGenreTbl(), foodEntity.getEatFlag(), foodEntity.getRequestId());
	}


	//自分の食べたい物リストの修正または削除するデータを持ってくる
	public FoodInfoDto getUdFoodList(int requestId){

		FoodTblEntity entity = foodRepository.getOne(requestId);
		FoodInfoDto dto = new FoodInfoDto();

		dto.setRequestId(entity.getRequestId());
		dto.setFoodName(entity.getFoodName());
		dto.setRequestOutline(entity.getRequestOutline());
		dto.setGenreId(entity.getGenreTbl().getGenreId());
		dto.setEatFlag(entity.getEatFlag());
		dto.setPictureName(entity.getRequestPicture());
		return dto;
	}

	//dtoの値をentityに入れるメソッド
	private FoodTblEntity change(FoodInfoDto dto) {

		FoodTblEntity foodEntity = new FoodTblEntity();

		foodEntity.setRequestId(dto.getRequestId());
		foodEntity.setFoodName(dto.getFoodName());
		foodEntity.setRequestOutline(dto.getRequestOutline());
		foodEntity.setRegistDate(dto.getRegistDate());
		foodEntity.setEatFlag(dto.getEatFlag());
		foodEntity.setRequestPicture(dto.getPictureName());

		UserTblEntity userEntity = new UserTblEntity();
		userEntity.setUserId(dto.getUserId());

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

		public List<FoodInfoDto> search(String keyword){

			List<FoodInfoDto> list = new ArrayList<FoodInfoDto>();
			List<FoodTblEntity> entityList = foodRepository.search(keyword);

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