package com.onito.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onito.task.entity.Ratings;

public interface RatingsRepo extends JpaRepository<Ratings, String>{

}
