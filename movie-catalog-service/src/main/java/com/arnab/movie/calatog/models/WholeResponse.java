package com.arnab.movie.calatog.models;

import java.util.List;

public class WholeResponse {

	private List<CatalogItem> catalogItems;
	private List<Services> services;

	public WholeResponse() {
		super();
	}

	public WholeResponse(List<CatalogItem> catalogItems, List<Services> services) {
		super();
		this.catalogItems = catalogItems;
		this.services = services;
	}

	public List<CatalogItem> getCatalogItems() {
		return catalogItems;
	}

	public void setCatalogItems(List<CatalogItem> catalogItems) {
		this.catalogItems = catalogItems;
	}

	public List<Services> getServices() {
		return services;
	}

	public void setServices(List<Services> services) {
		this.services = services;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catalogItems == null) ? 0 : catalogItems.hashCode());
		result = prime * result + ((services == null) ? 0 : services.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WholeResponse other = (WholeResponse) obj;
		if (catalogItems == null) {
			if (other.catalogItems != null)
				return false;
		} else if (!catalogItems.equals(other.catalogItems))
			return false;
		if (services == null) {
			if (other.services != null)
				return false;
		} else if (!services.equals(other.services))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WholeResponse [catalogItems=" + catalogItems + ", services=" + services + "]";
	}

}
