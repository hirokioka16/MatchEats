package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.sql.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.UserTblEntity;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<UserTblEntity, Integer> {
	
	
	
	//会員情報修正
	@Modifying
	@Query("UPDATE UserTblEntity u SET "
			+ " u.userMail = :userMail, "
			+ " u.userPass = :userPass, "
			+ " u.nickname = :nickname, "
			+ " u.userName = :userName, "
			+ " u.userTel = :userTel, "
			+ " u.postalCode = :postalCode, "
			+ " u.userAddres = :userAddres, "
			+ " u.userBirth = :userBirth, "
			+ " u.cardName = :cardName, "
			+ " u.userCard = :userCard, "
			+ " u.limitDate = :limitDate, "
			+ " u.secureCode = :secureCode "
			+ " WHERE u.userId = :userId ")
	public void update(
			@Param("userId") Integer userId,
			@Param("userMail") String userMail,
			@Param("userPass") String userPass,
			@Param("nickname") String nickName,
			@Param("userName") String userName,
			@Param("userTel") String userTel,
			@Param("postalCode") String postalCode,
			@Param("userAddres") String userAddres,
			@Param("userBirth") java.util.Date date,
			@Param("cardName") String cardName,
			@Param("userCard") String userCard,
			@Param("limitDate") String limitDate,
			@Param("secureCode") String secureCode
			);
	

}
