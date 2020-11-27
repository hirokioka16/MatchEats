package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AdminTblEntity;
import com.example.demo.entity.UserTblEntity;

@Transactional
@Repository
public interface AdminRepository extends JpaRepository<UserTblEntity, Integer>  {

	@Query("select a from AdminTblEntity a where adminMail = :mail and  adminPass = :password")
	public AdminTblEntity login(@Param("mail")String mail,@Param("password")String password);

}
