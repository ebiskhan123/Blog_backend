package com.ebby.blog.dataobjects;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BlogDTO {
	
	private Long id;
	private String content;
	private Date publishedDate;
	private Date lastUpdatedDate;
	private String title;
	private List<String> tagList;
}
