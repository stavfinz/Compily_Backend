package com.example.demo.data.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;


@Embeddable
public class TreatmentPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3084500964171668192L;

	private String treatmentName;
	private String patientId;

	public TreatmentPK() {
		super();
	}

	public TreatmentPK(String treatmentName, String patientId) {
		super();
		this.treatmentName = treatmentName;
		this.patientId = patientId;
	}

	public String getTreatmentName() {
		return treatmentName;
	}

	public void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(patientId, treatmentName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreatmentPK other = (TreatmentPK) obj;
		return Objects.equals(patientId, other.patientId) && Objects.equals(treatmentName, other.treatmentName);
	}

	@Override
	public String toString() {
		return "TreatmentPK [treatmentName=" + treatmentName + ", patientId=" + patientId + "]";
	}
	
	
	
	
}