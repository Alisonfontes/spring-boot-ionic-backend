package com.alisonshow.cursomc.resources.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String findName;
	private String message;
	
	public FieldMessage() {
		
	}

	public FieldMessage(String findName, String message) {
		super();
		this.findName = findName;
		this.message = message;
	}

	public String getFindName() {
		return findName;
	}

	public void setFindName(String findName) {
		this.findName = findName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
