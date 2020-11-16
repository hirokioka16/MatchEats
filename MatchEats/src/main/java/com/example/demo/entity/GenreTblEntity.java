package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="genre")
public class GenreTblEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer genreId;

	private String genreName;

	@OneToMany(mappedBy="genreTbl")
	private List<FoodTblEntity> foodList;


	public List<FoodTblEntity> getFoodList() {
		return foodList;
	}
	public void setFoodList(List<FoodTblEntity> foodList) {
		this.foodList = foodList;
	}
	public Integer getGenreId() {
		return genreId;
	}
	public void setGenreId(int i) {
		this.genreId = i;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
		
	
}


}