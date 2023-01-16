package com.example.demo.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ComplianceCheerUpMsg {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int msgId;

	private String msg;

}
