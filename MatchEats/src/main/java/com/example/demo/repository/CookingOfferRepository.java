package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CookOfferTblEntity;
@Repository
@Transactional
public interface CookingOfferRepository extends JpaRepository<CookOfferTblEntity, Integer> {


	@Query("SELECT o FROM CookOfferTblEntity o left join o.userTbl u WHERE u.userId = :userId")
	public List<CookOfferTblEntity> getOfferHistory(@Param("userId") int userId);

	/**
	@Transactional
	@Modifying
	@Query("UPDATE CookOfferTblEntity c SET"
				+" c.reactionStatus = :reactionStatus "
				+" WHERE c.offerId = :offerId")
	public void update(
					@Param("reactionStatus") String reactionStatus,
					@Param("offerId") Integer offerId
			);
	**/
	
	@Query("SELECT o FROM CookOfferTblEntity o left join o.userTbl u WHERE o.deliveryFlg = :deliveryFlg")
	public List<CookOfferTblEntity> getDeliveryList(@Param("deliveryFlg") boolean deliveryFlg);

	@Modifying
	@Query("UPDATE CookOfferTblEntity c SET"
			+ " c.approvalRequestDeliveryDate = :date "
			+ " WHERE c.offerId = :offerId ")
	public void setApprovalDate(
			@Param("date") Date date,
			@Param("offerId") int offerId
			);
	
}
