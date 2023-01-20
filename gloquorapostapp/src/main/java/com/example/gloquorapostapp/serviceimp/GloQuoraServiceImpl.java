package com.example.gloquorapostapp.serviceimp;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gloquorapostapp.dto.GloQuoraPostDto;
import com.example.gloquorapostapp.dto.Response;
import com.example.gloquorapostapp.exception.GloQuoraException;
import com.example.gloquorapostapp.modal.GloQuoraPost;
import com.example.gloquorapostapp.repository.GloQuoraRepository;
import com.example.gloquorapostapp.service.GloQuoraService;

@Service
public class GloQuoraServiceImpl implements GloQuoraService {

	Logger logger = LoggerFactory.getLogger(GloQuoraServiceImpl.class);

	@Autowired
	GloQuoraRepository gloQuoraRepository;

	@Override
	public Object addGloQuoraPost(GloQuoraPostDto gloQuoraPostDto) {
		logger.info("Inside addGloQuoraPost of GloQuoraServiceImpl");
		try {
			GloQuoraPost gloQuoraPost = new GloQuoraPost();
			gloQuoraPost.setBody(gloQuoraPostDto.getBody());
			gloQuoraPost.setTiltle(gloQuoraPostDto.getTitle());
			gloQuoraPost.setUserId(gloQuoraPostDto.getUserId());
			gloQuoraRepository.save(gloQuoraPost);
			return new Response<Object>("Glo quora post  saved Successfully", "1");

		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in addGloQuoraPost of GloQuoraServiceImpl: {0}",
					e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new GloQuoraException(e.getMessage());
		}
	}

	@Override
	public Object updateGloQuoraPost(GloQuoraPostDto gloQuoraPostDto) {
		logger.info("Inside updateGloQuoraPost of GloQuoraServiceImpl");
		try {
			Optional<GloQuoraPost> gloFromDb = gloQuoraRepository.findById(gloQuoraPostDto.getPostId());
			if (gloFromDb.isEmpty()) {
				throw new Exception("Post does not exist");
			}
			GloQuoraPost gloQuoraPost = new GloQuoraPost();
			gloQuoraPost.set_id(gloQuoraPostDto.getPostId());
			gloQuoraPost.setBody(gloQuoraPostDto.getBody());
			gloQuoraPost.setUserId(gloQuoraPostDto.getUserId());
			gloQuoraPost.setTiltle(gloQuoraPostDto.getTitle());
			gloQuoraRepository.save(gloQuoraPost);
			return new Response<Object>("Glo quora post  updated Successfully", "1");

		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in updateGloQuoraPost of GloQuoraServiceImpl: {0}",
					e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new GloQuoraException(e.getMessage());
		}

	}

	@Override
	public Object getAllGloQuoraPost() {
		logger.info("Inside getAllGloQuoraPost of GloQuoraServiceImpl");
		List<GloQuoraPostDto> responseList = new ArrayList<>();
		try {
			List<GloQuoraPost> dataFromRepo = gloQuoraRepository.findAll();
			if (dataFromRepo.size() == 0) {
				throw new GloQuoraException("Data does not exist");
			}
			for (GloQuoraPost gloQuoraPost : dataFromRepo) {
				GloQuoraPostDto gloQuoraPostDto = new GloQuoraPostDto();
				gloQuoraPostDto.setPostId(gloQuoraPost.get_id());
				gloQuoraPostDto.setBody(gloQuoraPost.getBody());
				gloQuoraPostDto.setTitle(gloQuoraPost.getTiltle());
				gloQuoraPostDto.setUserId(gloQuoraPost.getUserId());
				responseList.add(gloQuoraPostDto);

			}
			return new Response<Object>(responseList, "Glo quora post fetched Successfully", "1");

		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in getAllGloQuoraPost of GloQuoraServiceImpl: {0}",
					e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new GloQuoraException(e.getMessage());
		}

	}

	@Override
	public Object deleteGloQuoraPost(String postId) {
		logger.info("Inside deleteGloQuoraPost of GloQuoraServiceImpl");
		try {
			Optional<GloQuoraPost> gloFromDb = gloQuoraRepository.findById(postId);
			if (gloFromDb.isEmpty()) {
				throw new Exception("Post does not exist");
			}
			gloQuoraRepository.deleteById(postId);
			return new Response<Object>("Glo quora post  deleted Successfully", "1");

		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in deleteGloQuoraPost of GloQuoraServiceImpl: {0}",
					e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new GloQuoraException(e.getMessage());
		}

	}

}
