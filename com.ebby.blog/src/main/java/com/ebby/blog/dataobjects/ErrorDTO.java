package com.ebby.blog.dataobjects;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Data;

@XmlRootElement
@Data
@Builder
public class ErrorDTO {

	private String message;
	private int code;
	private String uri;

}
