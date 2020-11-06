package com.example.demo.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.demo.entity.FoodTblEntity;
import com.example.demo.entity.GenreTblEntity;
import com.example.demo.entity.UserTblEntity;



public interface FoodRepository extends JpaRepository<FoodTblEntity,Integer>{

	//テーブル結合の例
	@Query("SELECT f FROM FoodTblEntity f left join f.genreTbl g left join f.userTbl u")
    public List<FoodRepository> testFind();
	
	//自分の投稿した食べたい物リストを取得する
	@Query("SELECT f FROM FoodTblEntity f left join f.userTbl u WHERE u.userId = :userId")
	public List<FoodTblEntity> getMyFoodList(@Param("userId") int userId);
}
