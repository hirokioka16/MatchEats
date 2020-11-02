package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.FoodRepository;
import com.example.demo.dto.FoodInfoDto;
import com.example.demo.dto.GenreInfoDto;
import com.example.demo.entity.GenreTblEntity;



@Service
public class FoodService {
	
	@Autowired
	FoodRepository foodRepository;

	public List<GenreInfoDto> getGenre(){
		
		List<GenreInfoDto> list = new ArrayList<GenreInfoDto>();
		List<GenreTblEntity> entityList = foodRepository.findAll();
		
		for(GenreTblEntity entity : entityList ) {
			
			GenreInfoDto dto = new GenreInfoDto();
			
			dto.setGenreId(entity.getGenreId());
			dto.setGenreName(entity.getGenreName());
			
			list.add(dto);
		}
		
		return list;
	}
	
	public void insert(FoodInfoDto dto) {
		
		
	}
}
