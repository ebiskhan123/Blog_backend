package com.ebby.blog.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.ebby.blog.repository.BlogRepository;

@SpringBootApplication
@ComponentScan(basePackages = { "com.ebby.blog.config", "com.ebby.blog.dataobjects", "com.ebby.blog.endpoints",
		"com.ebby.blog.repository","com.ebby.blog.Application","com.ebby.blog.services" })
@EnableMongoRepositories(basePackageClasses = BlogRepository.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
