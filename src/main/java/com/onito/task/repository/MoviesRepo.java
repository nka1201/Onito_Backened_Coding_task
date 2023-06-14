package com.onito.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onito.task.entity.Movies;

public interface MoviesRepo extends JpaRepository<Movies, String>{

}
