package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.CookOfferTblEntity;
@Repository
@Transactional
public interface CookingOfferRepository extends JpaRepository<CookOfferTblEntity, Integer> {


	@Query("SELECT o FROM CookOfferTblEntity o left join o.userTbl u WHERE u.userId = :userId AND o.reactionStatus IN ('0')")
	public List<CookOfferTblEntity> getOfferHistory(@Param("userId") int userId);


	@Modifying
	@Query("UPDATE CookOfferTblEntity c SET"
				+" c.reactionStatus = :reactionStatus "
				+" WHERE c.offerId = :offerId")
	public void update(
					@Param("reactionStatus") String reactionStatus,
					@Param("offerId") Integer offerId
			);
	
	//配達リクエストがきた料理の一覧
	@Query("SELECT o FROM CookOfferTblEntity o left join o.userTbl u WHERE o.deliveryFlg = :deliveryFlg AND o.approvalDeliveryStatus = null")
	public List<CookOfferTblEntity> getDeliveryList(@Param("deliveryFlg") boolean deliveryFlg);
	
//	
//	@Query("SELECT c.userID, f.userTbl.userName FROM CookOfTblEntity WHERE c.userTbl.userId = :userId AND c.reactionStatus = '0'")
//	public List<CookOfferTblEntity> getOfferUser
	
	
	@Query("SELECT c FROM CookOfferTblEntity c left join c.foodTbl f WHERE f.userTbl.userId = :userId AND c.reactionStatus = '0'")
	public List<CookOfferTblEntity> getReactionList(@Param("userId") int userId);
	

	@Modifying
	@Query("UPDATE CookOfferTblEntity c SET"
			+ " c.approvalRequestDeliveryDate = :date, "
			+ " c.approvalDeliveryStatus = 1 "
			+ " WHERE c.offerId = :offerId ")
	public void setApprovalDate(
			@Param("date") Date date,
			@Param("offerId") int offerId
			);
	
	@Query("SELECT c FROM CookOfferTblEntity c left join c.userTbl u WHERE  c.approvalDeliveryStatus = 1")
	public List<CookOfferTblEntity> getRequestAPProvalList(@Param("status") String status);
	
	
	@Modifying
	@Query("UPDATE CookOfferTblEntity c SET"
			+ " c.approvalDeliveryStatus = :status "
			+ " WHERE c.offerId = :offerId "
			)
	public void changeApprovalDeliveryStatus(
			@Param("status") String status,
			@Param("offerId") int offerId);
	
	@Modifying
	@Query("UPDATE CookOfferTblEntity c SET"
			+ " c.reactionStatus = 2 "
			+ " WHERE c.offerId = :offerId "
			)
	public void rejectOffer(
			@Param("offerId") int offerId);
}
