package com.arnab.movie.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arnab.movie.info.constants.MovieInfoConstants;
import com.arnab.movie.info.models.Movie;
import com.arnab.movie.info.service.MovieInfoService;

@RestController
@RequestMapping(value = MovieInfoConstants.MOVIE_INFO_API_SUB_PATH_1)
public class MovieInfoController {

	@Autowired
	private MovieInfoService service;
	
	@GetMapping(value = MovieInfoConstants.MOVIE_INFO_API_SUB_PATH_3, produces = MediaType.APPLICATION_JSON_VALUE)
	public Movie getMovie(@PathVariable(MovieInfoConstants.MOVIE_INFO_API_SUB_PATH_2_PATH_VARIABLE) String movieId) {
		return service.getMovie(movieId);
	}
	
}
