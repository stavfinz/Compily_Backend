package com.example.demo.data.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Treatment {

	@EmbeddedId
	private TreatmentPK treatmentPk;

	@JsonInclude
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Transient
	private String date;

	@JsonIgnore()
	private int year;
	@JsonIgnore()
	private int month;
	@JsonIgnore()
	private int day;

	@Lob
	private String description;

	private boolean isComplied;

}
