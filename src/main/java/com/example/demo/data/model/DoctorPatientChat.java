package com.example.demo.data.model;

import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class DoctorPatientChat {

	@EmbeddedId
	private ChatPK chatPk;

	// Key = time + senderId , Value = msg
	@Lob
	private ArrayList<Message> messages;

	public DoctorPatientChat() {
		super();
	}

	public DoctorPatientChat(ChatPK chatPk, ArrayList<Message> messages) {
		super();
		this.chatPk = chatPk;
		this.messages = messages;
	}

	public ChatPK getChatPk() {
		return chatPk;
	}

	public void setChatPk(ChatPK chatPk) {
		this.chatPk = chatPk;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chatPk, messages);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoctorPatientChat other = (DoctorPatientChat) obj;
		return Objects.equals(chatPk, other.chatPk) && Objects.equals(messages, other.messages);
	}

	@Override
	public String toString() {
		return "DoctorPatientChat [chatPk=" + chatPk + ", messages=" + messages + "]";
	}
	
	

}
