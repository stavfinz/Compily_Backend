package com.example.demo.data.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TreatmentPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3084500964171668192L;

	private String treatmentName;
	private String patientId;

}