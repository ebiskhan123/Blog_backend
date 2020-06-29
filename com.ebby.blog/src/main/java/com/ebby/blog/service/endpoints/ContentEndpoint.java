package com.ebby.blog.service.endpoints;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.ebby.blog.dataobjects.BlogDTO;

@Service
@Path("/test")
public class ContentEndpoint {
	
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	public String getTest() {
		return "test";
	}

}
