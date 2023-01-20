package com.example.adminapp.dto;

import java.io.Serializable;
import java.util.List;

public class UserDetailsDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String name;
	private String userName;
	private String email;
	private String phone;
	private AddressDto address;
	private CompanyDto company;
	List<GloQuoraPostDto> postList;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public CompanyDto getCompany() {
		return company;
	}

	public void setCompany(CompanyDto company) {
		this.company = company;
	}

	public List<GloQuoraPostDto> getPostList() {
		return postList;
	}

	public void setPostList(List<GloQuoraPostDto> postList) {
		this.postList = postList;
	}

	@Override
	public String toString() {
		return "UserDetailsDto [userId=" + userId + ", name=" + name + ", userName=" + userName + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + ", company=" + company + ", postList=" + postList + "]";
	}

}
