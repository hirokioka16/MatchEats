package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AssessmentTblEntity;
@Transactional
@Repository
public interface AssessmentRepository extends JpaRepository<AssessmentTblEntity, Integer>{

	//評価登録済み判断用メソッド
	@Query("SELECT a FROM AssessmentTblEntity a WHERE history_id = :historyId")
	public AssessmentTblEntity getAssessment(@Param("historyId") int historyId);
}
