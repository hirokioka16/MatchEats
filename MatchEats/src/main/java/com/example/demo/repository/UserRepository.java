package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserTblEntity;

@Repository
public interface UserRepository extends JpaRepository<UserTblEntity, Integer> {
	
	

}
