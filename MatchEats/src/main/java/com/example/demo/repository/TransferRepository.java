package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TransferId;
import com.example.demo.entity.TransferTblEntity;


@Repository
public interface TransferRepository extends JpaRepository<TransferTblEntity,TransferId>{

	//振込申請一覧取得
	//@Query("SELECT t FROM TransferTblEntity t WHERE t");

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


