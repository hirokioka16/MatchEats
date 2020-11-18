package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.CookOfferTblEntity;

public interface CookingRepository  extends JpaRepository<CookOfferTblEntity,Integer>{


		@Query("SELECT c FROM CookOfferTblEntity c left join c.foodTbl f left join c.userTbl u WHERE u.userId = :userId AND c.reactionStatus = 2")
	    public List<CookOfferTblEntity> getList(@Param("userId")int userId);
		//SELECT f FROM FoodTblEntity f left join f.genreTbl g left join f.userTbl u

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
}


