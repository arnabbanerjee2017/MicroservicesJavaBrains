package com.arnab.movie.ratings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.arnab.movie.ratings.constants.RatingConstants;
import com.arnab.movie.ratings.models.Rating;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class MovieRatingsService {

	@Autowired
	private RestTemplate restTemplate;

	public Rating getRating(String id) {
		String movieInfo = restTemplate.getForObject(RatingConstants.MOVIE_DB_API_URL + id
				+ RatingConstants.MOVIE_DB_API_KEY_HOLDER_IN_URL + RatingConstants.MOVIE_DB_API_KEY, String.class);
		return this.parseMovieResponse(id, movieInfo);
	}

	private Rating parseMovieResponse(String id, String response) {
		JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
		return new Rating(id, Double.parseDouble(jsonObject.get(RatingConstants.RATING_RESPONSE_KEY_FROM_MOVIE_DB).getAsString()));
	}

}
