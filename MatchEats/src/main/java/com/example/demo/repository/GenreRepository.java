package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.GenreTblEntity;
@Repository
public interface GenreRepository extends JpaRepository<GenreTblEntity,Integer>{

}
