package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserTblEntity;
@Transactional
@Repository
public interface UserRepository extends JpaRepository<UserTblEntity, Integer> {

	@Query("select u from UserTblEntity u where userMail = :mail and  userPass = :password and accountType != 1")
	public UserTblEntity login(@Param("mail")String mail,@Param("password")String password);

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

	@Modifying
	@Query("UPDATE UserTblEntity u SET"
			+ " u.sales = 0 "
			+ " WHERE u.userId = :userId ")
	public void reset(
			@Param("userId")int userId);

	@Query("select u from UserTblEntity u where userMail = :mail and  userPass = :password")
	public UserTblEntity getUserInfo(@Param("mail")String mail,@Param("password")String password);

	@Modifying
	@Query("UPDATE UserTblEntity u SET"
			+ " u.accountType = 1 "
			+ " WHERE u.userId = :userId ")
	public void deleteUser(
			@Param("userId")int userId);

	@Modifying
	@Query("UPDATE UserTblEntity u SET"
			+ " u.sales = :sales "
			+ " WHERE u.userId = :userId ")
	public void updateSales(
			@Param("sales")int sales,@Param("userId")int userId);

	@Modifying
	@Query("UPDATE UserTblEntity u SET"
			+ " u.totalprice = :sales "
			+ " WHERE u.userId = :userId ")
	public void updateTotalPrice(
			@Param("sales")int sales,@Param("userId")int userId);

}