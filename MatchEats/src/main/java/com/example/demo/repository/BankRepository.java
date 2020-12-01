package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BankTblEntity;
import com.example.demo.entity.BankUserId;
import com.example.demo.entity.UserTblEntity;

public interface BankRepository extends JpaRepository<BankTblEntity, BankUserId> {

}
