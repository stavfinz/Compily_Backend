package com.example.demo.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.demo.data.model.Doctor;
import com.example.demo.data.model.Patient;
import com.example.demo.data.model.Treatment;
import com.example.demo.logic.Interface.ValidatorInterface;
import com.example.demo.logic.exception.InValidInputException;
import com.example.demo.logic.exception.InValidRelatedException;

@Component
public class Validator implements ValidatorInterface {

	@Override
	public boolean isValidAdminId(String adminId) {
		return adminId.equals("aristof123");
	}

	@Override
	public boolean isValidId(String id) {
		if (id.length() != 9)
			throw new InValidInputException("Id length is not equals 9 characters.");
		if (!id.matches("[0-9]+"))
			throw new InValidInputException("Id is not only numbers.");
		return true;
	}

	@Override
	public boolean isValidDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Date validDate = dateFormat.parse(date.trim());
			LocalDate validDateForYear = LocalDate.parse(date,formatter);
			this.isValidYear(validDateForYear.getYear());
			
		} catch (ParseException pe) {
			throw new InValidInputException("Date must be in format yyyy-MM-dd");
		}
		return true;

	}

	@Override
	public boolean isValidYear(int year) {

		if (year < 1950) {
			throw new InValidInputException("Year must be at least 1950.");
		}
		return true;
	}

	@Override
	public boolean isValidMonth(int month) {
	
		if (month > 13 || month < 0)
			throw new InValidInputException("Month must be between 1-12.");
		return false;
	}

	@Override
	public boolean isValidPatient(Patient patient) {
		isValidId(patient.getDoctorId());
		isValidId(patient.getPatientId());
		isValidName(patient.getFirstName());
		isValidName(patient.getLastName());
		if (patient.getAge() <= 0 || patient.getAge() > 120)
			throw new InValidInputException("Age must be a positive number between 0 to 120.");

		return true;
	}

	@Override
	public boolean isValidDoctor(Doctor doctor) {
		isValidId(doctor.getDoctorId());
		isValidName(doctor.getFirstName());
		isValidName(doctor.getLastName());
		return true;
	}

	@Override
	public boolean isValidTreatment(Treatment treatment) {
		isValidDate(treatment.getDate());
		isValidDescription(treatment.getDescription());
		isValidId(treatment.getTreatmentPk().getPatientId());
		isValidName(treatment.getTreatmentPk().getTreatmentName());
		
		return true;
	}

	@Override
	public boolean isValidName(String name) {
		if (name.length() < 2)
			throw new InValidInputException("Name length is not at least 2 characters.");
		for (int i = 0; i < name.length(); i++) {
			if (i == 0) {
				if (name.charAt(i) < 'A' || name.charAt(i) > 'Z')// Check Capital Letter
					throw new InValidInputException("Name must start with capital letter.");
			} else {
				if (name.charAt(i) < 'a' || name.charAt(i) > 'z')// Check if rest are small letters
					throw new InValidInputException("Name must have small letters.");
			}
		}
		return true;
	}

	@Override
	public boolean isValidDescription(String description) {
		if( description.isEmpty())
			throw new InValidInputException("Description must be not empty.");
		return true;
	}

	@Override
	public boolean isValidTreatmentFieldsToUpdate(Treatment treatment) {
		if(treatment.getDescription().isEmpty())
			throw new InValidInputException("Treatment to update must have description.");
		return true;
	}

	@Override
	public boolean isValidRelated(Doctor doctor, Patient patient) {
		if(!patient.getDoctorId().equals(doctor.getDoctorId())) {
			throw new InValidRelatedException("Doctor and Patient are Not Related!");
		}
		return true;
	}

}
