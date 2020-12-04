package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TransferId;
import com.example.demo.entity.TransferTblEntity;


@Repository
public interface TransferRepository extends JpaRepository<TransferTblEntity,TransferId>{

	@Modifying
	@Query("SELECT t FROM TransferTblEntity t WHERE t.acceptFlag = false")
	public List<TransferTblEntity> getAll();

	@Modifying
	@Query("UPDATE TransferTblEntity t SET t.acceptDate = :now WHERE t.transferId = :transferId")
	public void insertApproval(@Param("now") Date now,@Param("transferId") TransferId transferId);

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


