/**
 * Copyright (c) 2020, ARNAB BANERJEE. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted only for academic purposes.
 *
 * For further queries and/or information: arnab.ban20@yahoo.com
 */

package com.arnab.movie.calatog.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import com.arnab.movie.calatog.models.CatalogItem;
import com.arnab.movie.calatog.models.Movie;
import com.arnab.movie.calatog.models.Rating;
import com.arnab.movie.calatog.models.Services;
import com.arnab.movie.calatog.models.WholeResponse;
import com.arnab.movie.calatog.service.MovieCatalogService;
import com.arnab.movie.calatog.service.MovieInfoService;
import com.arnab.movie.calatog.service.MovieRatingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieCatalogServiceImpl implements MovieCatalogService {

	private static final String CLASSNAME = MovieCatalogService.class.getName();
	private static final Logger LOG = LoggerFactory.getLogger(MovieCatalogService.class);

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private MovieInfoService movieInfoService;

	@Autowired
	private MovieRatingService movieRatingService;

	@Override
	@HystrixCommand(fallbackMethod = "getCatalogFallback")
	public List<CatalogItem> getCatalog(String userId) {
		String source = CLASSNAME + ".getCatalog()";
		LOG.info("{} Entering ", source);

		List<CatalogItem> result = new ArrayList<>();
		List<String> movieIds = MovieCatalogServiceImpl.getListOfMovieIDs(userId);

		for (String movieId : movieIds) {
			Movie movie = movieInfoService.getMovieInfo(movieId);
			Rating rating = movieRatingService.getRatingInfo(movieId);
			if (movie != null && rating != null) {
				if ((movie.getId() != null && !movie.getId().isEmpty())
						&& (rating.getId() != null && !rating.getId().isEmpty())) {
					result.add(new CatalogItem(movie.getName(), movie.getUrl(), movie.getImdbId(), movie.getDesc(),
							movie.getReleaseDate(), rating.getRating()));
				} else {
					result.add(new CatalogItem());
				}
			}
		}
		return result;
	}

	@Override
	@HystrixCommand(fallbackMethod = "getWholeResponseFallback")
	public WholeResponse getWholeResponse(String userId) {
		String source = CLASSNAME + ".getWholeResponse()";
		LOG.info("{} Entering ", source);

		WholeResponse response = new WholeResponse();
		response.setCatalogItems(this.getCatalog(userId));

		List<Services> servicesResponse = new ArrayList<>();
		List<String> services = discoveryClient.getServices();

		if (services != null && !services.isEmpty()) {
			for (String service : services) {
				List<ServiceInstance> serviceInstances = discoveryClient.getInstances(service);
				if (serviceInstances != null && !serviceInstances.isEmpty()) {
					for (ServiceInstance serviceInstance : serviceInstances) {
						Services s = new Services();
						s.setHost(serviceInstance.getHost());
						s.setInstanceId(serviceInstance.getInstanceId());
						s.setMetadata(serviceInstance.getMetadata());
						s.setName(service);
						s.setPort(serviceInstance.getPort());
						s.setServiceId(serviceInstance.getServiceId());
						s.setUri(serviceInstance.getUri());
						servicesResponse.add(s);
					}
				}
			}
		}

		response.setServices(servicesResponse);
		return response;
	}

	@Override
	public List<CatalogItem> getCatalogFallback(String userId) {
		String source = CLASSNAME + ".getCatalogFallback()";
		LOG.info("{} Entering ", source);
		return new ArrayList<>();
	}

	@Override
	public WholeResponse getWholeResponseFallback(String userId) {
		String source = CLASSNAME + ".getWholeResponseFallback()";
		LOG.info("{} Entering ", source);
		return new WholeResponse();
	}

	/**
	 * This is the method returning the List of Movie IDs. This is a hard-coded
	 * example. This can be configured to fetch the Movie IDs from some different
	 * sources, i.e., Databases or Web-Services.
	 * 
	 * @param userId
	 * @return
	 */
	private static List<String> getListOfMovieIDs(String userId) {
		String source = CLASSNAME + ".getListOfMovieIDs()";
		LOG.info("{} Entering ", source);

		return (userId.equals("A001") ? Arrays.asList("550", "555", "560")
				: (userId.equals("A002") ? Arrays.asList("510", "505", "501")
						: (userId.equals("A003") ? Arrays.asList("530", "525", "535") : new ArrayList<>())));
	}

}
