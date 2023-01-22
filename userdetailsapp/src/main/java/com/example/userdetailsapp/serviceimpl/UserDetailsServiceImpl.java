package com.example.userdetailsapp.serviceimpl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.userdetailsapp.dto.AddressDto;
import com.example.userdetailsapp.dto.CompanyDto;
import com.example.userdetailsapp.dto.GeoDto;
import com.example.userdetailsapp.dto.Response;
import com.example.userdetailsapp.dto.UserDetailsDto;
import com.example.userdetailsapp.exception.UserException;
import com.example.userdetailsapp.modal.Address;
import com.example.userdetailsapp.modal.Company;
import com.example.userdetailsapp.modal.Geo;
import com.example.userdetailsapp.modal.SecureUser;
import com.example.userdetailsapp.modal.UserDetails;
import com.example.userdetailsapp.repository.AddressRepository;
import com.example.userdetailsapp.repository.CompanyRepository;
import com.example.userdetailsapp.repository.GeoRepository;
import com.example.userdetailsapp.repository.UserDetailsRepository;
import com.example.userdetailsapp.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	GeoRepository geoRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Object deleteUser(Long userId) {
		try {
			Optional<UserDetails> userDetails1 = userDetailsRepository.findById(userId);
			if (userDetails1.isEmpty()) {
				throw new UserException("User does not exist");
			}
			UserDetails userDetails = userDetails1.get();
			companyRepository.deleteById(userDetails.getCompany().getCompanyId());
			geoRepository.deleteById(userDetails.getGeo().getGeoId());
			addressRepository.deleteById(userDetails.getAddress().getAddressId());
			userDetailsRepository.deleteById(userDetails.getUserId());
			return new Response<Object>("UserDetails Deleted succesfully", "1");

		} catch (Exception e) {
			String errorMsg = MessageFormat
					.format("Exception caught in getAllUserDetails of UserDetailsServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new UserException(e.getMessage());
		}
	}

	@Override
	public Object getAllUserDetails() {
		try {
			logger.info("Inside addUser of UserDetailsServiceImpl");
			List<UserDetails> userDetailsFromRepo = userDetailsRepository.findAll();
			if (userDetailsFromRepo.size() == 0) {
				throw new UserException("No Data Found");

			}
			List<UserDetailsDto> responseDataList = new ArrayList<>();
			for (UserDetails userDetails : userDetailsFromRepo) {
				UserDetailsDto userDetailsDto = new UserDetailsDto();
				userDetailsDto.setUserId(userDetails.getUserId());
				userDetailsDto.setEmail(userDetails.getEmail());
				userDetailsDto.setName(userDetails.getName());
				userDetailsDto.setPhone(userDetails.getPhone());
				userDetailsDto.setUserName(userDetails.getUserName());
				AddressDto addressDto = new AddressDto();
				if (userDetails.getAddress() != null) {
					addressDto.setAddressId(userDetails.getAddress().getAddressId());
					addressDto.setCity(userDetails.getAddress().getCity());
					addressDto.setStreet(userDetails.getAddress().getStreet());
				}
				GeoDto geoDto = new GeoDto();
				if (userDetails.getGeo() != null) {
					geoDto.setGeoId(userDetails.getGeo().getGeoId());
					geoDto.setLat(userDetails.getGeo().getLat());
					geoDto.setLongitude(userDetails.getGeo().getLongitude());
				}
				addressDto.setGeo(geoDto);
				userDetailsDto.setAddress(addressDto);
				CompanyDto companyDto = new CompanyDto();
				if (userDetails.getCompany() != null) {
					companyDto.setLocation(userDetails.getCompany().getName());
					companyDto.setName(userDetails.getCompany().getLocation());
				}
				userDetailsDto.setCompany(companyDto);
				responseDataList.add(userDetailsDto);
			}
			return new Response<Object>("UserDetails Fetched Successfully", "1", responseDataList);
		} catch (Exception e) {
			String errorMsg = MessageFormat
					.format("Exception caught in getAllUserDetails of UserDetailsServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new UserException(e.getMessage());
		}
	}

	@Override
	public Object getSpecificUserDetails(Long UserId) {
		try {
			logger.info("Inside addUser of UserDetailsServiceImpl with userid :{}", UserId);
			Optional<UserDetails> userDetailsFromRepo = userDetailsRepository.findById(UserId);
			logger.info("Data from repo is :{}", userDetailsFromRepo);
			if (userDetailsFromRepo.isEmpty()) {
				throw new UserException("User Does Not Exist");

			}
			UserDetailsDto userDetailsDto = new UserDetailsDto();
			UserDetails userDetails1 = userDetailsFromRepo.get();
			userDetailsDto.setUserId(userDetails1.getUserId());
			userDetailsDto.setEmail(userDetails1.getEmail());
			userDetailsDto.setName(userDetails1.getName());
			userDetailsDto.setPhone(userDetails1.getPhone());
			userDetailsDto.setUserName(userDetails1.getUserName());
			AddressDto addressDto = new AddressDto();
			if (userDetails1.getAddress() != null) {
				addressDto.setAddressId(userDetails1.getAddress().getAddressId());
				addressDto.setCity(userDetails1.getAddress().getCity());
				addressDto.setStreet(userDetails1.getAddress().getStreet());
			}
			GeoDto geoDto = new GeoDto();
			if (userDetails1.getGeo() != null) {
				geoDto.setGeoId(userDetails1.getGeo().getGeoId());
				geoDto.setLat(userDetails1.getGeo().getLat());
				geoDto.setLongitude(userDetails1.getGeo().getLongitude());
			}
			addressDto.setGeo(geoDto);
			userDetailsDto.setAddress(addressDto);
			CompanyDto companyDto = new CompanyDto();
			if (userDetails1.getCompany() != null) {
				companyDto.setLocation(userDetails1.getCompany().getName());
				companyDto.setName(userDetails1.getCompany().getLocation());
			}
			userDetailsDto.setCompany(companyDto);
			return new Response<Object>("UserDetails Fetched Successfully", "1", userDetailsDto);
		} catch (Exception e) {
			String errorMsg = MessageFormat
					.format("Exception caught in getSpecificUserDetails of UserDetailsServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new UserException(e.getMessage());
		}
	}

	@Override
	public Object addUser(UserDetailsDto userDetailsDto) {
		try {
			logger.info("Inside addUser of UserDetailsServiceImpl");
			UserDetails userDetails = new UserDetails();
			// userDetails.setEmail(userDetailsDto.getEmail());
			logger.info("Encode email is :{}",passwordEncoder.encode(userDetailsDto.getEmail()));
			userDetails.setEmail(passwordEncoder.encode(userDetailsDto.getEmail()));
			userDetails.setName(userDetailsDto.getName());
			userDetails.setPhone(userDetailsDto.getPhone());
			// userDetails.setUserName(userDetailsDto.getUserName());
			userDetails.setUserName(passwordEncoder.encode(userDetailsDto.getUserName()));
			UserDetails savedUserDetails = userDetailsRepository.save(userDetails);
			Address address = new Address();
			address.setCity(userDetailsDto.getAddress().getCity());
			address.setStreet(userDetailsDto.getAddress().getStreet());
			address.setUserId(savedUserDetails);
			Address savedAddress = addressRepository.save(address);
			Geo geo = new Geo();
			geo.setLat(userDetailsDto.getAddress().getGeo().getLat());
			geo.setLongitude(userDetailsDto.getAddress().getGeo().getLongitude());
			geo.setUserId(savedUserDetails);
			Geo savedGeo = geoRepository.save(geo);
			userDetails.setAddress(address);
			userDetails.setGeo(geo);
			Company company = new Company();
			company.setLocation(userDetailsDto.getCompany().getName());
			company.setName(userDetailsDto.getCompany().getLocation());
			company.setUserId(savedUserDetails);
			Company savedCompany = companyRepository.save(company);
			userDetails.setAddress(savedAddress);
			userDetails.setGeo(savedGeo);
			userDetails.setCompany(savedCompany);
			return new Response<Object>("UserDetails saved Successfully", "1");
		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in addUser of UserDetailsServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new UserException(e.getMessage());
		}
	}

	@Override
	public Object updateUser(UserDetailsDto userDetailsDto) {
		try {
			logger.info("Inside addUser of UserDetailsServiceImpl");
			Optional<UserDetails> userDetails1 = userDetailsRepository.findById(userDetailsDto.getUserId());
			if (userDetails1.isEmpty()) {
				throw new UserException("User does not exist");
			}
			UserDetails userDetails = userDetails1.get();
			userDetails.setUserId(userDetailsDto.getUserId());
			userDetails.setEmail(userDetailsDto.getEmail());
			userDetails.setName(userDetailsDto.getName());
			userDetails.setPhone(userDetailsDto.getPhone());
			userDetails.setUserName(userDetailsDto.getUserName());
			UserDetails savedUserDetails = userDetailsRepository.save(userDetails);
			Address address = new Address();
			address.setAddressId(userDetailsDto.getAddress().getAddressId());
			address.setCity(userDetailsDto.getAddress().getCity());
			address.setStreet(userDetailsDto.getAddress().getStreet());
			address.setUserId(savedUserDetails);
			Address savedAddress = addressRepository.save(address);
			Geo geo = new Geo();
			geo.setGeoId(userDetailsDto.getAddress().getGeo().getGeoId());
			geo.setLat(userDetailsDto.getAddress().getGeo().getLat());
			geo.setLongitude(userDetailsDto.getAddress().getGeo().getLongitude());
			geo.setUserId(savedUserDetails);
			Geo savedGeo = geoRepository.save(geo);
			userDetails.setAddress(address);
			userDetails.setGeo(geo);
			Company company = new Company();
			company.setCompanyId(userDetailsDto.getCompany().getCompanyId());
			company.setLocation(userDetailsDto.getCompany().getName());
			company.setName(userDetailsDto.getCompany().getLocation());
			company.setUserId(savedUserDetails);
			Company savedCompany = companyRepository.save(company);
			userDetails.setAddress(savedAddress);
			userDetails.setGeo(savedGeo);
			userDetails.setCompany(savedCompany);
			return new Response<Object>("UserDetails Updated", "1");
		}

		catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in addUser of UserDetailsServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new UserException(e.getMessage());
		}

	}
}
