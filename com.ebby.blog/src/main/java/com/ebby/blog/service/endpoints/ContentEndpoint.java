package com.ebby.blog.service.endpoints;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebby.blog.dataobjects.BlogDTO;
import com.ebby.blog.repository.BlogRepository;

@Service
@Path("/test")
public class ContentEndpoint {
	
	@Autowired
	BlogRepository blogReposiory;
	
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	public String getTest() {
		BlogDTO blg = new BlogDTO();
		blg.setContent("something");
		blg.setPublishedDate(new Date());
		blg.setTitle("One");
		
		blogReposiory.save(blg);
		
		return "success";
	}

}
