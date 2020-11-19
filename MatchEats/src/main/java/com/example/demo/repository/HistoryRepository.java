package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.HistoryTblEntity;

@Transactional
@Repository
public interface HistoryRepository extends JpaRepository<HistoryTblEntity,Integer>{


	//自分が食事した履歴を取得する
	@Query("SELECT h FROM HistoryTblEntity h left join h.cookOfferUser u WHERE u.userId = :userId")
	public List<HistoryTblEntity> getFoodList(@Param("userId") int userId);

	//offerIDから食べたいものを逆引きする
	@Query("SELECT f.foodName FROM FoodTblEntity f left join CookOfferTblEntity c WHERE c.offerId = :offerId")
	public String backFoodName(@Param("offerId") int offerId);
}
