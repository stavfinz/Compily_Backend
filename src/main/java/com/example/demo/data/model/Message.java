package com.example.demo.data.model;

import java.io.Serializable;
import java.util.Objects;

public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1442797128567516503L;

	private String SenderId;
	
	private Long timeSent;
	
	private String content;

	public Message() {
		super();
	}

	public Message(String senderId, Long timeSent, String content) {
		super();
		SenderId = senderId;
		this.timeSent = timeSent;
		this.content = content;
	}

	public String getSenderId() {
		return SenderId;
	}

	public void setSenderId(String senderId) {
		SenderId = senderId;
	}

	public Long getTimeSent() {
		return timeSent;
	}

	public void setTimeSent(Long timeSent) {
		this.timeSent = timeSent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(SenderId, content, timeSent);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return Objects.equals(SenderId, other.SenderId) && Objects.equals(content, other.content)
				&& Objects.equals(timeSent, other.timeSent);
	}

	@Override
	public String toString() {
		return "Message [SenderId=" + SenderId + ", timeSent=" + timeSent + ", content=" + content + "]";
	}
	
	

}
