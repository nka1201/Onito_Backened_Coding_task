package com.onito.task.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ratings")
public class Ratings {
    
	@Id
	@Column(name = "tconst",nullable =false)
	private String tconst;
	
	@Column(name = "averageRating",nullable =false)
	private Float averageRating;
	
	@Column(name = "numVotes",nullable =false)
	private Long numVotes;

	public Ratings() {
		super();
	}

	public Ratings(String tconst, Float averageRating, Long numVotes) {
		super();
		this.tconst = tconst;
		this.averageRating = averageRating;
		this.numVotes = numVotes;
	}

	public String getTconst() {
		return tconst;
	}

	public void setTconst(String tconst) {
		this.tconst = tconst;
	}

	public float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Float averageRating) {
		this.averageRating = averageRating;
	}

	public Long getNumVotes() {
		return numVotes;
	}

	public void setNumVotes(Long numVotes) {
		this.numVotes = numVotes;
	}
	
}
