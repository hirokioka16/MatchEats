package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.FoodTblEntity;



public interface FoodRepository extends JpaRepository<FoodTblEntity,Integer>{

	@Query("SELECT f FROM FoodTblEntity f left join f.genreTbl g left join f.userTbl u")
    public List<FoodRepository> testFind();
	//SELECT f FROM FoodTblEntity f left join f.genreTbl g left join f.userTbl u


	@Query("SELECT f FROM FoodTblEntity f WHERE f.eatFlag = 0")
	public List<FoodTblEntity> getAllList();
}
