package com.ebby.blog.dataobjects;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data @Document(collection = "Publication")
public class BlogDTO {
	
	@Id
	private String id;
	private String content;
	private Date publishedDate;
	private Date lastUpdatedDate;
	private String title;
	private List<String> tagList;
}
