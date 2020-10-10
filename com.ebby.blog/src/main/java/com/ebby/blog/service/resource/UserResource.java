package com.ebby.blog.service.resource;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ebby.blog.dataobjects.AuthenticationResponseDTO;
import com.ebby.blog.dataobjects.ResponseDTO;
import com.ebby.blog.dataobjects.UserDTO;
import com.ebby.blog.repository.RoleRepository;
import com.ebby.blog.repository.UserRepository;
import com.ebby.blog.security.JwtUtils;
import com.ebby.blog.security.UserDetailsImpl;

@Service
@Path("/api/auth")
public class UserResource {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/signin")
	@POST
	public Response authenticateUser(@Valid @RequestBody UserDTO loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return Response.accepted()
				.entity(AuthenticationResponseDTO
						.builder()
						.jwtToken(jwt)
						.id(userDetails.getId())
						.username(userDetails.getUsername())
						.email(userDetails.getEmail())
						.roles(roles)
						.build())
				.build();

	}

	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/signup")
	@POST
	public Response registerUser(@RequestBody UserDTO signUpRequest ,@Context UriInfo uriInfo) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return Response.status(400)
					.entity(ResponseDTO.builder()
							.status("Username is taken by someone")
							.build())
					.build();
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return Response.status(400)
					.entity(ResponseDTO.builder()
							.status("Email is taken by someone")
							.build())
					.build();
		}

		// Create new user's account
		UserDTO user = UserDTO.builder()
				.username(signUpRequest.getUsername())
				.email(signUpRequest.getEmail())
				.password(encoder.encode(signUpRequest.getPassword()))
				.roles(signUpRequest.getRoles())
				.build();
		

//		Set<R> strRoles = 
//		Set<Role> roles = signUpRequest.getRoles();

//		if (strRoles == null) {
//			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//			roles.add(userRole);
//		} else {
//			strRoles.forEach(role -> {
//				switch (role) {
//				case "admin":
//					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(adminRole);
//
//					break;
//				case "mod":
//					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(modRole);
//
//					break;
//				default:
//					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(userRole);
//				}
//			});
//		}

//		user.setRoles(roles);
		UserDTO saveduser = userRepository.save(user);
			URI uri = uriInfo.getAbsolutePathBuilder().path(saveduser.getId()).build();
		return Response.created(uri)
				.entity(ResponseDTO
						.builder()
						.id(saveduser.getId())
						.createTime(new Date())
						.status("created")
						.build())
				.build();
	}

}
