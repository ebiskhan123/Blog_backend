package com.ebby.blog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ebby.blog.dataobjects.BlogDTO;

@Repository
public interface BlogRepository extends MongoRepository<BlogDTO, String> {
	
}
