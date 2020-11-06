package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.UserTblEntity;

public interface UserRepository extends JpaRepository<UserTblEntity,Integer>{

	@Query("select u from UserTblEntity u where userMail = :mail and  userPass = :password")
	public UserTblEntity login(@Param("mail")String mail,@Param("password")String password);

	}
