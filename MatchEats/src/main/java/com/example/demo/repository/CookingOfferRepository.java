package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.CookOfferTblEntity;
@Repository
public interface CookingOfferRepository extends JpaRepository<CookOfferTblEntity, Integer> {


	@Query("SELECT o FROM CookOfferTblEntity o left join o.userTbl u WHERE u.userId = :userId AND o.reactionStatus IN ('0')")
	public List<CookOfferTblEntity> getOfferHistory(@Param("userId") int userId);


	@Transactional
	@Modifying
	@Query("UPDATE CookOfferTblEntity c SET"
				+" c.reactionStatus = :reactionStatus "
				+" WHERE c.offerId = :offerId")
	public void update(
					@Param("reactionStatus") String reactionStatus,
					@Param("offerId") Integer offerId
			);
	
//	
//	@Query("SELECT c.userID, f.userTbl.userName FROM CookOfTblEntity WHERE c.userTbl.userId = :userId AND c.reactionStatus = '0'")
//	public List<CookOfferTblEntity> getOfferUser
	
	
	@Query("SELECT c FROM CookOfferTblEntity c left join c.foodTbl f WHERE f.userTbl.userId = :userId AND c.reactionStatus = '0'")
	public List<CookOfferTblEntity> getReactionList(@Param("userId") int userId);
	

}
