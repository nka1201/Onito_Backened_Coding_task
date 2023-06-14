Onito Backened Coding task

  Used Java with Spring Boot (using STS IDE) and MySQL database
  
->Created SQL Tables `movies` & `ratings`, and populate the CSV data(movies.csv and ratings.csv) into them.
-> Created an HTTP server with the following routes
     
     a) GET /api/v1/longest-duration-movies
         This route returns as JSON the top 10 movies with the longest runTime
         The output contain tconst, primaryTitle, runtimeMinutes & genres
     
     b) POST /api/v1/new-movie
       This route takes JSON as input for new movie and saves it into the database
        On successful save, it returns “success”
     
     c)GET /api/v1/top-rated-movies
       This route returns as JSON the movies with an averageRating > 6.0, in sorted order by averageRating
       The output contain tconst, primaryTitle, genre & averageRating.

      d) GET /api/v1/genre-movies-with-subtotals
          Shows a list of all movies genre-wise with Subtotals of their numVotes.
          
       e) POST /api/v1/update-runtime-minutes
            Increment runtimeMinutes of all Movies using  SQL query.
            Increment runtimeMinutes by :
            15 if genre = Documentary
             30 if genre = Animation
             45 for the rest
     
