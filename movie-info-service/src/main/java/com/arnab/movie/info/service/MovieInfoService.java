package com.arnab.movie.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.arnab.movie.info.constants.MovieInfoConstants;
import com.arnab.movie.info.models.Movie;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class MovieInfoService {

	@Autowired
	private RestTemplate restTemplate;

	public Movie getMovie(String id) {
		String movieInfo = restTemplate.getForObject(MovieInfoConstants.MOVIE_DB_API_URL + id
				+ MovieInfoConstants.MOVIE_DB_API_KEY_HOLDER_IN_URL + MovieInfoConstants.MOVIE_DB_API_KEY,
				String.class);
		return this.parseMovieResponse(id, movieInfo);
	}

	private Movie parseMovieResponse(String id, String response) {
		JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
		return new Movie(id, jsonObject.get(MovieInfoConstants.MOVIE_DB_API_KEY_TITLE).getAsString(), 
				jsonObject.get(MovieInfoConstants.MOVIE_DB_API_KEY_HOMEPAGE).getAsString(),
				jsonObject.get(MovieInfoConstants.MOVIE_DB_API_KEY_IMDB_ID).getAsString(), 
				jsonObject.get(MovieInfoConstants.MOVIE_DB_API_KEY_OVERVIEW).getAsString(),
				jsonObject.get(MovieInfoConstants.MOVIE_DB_API_KEY_RELEASE_DATE).getAsString());
	}

}
