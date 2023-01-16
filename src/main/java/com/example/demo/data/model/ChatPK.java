package com.example.demo.data.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class ChatPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8198852031466777494L;
	private String patientId;
	private String doctorId;

}
