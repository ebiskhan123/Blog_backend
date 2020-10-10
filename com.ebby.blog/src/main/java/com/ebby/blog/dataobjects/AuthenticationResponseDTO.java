package com.ebby.blog.dataobjects;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponseDTO {

	private String id;

	private String username;

	private String email;
	
	private String jwtToken;
	
	private List<String> roles;
}
