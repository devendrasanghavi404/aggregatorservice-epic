package com.example.adminapp.dto;

import java.io.Serializable;


public class AddressDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long addressId;
	private String street;
	private String city;
	private GeoDto geo;

	public GeoDto getGeo() {
		return geo;
	}

	public void setGeo(GeoDto geo) {
		this.geo = geo;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "AddressDto [addressId=" + addressId + ", street=" + street + ", city=" + city + ", geo=" + geo + "]";
	}

}
