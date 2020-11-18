package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CookOfferTblEntity;
@Repository
public interface CookingOfferRepository extends JpaRepository<CookOfferTblEntity, Integer> {




}
