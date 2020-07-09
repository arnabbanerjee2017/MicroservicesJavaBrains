package com.arnab.movie.calatog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.arnab.movie.calatog.models.Movie;
import com.arnab.movie.calatog.service.MovieInfoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieInfoServiceImpl implements MovieInfoService {
	
	private static final String CLASSNAME = MovieInfoServiceImpl.class.getName();
	private static final Logger LOG = LoggerFactory.getLogger(MovieInfoServiceImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	@HystrixCommand(fallbackMethod = "getMovieInfoFallback")
	public Movie getMovieInfo(String movieId) {
		String source = CLASSNAME + ".getMovieInfo()";
		LOG.info("{} Entering ", source);
		return restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/" + movieId, Movie.class);
	}
	
	@Override
	public Movie getMovieInfoFallback(String movieId) {
		String source = CLASSNAME + ".getMovieInfoFallback()";
		LOG.info("{} Entering ", source);
		return new Movie();
	}
}
