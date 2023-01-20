package com.example.gloquorapostapp.dto;

import java.io.Serializable;

public class GloQuoraPostDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String postId;
	private String userId;
	private String body;
	private String title;

	public GloQuoraPostDto() {
		super();
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "GloQuoraPostDto [postId=" + postId + ", userId=" + userId + ", body=" + body + ", title=" + title + "]";
	}

}
