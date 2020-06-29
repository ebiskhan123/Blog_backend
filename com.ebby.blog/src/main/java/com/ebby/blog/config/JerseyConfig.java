package com.ebby.blog.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.ebby.blog.service.resource.ContentResource;

@Configuration
public class JerseyConfig  extends ResourceConfig{
   
	public JerseyConfig() {
		register(ContentResource.class);
	}
}
