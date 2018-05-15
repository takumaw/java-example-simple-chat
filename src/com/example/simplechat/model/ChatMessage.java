package com.example.simplechat.model;

import java.io.Serializable;
import java.util.Date;

public class ChatMessage implements Serializable {
	protected String name;
	protected String message;
	protected String ipAddress;
	protected Date timestamp;
	
	public ChatMessage() {
		
	}
	
	public ChatMessage(String name, String message, String ipAddress, Date timestamp) {
		super();
		this.name = name;
		this.message = message;
		this.ipAddress = ipAddress;
		this.timestamp = timestamp;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
