package com.ebby.blog.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ebby.blog.config","com.ebby.blog.dataobjects","com.ebby.blog.endpoints"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
