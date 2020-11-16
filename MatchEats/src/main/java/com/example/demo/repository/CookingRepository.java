package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.CookOfferTblEntity;

public interface CookingRepository  extends JpaRepository<CookOfferTblEntity,Integer>{


		@Query("SELECT c FROM CookOfferTblEntity c left join c.foodTbl f left join c.userTbl u WHERE u.userId = :userId AND c.reactionStatus = 2")
	    public List<CookOfferTblEntity> getList(@Param("userId")int userId);
		//SELECT f FROM FoodTblEntity f left join f.genreTbl g left join f.userTbl u
}


