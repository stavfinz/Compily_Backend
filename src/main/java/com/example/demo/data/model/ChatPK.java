package com.example.demo.data.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class ChatPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8198852031466777494L;
	private String patientId;
	private String doctorId;

	public ChatPK() {
		super();
	}

	public ChatPK(String patientId, String doctorId) {
		super();
		this.patientId = patientId;
		this.doctorId = doctorId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(doctorId, patientId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatPK other = (ChatPK) obj;
		return Objects.equals(doctorId, other.doctorId) && Objects.equals(patientId, other.patientId);
	}

	@Override
	public String toString() {
		return "ChatPK [patientId=" + patientId + ", doctorId=" + doctorId + "]";
	}
	
	
	
	
}
