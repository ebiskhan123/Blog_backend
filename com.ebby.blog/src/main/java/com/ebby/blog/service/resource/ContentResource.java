package com.ebby.blog.service.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ebby.blog.dataobjects.BlogDTO;
import com.ebby.blog.dataobjects.ResponseDTO;
import com.ebby.blog.services.ContentService;

@Service
@Path("/api/content")
public class ContentResource {

	@Autowired
	ContentService contentService;
	
	

	@Produces(MediaType.APPLICATION_JSON)
	@Path("/blog")
	@GET
	public Response getTest() {
		
		List<BlogDTO> result = contentService.getAllBlogs();
		
		Response response = Response.ok()
				.entity(result)
				.build();
		
		return response;
	}

	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/blog")
	@POST
	public Response addNewBlog(@RequestBody BlogDTO body, @Context UriInfo uriinfo) {

		BlogDTO blogDTO = contentService.addBlog(body);
		
		ResponseDTO responseDTO = ResponseDTO.builder()
				.createTime(blogDTO.getPublishedDate())
				.status("created")
				.id(blogDTO.getId())
				.build();
		
		URI uri = uriinfo
				.getAbsolutePathBuilder()
				.path(responseDTO.getId())
				.build();
		
		Response response = Response.created(uri)
				.entity(responseDTO)
				.build();
		
		return response;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/blog/{id}")
	@PUT
	public Response updateContent(@RequestBody BlogDTO body, @Context UriInfo uriinfo, @PathParam("id") String id) throws Exception {

		BlogDTO blogDTO =  contentService.updateBlog(body,id);
		ResponseDTO responseDTO = ResponseDTO.builder()
				.createTime(blogDTO.getLastUpdatedDate())
				.status("updated")
				.id(blogDTO.getId())
				.build();
		
		URI uri = uriinfo.getAbsolutePathBuilder()
				.path(blogDTO.getId())
				.build();
		
		Response response = Response.created(uri)
				.entity(responseDTO)
				.build();
		
		return response;
	}

	
	
	
	
}
