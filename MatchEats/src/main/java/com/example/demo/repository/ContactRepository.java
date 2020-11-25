package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ContactsTblEntity;

/**
 *
 * お問い合わせ情報 Repository
 *
 */
@Repository
public interface ContactRepository extends JpaRepository<ContactsTblEntity,Integer>{
//	@Query("SELECT c FROM ContactsTblEntity c")
//	public List<ContactsTblEntity> getContactList();
}
