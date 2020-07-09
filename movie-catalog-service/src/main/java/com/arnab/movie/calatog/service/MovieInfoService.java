package com.arnab.movie.calatog.service;

import com.arnab.movie.calatog.models.Movie;

public interface MovieInfoService {

	Movie getMovieInfo(String movieId);
	Movie getMovieInfoFallback(String movieId);

}