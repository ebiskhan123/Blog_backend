package com.ebby.blog.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ebby.blog.dataobjects.ERole;
import com.ebby.blog.dataobjects.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	  Optional<Role> findByName(ERole name);
	}