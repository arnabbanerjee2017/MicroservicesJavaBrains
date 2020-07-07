package com.arnab.movie.calatog.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.arnab.movie.calatog.models.CatalogItem;
import com.arnab.movie.calatog.models.Movie;
import com.arnab.movie.calatog.models.Rating;
import com.arnab.movie.calatog.models.Services;
import com.arnab.movie.calatog.models.WholeResponse;

@Service
public class MovieCatalogService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;

	public List<CatalogItem> getCatalog(String userId) {
		
		List<CatalogItem> result = new ArrayList<>();		
		List<String> movieIds = MovieCatalogService.getListOfMovieIDs(userId);
		
		for(String movieId: movieIds) {
			Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/" + movieId, Movie.class);
			Rating rating = restTemplate.getForObject("http://MOVIE-RATINGS-DATA-SERVICE/ratings/" + movieId, Rating.class);			
			if(movie != null && rating != null) {
				result.add(new CatalogItem(movie.getName(), "Movie " + movie.getName(), rating.getRating()));
			}
		}
		return result;		
	}
	
	public WholeResponse getWholeResponse(String userId) {
		WholeResponse response = new WholeResponse();		
		response.setCatalogItems(this.getCatalog(userId));	
		
		List<Services> servicesResponse = new ArrayList<>();		
		List<String> services = discoveryClient.getServices();
		
		if(services != null && !services.isEmpty()) {
			for(String service: services) {			
				List<ServiceInstance> serviceInstances = discoveryClient.getInstances(service);			
				if(serviceInstances != null && !serviceInstances.isEmpty()) {
					for(ServiceInstance serviceInstance: serviceInstances) {
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
		
		//List<ServiceInstance> movieInfoServiceInstances = discoveryClient.getInstances("MOVIE-INFO-SERVICE");
		//List<ServiceInstance> movieRatingsServiceInstances = discoveryClient.getInstances("MOVIE-RATINGS-DATA-SERVICE");
		
		return response;
		
	}
	
	private static List<String> getListOfMovieIDs(String userId) {
		return (userId.equals("A001") ? Arrays.asList("M00001", "M00002", "M00003") :
			(userId.equals("A002") ? Arrays.asList("M00004", "M00005", "M00006") :
				(userId.equals("A003") ? Arrays.asList("M00007", "M00008", "M00009") : new ArrayList<>())));
	}
	
}
