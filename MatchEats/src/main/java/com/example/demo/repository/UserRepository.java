package com.example.demo.repository;

import org.springframework.data.repository.query.Param;

public class UserRepository extends JpaRepository<UserTblEntity,Integer>{

	Query("select u from UserTblEntity uwhere adminEmail = :mail and adminPass = :password")
	public UserTblEntity login(@Param("mail")String mail,@Param("password")String password) {

	}


}
