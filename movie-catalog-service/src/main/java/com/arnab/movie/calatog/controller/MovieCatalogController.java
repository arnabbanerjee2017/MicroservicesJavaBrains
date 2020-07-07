package com.arnab.movie.calatog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arnab.movie.calatog.models.CatalogItem;
import com.arnab.movie.calatog.models.WholeResponse;
import com.arnab.movie.calatog.service.MovieCatalogService;

@RestController
@RequestMapping(value = "/catalog")
public class MovieCatalogController {

	@Autowired
	private MovieCatalogService service;
	
	@GetMapping(value = "/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CatalogItem> getCatalog(@PathVariable("userid") String userId) {
		return service.getCatalog(userId);
	}
	
	@GetMapping(value = "/wholeresponse/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public WholeResponse getWholeResponse(@PathVariable("userid") String userId) {
		return service.getWholeResponse(userId);
	}
	
}
