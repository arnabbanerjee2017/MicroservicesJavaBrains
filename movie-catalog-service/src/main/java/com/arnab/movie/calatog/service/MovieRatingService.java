package com.arnab.movie.calatog.service;

import com.arnab.movie.calatog.models.Rating;

public interface MovieRatingService {
	
	Rating getRatingInfo(String movieId);
	Rating getRatingInfoFallback(String movieId);

}
