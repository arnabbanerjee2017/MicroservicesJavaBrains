package com.arnab.movie.ratings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arnab.movie.ratings.constants.RatingConstants;
import com.arnab.movie.ratings.models.Rating;
import com.arnab.movie.ratings.service.MovieRatingsService;

@RestController
@RequestMapping(value = RatingConstants.RATINGS_API_SUB_PATH_1)
public class MovieRatingsController {

	@Autowired
	private MovieRatingsService service;
	
	@GetMapping(value = RatingConstants.RATINGS_API_SUB_PATH_2, produces = MediaType.APPLICATION_JSON_VALUE)
	public Rating getRating(@PathVariable(RatingConstants.RATINGS_API_SUB_PATH_2_PATH_VARIABLE) String movieId) {
		return service.getRating(movieId);
	}
	
}
