package com.example.demo.repository;


import java.util.List;

<<<<<<< HEAD
=======
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> main
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
<<<<<<< HEAD

import com.example.demo.entity.FoodTblEntity;
=======
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FoodTblEntity;
import com.example.demo.entity.GenreTblEntity;
>>>>>>> main



@Transactional
@Repository
public interface FoodRepository extends JpaRepository<FoodTblEntity,Integer>{

	//テーブル結合の例
	@Query("SELECT f FROM FoodTblEntity f left join f.genreTbl g left join f.userTbl u")
    public List<FoodRepository> testFind();
<<<<<<< HEAD
	//SELECT f FROM FoodTblEntity f left join f.genreTbl g left join f.userTbl u


	@Query("SELECT f FROM FoodTblEntity f WHERE f.eatFlag = 0")
	public List<FoodTblEntity> getAllList();
=======
	
	//自分の投稿した食べたい物リストを取得する
	@Query("SELECT f FROM FoodTblEntity f left join f.userTbl u WHERE u.userId = :userId")
	public List<FoodTblEntity> getMyFoodList(@Param("userId") int userId);
	
	//+ " f.genreTbl = :genreId, "	@Param("genreId") int genreId,
	//食べたい物リスト修正
	@Modifying
	@Query("UPDATE FoodTblEntity f SET "
			+ " f.foodName = :foodName, "
			+ " f.requestOutline = :requestOutline, "
			+ " f.genreTbl = :genreId, "
			+ " f.eatFlag = :eatFlag "
			+ " WHERE f.requestId = :requestId ")
	public void update(
			@Param("foodName") String foodName,
			@Param("requestOutline") String requestOutline,
			@Param("genreId") GenreTblEntity genreId,
			@Param("eatFlag") String eat_flag,
			@Param("requestId") int requestId
			);
>>>>>>> main
}
