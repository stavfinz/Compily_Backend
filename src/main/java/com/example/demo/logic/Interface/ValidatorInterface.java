package com.example.demo.logic.Interface;


import com.example.demo.data.model.Doctor;
import com.example.demo.data.model.Patient;
import com.example.demo.data.model.Treatment;

public interface ValidatorInterface {

	boolean isValidAdminId(String adminId);
	
	boolean isValidId(String id);
	
	boolean isValidDate(String date);
	
	boolean isValidYear(int year);
	
	boolean isValidMonth(int month);
	
	boolean isValidPatient(Patient patient);
	
	boolean isValidDoctor(Doctor doctor);
	
	boolean isValidTreatment(Treatment treatment);
	
	boolean isValidName(String name);
	
	boolean isValidDescription(String description);

	boolean isValidTreatmentFieldsToUpdate(Treatment treatment);
	
	boolean isValidRelated(Doctor doctor,Patient patient);
	
}
