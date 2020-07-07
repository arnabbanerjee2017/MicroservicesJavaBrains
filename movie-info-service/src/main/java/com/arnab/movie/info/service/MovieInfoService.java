package com.arnab.movie.info.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.arnab.movie.info.models.Movie;

@Service
public class MovieInfoService {

	public Movie getMovie(String id) {
		try {
			return MovieInfoService.getMovies().stream().filter(m -> m.getId().equals(id)).findFirst().get();
		} catch(Exception e) {
			return null;
		}
	}
	
	private static List<Movie> getMovies() {
		return Arrays.asList(new Movie("M00001", "Titanic"),
				new Movie("M00002", "Avengers"),
				new Movie("M00003", "Spider Man"),
				new Movie("M00004", "Pearl Harbour"),
				new Movie("M00005", "The Dark Knight"),
				new Movie("M00006", "The God Father"),
				new Movie("M00007", "Blood Diamond"),
				new Movie("M00008", "Shutter Island"),
				new Movie("M00009", "October Sky"));
	}
	
}
