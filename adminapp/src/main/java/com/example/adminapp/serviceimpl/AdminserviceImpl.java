package com.example.adminapp.serviceimpl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.adminapp.dto.GloQuoraPostDto;
import com.example.adminapp.dto.Response;
import com.example.adminapp.dto.UserDetailsDto;
import com.example.adminapp.exception.AdminException;
import com.example.adminapp.feign.GloQuoraAppProxy;
import com.example.adminapp.feign.UserDetailsProxy;
import com.example.adminapp.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service

public class AdminserviceImpl implements AdminService {

	Logger logger = LoggerFactory.getLogger(AdminserviceImpl.class);

	RestTemplate restTemplate;

	@Autowired
	GloQuoraAppProxy gloQuoraAppProxy;

	@Autowired
	UserDetailsProxy userDetailsProxy;
	

	@Override
	public Object getUserPostById(String userId) {
		logger.info("Inside getUserDataByRestTemplate of FeignDemo3Controller");
		try {
			// ResponseEntity<Response> response = new RestTemplate()
			// .getForEntity("http://localhost:8080/v1/getAllQuoraPost", Response.class);
			// ResponseEntity<Response> response = restTemplate
			// .getForEntity("http://8762/gloquorapplication/v1/getAllQuoraPost",
			// Response.class);
			/*
			 * ResponseEntity<Response> response = restTemplate
			 * .getForEntity("http://gloquorapplication/v1/getAllQuoraPost",
			 * Response.class);
			 */
			ResponseEntity<Object> response = gloQuoraAppProxy.getAllGloQuoraStatus();
			ObjectMapper objectMapper = new ObjectMapper();
			Response response2 = objectMapper.convertValue(response.getBody(), Response.class);
			logger.info("Response is :{}", response2.getData());
			Object object = response2.getData();
			List<GloQuoraPostDto> listDataResponse = new ArrayList<>();
			ArrayList<Object> listData = (ArrayList<Object>) object;
			logger.info("List Data is:{}", listData);
			for (Object object3 : listData) {
				ObjectMapper objectMapper1 = new ObjectMapper();
				GloQuoraPostDto response3 = objectMapper.convertValue(object3, GloQuoraPostDto.class);
				if (response3.getUserId().equals(userId)) {
					listDataResponse.add(response3);
				}
				System.out.println(response3);
			}
			return new Response<Object>(listDataResponse, "Post  fetched Successfully", "1");
		} catch (Exception e) {
			e.printStackTrace();
			String errorMsg = MessageFormat.format("Exception caught in getUserPostById of AdminService :{0}", e);
			logger.error(errorMsg);
			throw new AdminException(e.getMessage());

		}
	}

