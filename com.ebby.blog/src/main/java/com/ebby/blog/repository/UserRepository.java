package com.ebby.blog.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ebby.blog.dataobjects.UserDTO;

public interface UserRepository extends MongoRepository<UserDTO, String> {

	  Boolean existsByUsername(String username);

	  Boolean existsByEmail(String email);

	  Optional<UserDTO> findByUsername(String username);
	  
	}