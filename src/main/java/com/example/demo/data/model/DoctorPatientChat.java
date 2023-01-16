package com.example.demo.data.model;

import java.util.ArrayList;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DoctorPatientChat {

	@EmbeddedId
	private ChatPK chatPk;

	// Key = time + senderId , Value = msg
	@Lob
	private ArrayList<Message> messages;

}
