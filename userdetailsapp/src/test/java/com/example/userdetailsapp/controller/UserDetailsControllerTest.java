package com.example.userdetailsapp.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.userdetailsapp.dto.AddressDto;
import com.example.userdetailsapp.dto.CompanyDto;
import com.example.userdetailsapp.dto.GeoDto;
import com.example.userdetailsapp.dto.UserDetailsDto;
import com.example.userdetailsapp.serviceimpl.UserDetailsServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserDetailsControllerTest {

	@Mock
	UserDetailsServiceImpl userDetailsServiceImpl;

	@InjectMocks
	UserDetailsController userDetailsController;

	@Test
	@DisplayName("Testing add userDetails")
	void testAddUserDetails() {
		UserDetailsDto userDetailsDto = new UserDetailsDto();
		AddressDto addressDto = new AddressDto();
		addressDto.setAddressId(1l);
		addressDto.setCity("mzn");
		GeoDto geoDto = new GeoDto();
		geoDto.setGeoId(1l);
		geoDto.setLat("30.48");
		geoDto.setLongitude("50.67");
		addressDto.setGeo(geoDto);
		addressDto.setStreet("1");
		userDetailsDto.setAddress(addressDto);
		CompanyDto companyDto = new CompanyDto();
		companyDto.setCompanyId(1l);
		companyDto.setLocation("India");
		companyDto.setName("globallogic");
		userDetailsDto.setCompany(companyDto);
		userDetailsDto.setEmail("a@gmail.com");
		userDetailsDto.setName("ak");
		userDetailsDto.setPhone("7474747444");
		userDetailsDto.setUserId(1l);
		userDetailsDto.setUserName("akg");
		ResponseEntity<Object> response = new ResponseEntity<>(null, HttpStatus.OK);
		when(userDetailsServiceImpl.addUser(userDetailsDto)).thenReturn(response);
		ResponseEntity<Object> responseFromController = userDetailsController.addUserDetails(userDetailsDto);
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
	}

	@Test
	@DisplayName("Testing update userDetails")
	void testupdateUserDetails() {
		UserDetailsDto userDetailsDto = new UserDetailsDto();
		AddressDto addressDto = new AddressDto();
		addressDto.setAddressId(1l);
		addressDto.setCity("mzn");
		GeoDto geoDto = new GeoDto();
		geoDto.setGeoId(1l);
		geoDto.setLat("30.48");
		geoDto.setLongitude("50.67");
		addressDto.setGeo(geoDto);
		addressDto.setStreet("1");
		userDetailsDto.setAddress(addressDto);
		CompanyDto companyDto = new CompanyDto();
		companyDto.setCompanyId(1l);
		companyDto.setLocation("India");
		companyDto.setName("globallogic");
		userDetailsDto.setCompany(companyDto);
		userDetailsDto.setEmail("a@gmail.com");
		userDetailsDto.setName("ak");
		userDetailsDto.setPhone("7474747444");
		userDetailsDto.setUserId(1l);
		userDetailsDto.setUserName("akg");
		ResponseEntity<Object> response = new ResponseEntity<>(null, HttpStatus.OK);
		when(userDetailsServiceImpl.updateUser(userDetailsDto)).thenReturn(response);
		ResponseEntity<Object> responseFromController = userDetailsController.updateUserDetails(userDetailsDto);
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
	}

	@Test
	@DisplayName("Testing get specific userDetails")
	void testgetSpecificUserDetails() {
		ResponseEntity<Object> response = new ResponseEntity<>(null, HttpStatus.OK);
		when(userDetailsServiceImpl.getSpecificUserDetails(1l)).thenReturn(response);
		ResponseEntity<Object> responseFromController = userDetailsController.getSpecificUserDetail(1l);
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
	}

	@Test
	@DisplayName("Testing get all userDetails")
	void testgetAllUserDetails() {
		ResponseEntity<Object> response = new ResponseEntity<>(null, HttpStatus.OK);
		when(userDetailsServiceImpl.getAllUserDetails()).thenReturn(response);
		ResponseEntity<Object> responseFromController = userDetailsController.getAllUserDetails();
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
	}

	@Test
	@DisplayName("Testing delete userDetails")
	void testDeleteUserDetails() {
		ResponseEntity<Object> response = new ResponseEntity<>(null, HttpStatus.OK);
		when(userDetailsServiceImpl.deleteUser(1l)).thenReturn(response);
		ResponseEntity<Object> responseFromController = userDetailsController.deleteUserDetails(1l);
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
	}

}
