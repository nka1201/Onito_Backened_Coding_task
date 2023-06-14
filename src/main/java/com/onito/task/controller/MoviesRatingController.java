package com.onito.task.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onito.task.entity.Movies;
import com.onito.task.entity.Ratings;
import com.onito.task.utility.MovieData;

@RestController
public class MoviesRatingController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

    public MoviesRatingController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

     /* GET /api/v1/longest-duration-movies
        This route returns as JSON the top 10 movies with the longest runTime
         The output contain tconst, primaryTitle, runtimeMinutes & genres
      */
    
    @GetMapping(value = "/api/v1/longest-duration-movies", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getLongestDurationMovies() {
        String sql = "SELECT tconst, primaryTitle, runtimeMinutes, genres FROM movies " +
                      "ORDER BY runtimeMinutes DESC LIMIT 10";
        return jdbcTemplate.queryForList(sql);
    }
    
    /* POST /api/v1/new-movie
     This route takes JSON as input for new movie and saves it into the database
     On successful save, it returns “success”
     */
    
    @PostMapping(value = "/api/v1/new-movie", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addNewMovie(@RequestBody MovieData movieData) {
    	Movies movies = movieData.getMovies();
        Ratings ratings = movieData.getRatings();
        try {
            String movieSql = "INSERT INTO movies (tconst, titleType, primaryTitle, runtimeMinutes, genres) VALUES (?, ?, ?, ?, ?)";
            String ratingsSql = "INSERT INTO ratings (tconst, averageRating, numVotes) VALUES (?, ?, ?)";
            
            // Handle the case when any required information is missing or null here all columns is required and NOT NULL .So if some field is missing input ,it will give output 'failure'
            if (movies == null || ratings == null || movies.getTconst() == null || movies.getTitleType() == null ||
            		movies.getPrimaryTitle() == null||movies.getRuntimeMinutes()==null || movies.getGenres() == null || ratings.getTconst() == null||Objects.isNull(ratings.getAverageRating()) ||ratings.getNumVotes()==null) {
              
                return "failure"+"\r\n Please make sure jason format should be like this --> as every column in both table is required"+ "  "+"\r\n{"
                		+ "  \"movies\": {\r\n"
                		+ "    \"tconst\": \"\",\r\n"
                		+ "    \"titleType\": \"\",\r\n"
                		+ "    \"primaryTitle\": \"\",\r\n"
                		+ "    \"runtimeMinutes\": ,\r\n"
                		+ "    \"genres\": \"\"\r\n"
                		+ "  },\r\n"
                		+ "  \"ratings\": {\r\n"
                		+ "    \"tconst\": \"\",\r\n"
                		+ "    \"averageRating\": ,\r\n"
                		+ "    \"numVotes\": \r\n"
                		+ "  }\r\n"
                		+ "}\r\n"
                		+ "";
            }
          
            jdbcTemplate.update(movieSql, movies.getTconst(), movies.getTitleType(), movies.getPrimaryTitle(),movies.getRuntimeMinutes(), movies.getGenres());
            
            jdbcTemplate.update(ratingsSql, ratings.getTconst(), ratings.getAverageRating(), ratings.getNumVotes());
            
            return "success";
           
        } catch (Exception e) {
            // Handle any exceptions or errors
            return "failure\r\n"+e.getMessage().toString();
        }
    }
    
    /*This route returns as JSON the movies with an averageRating > 6.0, in sorted
      order by averageRating
      The output contain tconst, primaryTitle, genre & averageRating
     */
    
    @GetMapping(value = "/api/v1/top-rated-movies", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getTopRatedMovies() {
        String sql = "SELECT ratings.tconst,primaryTitle, genres AS genre,averageRating "+ " FROM ratings "+ " INNER JOIN movies "
        		+ " USING(tconst) "+ " WHERE averageRating>6.0 "+ " ORDER BY averageRating ";
        return jdbcTemplate.queryForList(sql);
    }
    
    /*GET /api/v1/genre-movies-with-subtotals
     Show a list of all movies genre-wise with Subtotals of their numVotes.
    */
    
    @GetMapping(value = "/api/v1/genre-movies-with-subtotals", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getGenreMoviesWithSubtotals() {
        String sql = " SELECT CASE WHEN GROUPING(primaryTitle)=1 AND GROUPING(genres )=0 THEN null ELSE genres END AS Genre,"
                     +" CASE  WHEN GROUPING(primaryTitle)=1 AND GROUPING(genres )=0 THEN 'Total' " 
        		        +" WHEN GROUPING(primaryTitle)=1 AND GROUPING(genres )=1 THEN 'GrandTotal' "
                     + " ELSE primaryTitle  END AS primaryTitle,"
               		 + " SUM(numVotes) AS numVotes FROM movies INNER JOIN ratings USING(tconst) "
                     +" GROUP BY genres,primaryTitle WITH ROLLUP ";
        return jdbcTemplate.queryForList(sql);
    }
    
    /*POST /api/v1/update-runtime-minutes
      Increment runtimeMinutes of all Movies using only SQL query (not in API code).
      Increment runtimeMinutes by :
      15 if genre = Documentary
      30 if genre = Animation
      45 for the rest*
     */
    
    @PostMapping("/api/v1/update-runtime-minutes")
    public String updateRuntimeMinutes() {
        jdbcTemplate.update("UPDATE movies SET runtimeMinutes = runtimeMinutes + 15 WHERE genres = 'Documentary'");
        jdbcTemplate.update("UPDATE movies SET runtimeMinutes = runtimeMinutes + 30 WHERE genres = 'Animation'");
        jdbcTemplate.update("UPDATE movies SET runtimeMinutes = runtimeMinutes + 45 WHERE genres NOT IN ('Documentary', 'Animation')");

        return "Runtime minutes updated successfully.";
    }
    
    }