	@Override
	public Object getAllUserPost() {
		try {
			/*
			 * ResponseEntity<Response> responseFromUserDetails = new RestTemplate()
			 * .getForEntity("http://localhost:8765/v1/getAllUsersDetails", Response.class);
			 */
			ResponseEntity<Object> responseFromUserDetails = userDetailsProxy.getAllUserDetails();
			ObjectMapper objectMapper3 = new ObjectMapper();
			Response response4 = objectMapper3.convertValue(responseFromUserDetails.getBody(), Response.class);
			logger.info("Response is :{}", response4.getData());
			Object object10 = response4.getData();
			List<UserDetailsDto> listDataResponse2 = new ArrayList<>();
			ArrayList<Object> listData4 = (ArrayList<Object>) object10;
			for (Object object3 : listData4) {
				ObjectMapper objectMapper1 = new ObjectMapper();
				UserDetailsDto response3 = objectMapper1.convertValue(object3, UserDetailsDto.class);
				listDataResponse2.add(response3);
			}
			logger.info("listDataResponse2 is :{}", listDataResponse2);
			/*
			 * ResponseEntity<Response> response = new RestTemplate()
			 * .getForEntity("http://localhost:8080/v1/getAllQuoraPost", Response.class);
			 */
			ResponseEntity<Object> response = gloQuoraAppProxy.getAllGloQuoraStatus();
			ObjectMapper objectMapper = new ObjectMapper();
			Response response2 = objectMapper.convertValue(response.getBody(), Response.class);
			Object object = response2.getData();
			List<GloQuoraPostDto> listDataResponse = new ArrayList<>();
			;
			ArrayList<Object> listData = (ArrayList<Object>) object;
			for (Object object3 : listData) {
				ObjectMapper objectMapper1 = new ObjectMapper();
				GloQuoraPostDto response3 = objectMapper.convertValue(object3, GloQuoraPostDto.class);
				listDataResponse.add(response3);
				System.out.println(response3);
			}
			logger.info("listDataResponse is:{}", listDataResponse);
			HashMap<String, List<GloQuoraPostDto>> map = new HashMap<>();
			for (GloQuoraPostDto gloQuoraPostDto : listDataResponse) {
				if (map.containsKey(gloQuoraPostDto.getUserId())) {
					List<GloQuoraPostDto> list = map.get(gloQuoraPostDto.getUserId());
					list.add(gloQuoraPostDto);
					map.put(gloQuoraPostDto.getUserId(), list);
				} else {
					ArrayList<GloQuoraPostDto> list1 = new ArrayList<>();
					list1.add(gloQuoraPostDto);
					map.put(gloQuoraPostDto.getUserId(), list1);
				}

			}
			logger.info("map is:{}", map);
			List<UserDetailsDto> finalList = new ArrayList<>();
			for (UserDetailsDto userDetailsDto : listDataResponse2) {
				if (map.containsKey(String.valueOf(userDetailsDto.getUserId()))) {
					logger.info("Post for a user is :{}", map.get(String.valueOf(userDetailsDto.getUserId())));
					userDetailsDto.setPostList(map.get(String.valueOf(userDetailsDto.getUserId())));
					finalList.add(userDetailsDto);
				}
			}
			return new Response<Object>(finalList, "Post  fetched Successfully", "1");
		} catch (Exception e) {
			e.printStackTrace();
			String errorMsg = MessageFormat.format("Exception caught in getUserPostById of AdminService :{0}", e);
			logger.error(errorMsg);
			throw new AdminException(e.getMessage());
		}
	}

	@Override
	public Object getUserPostByNumber(Long postCount) {
		logger.info("Inside getUserDataByRestTemplate of FeignDemo3Controller");
		try {
			/*
			 * ResponseEntity<Response> response = new RestTemplate()
			 * .getForEntity("http://localhost:8080/v1/getAllQuoraPost", Response.class);
			 */
			
			ResponseEntity<Object> response = gloQuoraAppProxy.getAllGloQuoraStatus();
			ObjectMapper objectMapper = new ObjectMapper();
			Response response2 = objectMapper.convertValue(response.getBody(), Response.class);
			logger.info("Response is :{}", response2.getData());
			Object object = response2.getData();
			List<GloQuoraPostDto> listDataResponse = new ArrayList<>();
			;
			ArrayList<Object> listData = (ArrayList<Object>) object;
			logger.info("List Data is:{}", listData);
			for (Object object3 : listData) {
				ObjectMapper objectMapper1 = new ObjectMapper();
				GloQuoraPostDto response3 = objectMapper.convertValue(object3, GloQuoraPostDto.class);
				listDataResponse.add(response3);
				System.out.println(response3);
			}
			HashMap<String, List<GloQuoraPostDto>> map = new HashMap<>();
			for (GloQuoraPostDto gloQuoraPostDto : listDataResponse) {
				if (map.containsKey(gloQuoraPostDto.getUserId())) {
					List<GloQuoraPostDto> list = map.get(gloQuoraPostDto.getUserId());
					list.add(gloQuoraPostDto);
					map.put(gloQuoraPostDto.getUserId(), list);
				} else {
					ArrayList<GloQuoraPostDto> list1 = new ArrayList<>();
					list1.add(gloQuoraPostDto);
					map.put(gloQuoraPostDto.getUserId(), list1);
				}

			}
			List<Entry<String, Long>> list = listDataResponse.stream()
					.collect(Collectors.groupingBy(GloQuoraPostDto::getUserId, LinkedHashMap::new,
							Collectors.counting()))
					.entrySet().stream().filter(x -> x.getValue() >= postCount).collect(Collectors.toList());
			List<GloQuoraPostDto> finalList = new ArrayList<>();
			for (Entry<String, Long> entry : list) {
				finalList.addAll(map.get(entry.getKey()));
			}
			return new Response<Object>(finalList, "Post  fetched Successfully", "1");
		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in getUserPostById of AdminService :{0}", e);
			logger.error(errorMsg);
			throw new AdminException(e.getMessage());

		}
	}

