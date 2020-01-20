package com.magiawalas.chatapp;

import java.util.Date;

public class Message 
{
	Date timeStamp;
	String message;
	
	public Message (String message)
	{
		this.message = message;
	}
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
