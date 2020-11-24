package com.example.demo.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.TransferId;
import com.example.demo.entity.TransferTblEntity;

public interface TransferRepository extends JpaRepository<TransferTblEntity,TransferId>{
	
//	@Modifying
//	@Query("UPDATE TransferTblEntity t SET "
//			+ " t.price= :price, "
//			+ " t.transferDate = :transferDate "
//			+ " WHERE t.transferId = :userId ")
//	public void update(
//			@Param("price") int price,
//			@Param("transferDate") Date date,
//			@Param("userId") int userId
//			);
}


