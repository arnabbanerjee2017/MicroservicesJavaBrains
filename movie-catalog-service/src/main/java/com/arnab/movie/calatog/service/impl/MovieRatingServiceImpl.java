package com.arnab.movie.calatog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.arnab.movie.calatog.models.Rating;
import com.arnab.movie.calatog.service.MovieRatingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieRatingServiceImpl implements MovieRatingService {
	
	private static final String CLASSNAME = MovieRatingServiceImpl.class.getName();
	private static final Logger LOG = LoggerFactory.getLogger(MovieRatingServiceImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getRatingInfoFallback")
	public Rating getRatingInfo(String movieId) {
		String source = CLASSNAME + ".getRatingInfo()";
		LOG.info("{} Entering ", source);
		return restTemplate.getForObject("http://MOVIE-RATINGS-DATA-SERVICE/ratings/" + movieId, Rating.class);
	}
	
	public Rating getRatingInfoFallback(String movieId) {
		String source = CLASSNAME + ".getRatingInfoFallback()";
		LOG.info("{} Entering ", source);
		return new Rating();
	}
	
}
