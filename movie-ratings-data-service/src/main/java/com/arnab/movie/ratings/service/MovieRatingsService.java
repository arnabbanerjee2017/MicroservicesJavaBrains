package com.arnab.movie.ratings.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.arnab.movie.ratings.models.Rating;

@Service
public class MovieRatingsService {
	
	public Rating getRating(String id) {
		try {
			return MovieRatingsService.getRatings().stream().filter(m -> m.getId().equals(id)).findFirst().get();
		} catch(Exception e) {
			return null;
		}
	}
	
	private static List<Rating> getRatings() {
		return Arrays.asList(new Rating("M00001", 8),
				new Rating("M00002", 7),
				new Rating("M00003", 7),
				new Rating("M00004", 7),
				new Rating("M00005", 8),
				new Rating("M00006", 9),
				new Rating("M00007", 8),
				new Rating("M00008", 8),
				new Rating("M00009", 8));
	}

}
