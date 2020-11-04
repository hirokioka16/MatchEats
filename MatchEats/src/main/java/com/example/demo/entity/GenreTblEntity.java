package com.example.demo.entity;

<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.Id;
=======
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
>>>>>>> main
import javax.persistence.Table;

@Entity 
@Table(name="genre")
<<<<<<< HEAD
public class GenreTblEntity {
	
	@Id
	private Integer genreId;
	private String genreName;
	
	
=======
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
>>>>>>> main
	public Integer getGenreId() {
		return genreId;
	}
	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
		
	
}
