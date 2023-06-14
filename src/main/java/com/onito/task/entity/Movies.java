package com.onito.task.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="movies")
public class Movies {
	@Id
	@Column(name = "tconst",nullable =false)
	private String tconst;
	
	@Column(name = "titleType",nullable =false)
	private String titleType;
	
	@Column(name = "primaryTitle",nullable =false)
	private String primaryTitle;
	
	@Column(name = "runtimeMinutes",nullable =false)
	private Long runtimeMinutes;
	
	@Column(name = "genres",nullable =false)
	private String genres;

	 
	public Movies() {
		super();
	}

	public Movies(String tconst, String titleType, String primaryTitle, Long runtimeMinutes, String genres) {
		super();
		this.tconst = tconst;
		this.titleType = titleType;
		this.primaryTitle = primaryTitle;
		this.runtimeMinutes = runtimeMinutes;
		this.genres = genres;
	}

	public String getTconst() {
		return tconst;
	}

	public void setTconst(String tconst) {
		this.tconst = tconst;
	}

	public String getTitleType() {
		return titleType;
	}

	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}

	public String getPrimaryTitle() {
		return primaryTitle;
	}

	public void setPrimaryTitle(String primaryTitle) {
		this.primaryTitle = primaryTitle;
	}

	public Long getRuntimeMinutes() {
		return runtimeMinutes;
	}

	public void setRuntimeMinutes(Long runtimeMinutes) {
		this.runtimeMinutes = runtimeMinutes;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}
	
	
	
}
