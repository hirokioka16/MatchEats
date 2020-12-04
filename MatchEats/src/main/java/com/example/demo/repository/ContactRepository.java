package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ContactsTblEntity;

/**
 *
 * お問い合わせ情報 Repository
 *
 */
@Repository
public interface ContactRepository extends JpaRepository<ContactsTblEntity,Integer>{

	@Modifying
	@Query("SELECT c FROM ContactsTblEntity c ORDER BY c.contactDate DESC")
	public List<ContactsTblEntity> getAll();
//	@Query("SELECT c FROM ContactsTblEntity c")
//	public List<ContactsTblEntity> getContactList();
}
