package com.example.adminapp.dto;

import java.io.Serializable;

public class GeoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long geoId;
	private String lat;
	private String longitude;

	public Long getGeoId() {
		return geoId;
	}

	public void setGeoId(Long geoId) {
		this.geoId = geoId;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "GeoDto [geoId=" + geoId + ", lat=" + lat + ", longitude=" + longitude + "]";
	}

}
