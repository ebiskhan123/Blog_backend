package com.ebby.blog.service.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ebby.blog.dataobjects.BlogDTO;
import com.ebby.blog.repository.BlogRepository;

@Service
@Path("/api/content")
public class ContentResource {

	@Autowired
	BlogRepository blogReposiory;

	@Produces(MediaType.APPLICATION_JSON)
	@Path("/blog")
	@GET
	public Response getTest() {
		List<BlogDTO> result = blogReposiory.findAll();
		Response response = Response.ok().entity(result).build();
		return response;
	}

	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/blog")
	@POST
	public Response addNewBlog(@RequestBody BlogDTO body, @Context UriInfo uriinfo) {

		blogReposiory.insert(body);
		URI uri = uriinfo.getAbsolutePathBuilder().path(body.getId()).build();
		Response response = Response.created(uri).entity(body).build();
		return response;
	}

}
