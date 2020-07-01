package com.ebby.blog.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ebby.blog.dataobjects.ErrorDTO;

@Provider
public class ExceptionMapperGeneric implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {

		ErrorDTO errorDTO = ErrorDTO.builder()
				.message(ex.getMessage())
				.code(404).uri("blog URI")
				.build();
		
		return Response.status(Status.NOT_FOUND)
				.entity(errorDTO)
				.build();
		
	}

}
