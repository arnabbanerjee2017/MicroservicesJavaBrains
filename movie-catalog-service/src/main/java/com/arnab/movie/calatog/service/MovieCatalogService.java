package com.arnab.movie.calatog.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.arnab.movie.calatog.models.CatalogItem;

@Service
public class MovieCatalogService {

	public List<CatalogItem> getCatalog(String userId) {
		if(userId.equals("A001")) {
			return Arrays.asList(new CatalogItem("Titanic", "Movie Titanic", 8), 
					new CatalogItem("Avengers", "Movie Avengers", 7), 
					new CatalogItem("Spider Man", "Movie Spider Man", 7));
		} else if(userId.equals("A002")) {
			return Arrays.asList(new CatalogItem("Pearl Harbour", "Movie Pearl Harbour", 7), 
					new CatalogItem("The Dark Knight", "Movie The Dark Knight", 8), 
					new CatalogItem("The God Father", "Movie The God Father", 9));
		} else if(userId.equals("A003")) {
			return Arrays.asList(new CatalogItem("Blood Diamond", "Movie Blood Diamond", 8), 
					new CatalogItem("Shutter Island", "Movie Shutter Island", 8), 
					new CatalogItem("October Sky", "Movie October Sky", 8));
		} else {
			return null;
		}
	}
	
}
