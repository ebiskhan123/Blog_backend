package com.ebby.blog.dataobjects;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Data;

@XmlRootElement
@Data
@Builder
public class ResponseDTO {

	private String status;
	private Date createTime;
	private String id;
}
