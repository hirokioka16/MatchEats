package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CookOfferTblEntity;

@Repository
public interface CookingOfferRepository extends JpaRepository<CookOfferTblEntity, Integer> {


	@Query("SELECT o FROM CookOfferTblEntity o left join o.userTbl u WHERE u.userId = :userId")
	public List<CookOfferTblEntity> getOfferHistory(@Param("userId") int userId);

}
