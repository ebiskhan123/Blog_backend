package com.ebby.blog.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.ebby.blog.service.endpoints.ContentEndpoint;

@Configuration
public class JerseyConfig  extends ResourceConfig{
   
	public JerseyConfig() {
		register(ContentEndpoint.class);
	}
}
