package com.arnab.movie.ratings.constants;

public class RatingConstants {
	
	public static final String RATINGS_API_SUB_PATH_1 = "/ratings";
	public static final String RATINGS_API_SUB_PATH_2_PATH_VARIABLE = "movieid";
	public static final String RATINGS_API_SUB_PATH_2 = "/{" + RATINGS_API_SUB_PATH_2_PATH_VARIABLE + "}";

	public static final String MOVIE_DB_API_KEY = "5c956f44a728d2302054935ec4643921";
	public static final String MOVIE_DB_API_URL = "https://api.themoviedb.org/3/movie/";
	public static final String MOVIE_DB_API_KEY_HOLDER_IN_URL = "?api_key=";
	
	public static final String RATING_RESPONSE_KEY_FROM_MOVIE_DB = "vote_average";
	
}
