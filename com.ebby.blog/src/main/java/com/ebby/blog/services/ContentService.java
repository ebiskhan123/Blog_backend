package com.ebby.blog.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebby.blog.dataobjects.BlogDTO;
import com.ebby.blog.dataobjects.ResponseDTO;
import com.ebby.blog.repository.BlogRepository;

@Service
public class ContentService {

	@Autowired
	BlogRepository blogRepository;

	public List<BlogDTO> getAllBlogs() {

		List<BlogDTO> result = blogRepository.findAll();

		return result;
	}

	public ResponseDTO addBlog(BlogDTO newblog) {

		Date current = new Date();
		newblog.setLastUpdatedDate(current);
		newblog.setPublishedDate(current);

		BlogDTO result = blogRepository.insert(newblog);

		ResponseDTO responseDTO = ResponseDTO.builder().createTime(current).status("created").id(result.getId())
				.build();

		return responseDTO;
	}

}
