package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AdminTblEntity;
import com.example.demo.entity.HistoryTblEntity;

@Transactional
@Repository
public interface HistoryRepository extends JpaRepository<HistoryTblEntity, Integer> {


	//自分が食事した履歴を取得する
	@Query("SELECT h FROM HistoryTblEntity h left join h.requestUser u WHERE u.userId = :userId")
	public List<HistoryTblEntity> getFoodList(@Param("userId") int userId);


	//自分が調理した履歴を取得する
	@Query("SELECT h FROM HistoryTblEntity h left join h.cookOfferUser u WHERE u.userId = :userId")
	public List<HistoryTblEntity> getCookList(@Param("userId") int userId);

	//offerIDから食べたいものを逆引きする
	@Query("SELECT f.foodName FROM FoodTblEntity f left join CookOfferTblEntity c WHERE c.offerId = :offerId")
	public String backFoodName(@Param("offerId") int offerId);

	//料理の回収登録をする
	@Modifying
	@Query("UPDATE HistoryTblEntity h SET "
				+" h.stateStatus = 1, "
				+" h.adminTbl = :adminId, "
				+" h.recoveryDate = :date "
				+" WHERE h.CookOfferTbl.offerId = :offerId")
	public void cookCollectionUpdate(
			@Param("adminId") AdminTblEntity adminId,
			@Param("offerId") Integer offerId,
			@Param("date") Date date
			);
	
	//料理の配達登録をする
		@Modifying
		@Query("UPDATE HistoryTblEntity h SET "
					+" h.stateStatus = 2, "
					+" h.deliveryCompleteDate = :date "
					+" WHERE h.historyId = :historyId")
		public void cookDeliveryUpdate(
				@Param("historyId") Integer historyId,
				@Param("date") Date date
				);
	
	//自分が配達しなければならない料理をオーダーした人の情報を取得する
	@Query("SELECT h FROM HistoryTblEntity h WHERE h.adminTbl = :adminId AND h.stateStatus != 2")
	public List<HistoryTblEntity>  getDeleveryUserInfo(
			@Param("adminId") AdminTblEntity adminId
			);
			
}
