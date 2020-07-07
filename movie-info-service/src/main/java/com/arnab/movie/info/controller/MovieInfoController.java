package com.arnab.movie.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arnab.movie.info.models.Movie;
import com.arnab.movie.info.service.MovieInfoService;

@RestController
@RequestMapping(value = "/movies")
public class MovieInfoController {

	@Autowired
	private MovieInfoService service;
	
	@GetMapping("/{movieid}")
	public Movie getMovie(@PathVariable("movieid") String movieId) {
		return service.getMovie(movieId);
	}
	
}
