package com.arnab.movie.ratings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arnab.movie.ratings.models.Rating;
import com.arnab.movie.ratings.service.MovieRatingsService;

@RestController
@RequestMapping(value = "/ratings")
public class MovieRatingsController {

	@Autowired
	private MovieRatingsService service;
	
	@GetMapping(value = "/{movieid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Rating getRating(@PathVariable("movieid") String movieId) {
		return service.getRating(movieId);
	}
	
}
