package com.ebby.blog.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ebby.blog.dataobjects.ErrorDTO;

@Provider
public class ExceptionMapperGeneric implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {

		ErrorDTO errorDTO = ErrorDTO.builder()
				.message(ex.getMessage())
				.code(404).uri("blog URI")
				.build();
		
		return Response.serverError()
				.entity(errorDTO)
				.build();
	}

}
