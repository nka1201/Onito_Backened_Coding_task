package com.onito.task.utility;

import com.onito.task.entity.Movies;
import com.onito.task.entity.Ratings;

public class MovieData {
	private Movies movies;
    private Ratings ratings;
    
    public MovieData() {
    }

    public MovieData(Movies movies, Ratings ratings) {
        this.movies = movies;
        this.ratings = ratings;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }
}
