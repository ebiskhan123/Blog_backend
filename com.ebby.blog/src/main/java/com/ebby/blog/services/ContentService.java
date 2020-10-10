package com.ebby.blog.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebby.blog.dataobjects.BlogDTO;
import com.ebby.blog.exception.DataNotFoundException;
import com.ebby.blog.repository.BlogRepository;

@Service
public class ContentService {

	@Autowired
	BlogRepository blogRepository;

	public List<BlogDTO> getAllBlogs() {

		List<BlogDTO> result = blogRepository.findAll();

		return result;
	}

	public BlogDTO addBlog(BlogDTO newblog) {

		Date current = new Date();
		newblog.setLastUpdatedDate(current);
		newblog.setPublishedDate(current);

		BlogDTO result = blogRepository.insert(newblog);
		return result;
	}

	public BlogDTO updateBlog(BlogDTO body,String Id) {
		Date current = new Date();
		body.setLastUpdatedDate(current);
		
		BlogDTO result;
		if(blogRepository.existsById(Id)) {
			body.setId(Id);
			result = blogRepository.save(body);
		}
		else throw new DataNotFoundException("No Blog exists with this ID so unable to update");
		
		return result;
	}

	public String deleteBlog(BlogDTO body, String id) {
			
		if(blogRepository.existsById(id)) {
			body.setId(id);
			blogRepository.deleteById(id);
		}
		else throw new DataNotFoundException("No Blog exists with this ID sounable to delete a blog not present");
		
		return "deleted";
	}

}
