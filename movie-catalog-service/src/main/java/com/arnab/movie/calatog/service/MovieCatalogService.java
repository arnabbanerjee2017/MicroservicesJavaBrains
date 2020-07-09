package com.arnab.movie.calatog.service;

import java.util.List;

import com.arnab.movie.calatog.models.CatalogItem;
import com.arnab.movie.calatog.models.WholeResponse;

public interface MovieCatalogService {

	List<CatalogItem> getCatalog(String userId);
	WholeResponse getWholeResponse(String userId);
	List<CatalogItem> getCatalogFallback(String userId);
	WholeResponse getWholeResponseFallback(String userId);

}