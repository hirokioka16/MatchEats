package com.example.demo.repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.GenreTblEntity;

public interface FoodRepository extends JpaRepository<GenreTblEntity,Integer>{

=======

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.demo.entity.FoodTblEntity;
import com.example.demo.entity.GenreTblEntity;
import com.example.demo.entity.UserTblEntity;



public interface FoodRepository extends JpaRepository<FoodTblEntity,Integer>{

	@Query("SELECT f FROM FoodTblEntity f left join f.genreTbl g left join f.userTbl u")
    public List<FoodRepository> testFind();
	//SELECT f FROM FoodTblEntity f left join f.genreTbl g left join f.userTbl u
>>>>>>> main
}
