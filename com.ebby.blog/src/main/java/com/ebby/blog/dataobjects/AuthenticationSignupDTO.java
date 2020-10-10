package com.ebby.blog.dataobjects;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationSignupDTO {

	private String id;

	private String username;

	private String email;
	
	private List<String> roles;
	
}