	@Override
	public Object getCompanyName() {
		logger.info("Inside getUserDataByRestTemplate of FeignDemo3Controller");
		try {
			/*
			 * ResponseEntity<Response> response = new RestTemplate()
			 * .getForEntity("http://localhost:8080/v1/getAllQuoraPost", Response.class);
			 */
			ResponseEntity<Object> response = gloQuoraAppProxy.getAllGloQuoraStatus();
			ObjectMapper objectMapper = new ObjectMapper();
			Response response2 = objectMapper.convertValue(response.getBody(), Response.class);
			logger.info("Response is :{}", response2.getData());
			Object object = response2.getData();
			List<GloQuoraPostDto> listDataResponse = new ArrayList<>();
			;
			ArrayList<Object> listData = (ArrayList<Object>) object;
			logger.info("List Data is:{}", listData);
			for (Object object3 : listData) {
				ObjectMapper objectMapper1 = new ObjectMapper();
				GloQuoraPostDto response3 = objectMapper.convertValue(object3, GloQuoraPostDto.class);
				listDataResponse.add(response3);
			}
			List<String> listOfUersID = listDataResponse.stream().map(x -> x.getUserId()).collect(Collectors.toList());

			/*
			 * ResponseEntity<Response> responseFromUserDetails = new RestTemplate()
			 * .getForEntity("http://localhost:8765/v1/getAllUsersDetails", Response.class);
			 */
			ResponseEntity<Object> responseFromUserDetails =userDetailsProxy.getAllUserDetails();
			ObjectMapper objectMapper3 = new ObjectMapper();
			Response response4 = objectMapper3.convertValue(responseFromUserDetails.getBody(), Response.class);
			logger.info("Response is :{}", response4.getData());
			Object object10 = response4.getData();
			List<UserDetailsDto> listDataResponse2 = new ArrayList<>();
			ArrayList<Object> listData4 = (ArrayList<Object>) object10;
			logger.info("List Data is:{}", listData);
			for (Object object3 : listData4) {
				ObjectMapper objectMapper1 = new ObjectMapper();
				UserDetailsDto response3 = objectMapper1.convertValue(object3, UserDetailsDto.class);
				listDataResponse2.add(response3);
			}
			Map<String, String> map = new HashMap<>();
			for (UserDetailsDto userDetailsDto : listDataResponse2) {
				map.put(String.valueOf(userDetailsDto.getUserId()), userDetailsDto.getCompany().getName());
			}
			Set<String> companyNames = new HashSet<>();
			for (String s : listOfUersID) {
				companyNames.add(map.get(s));
			}

			return new Response<Object>(companyNames, "Company names fetched Successfully", "1");
		} catch (Exception e) {
			e.printStackTrace();
			String errorMsg = MessageFormat.format("Exception caught in getUserPostById of AdminService :{0}", e);
			logger.error(errorMsg);
			throw new AdminException(e.getMessage());

		}

	}

}
