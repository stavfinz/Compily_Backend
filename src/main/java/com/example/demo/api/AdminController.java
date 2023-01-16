package com.example.demo.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.data.model.Doctor;
import com.example.demo.data.model.Patient;

@RestController
@RequestMapping("admin")
public class AdminController {

	// Add/Update Patient
	@PostMapping(value = "/{adminId}/addUpdatePatient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient addUpdatePatient(
			@PathVariable("adminId") String adminId,
			@RequestBody Patient patient) {
		return null;
	}

	// Delete Patient
	@DeleteMapping(value = "/{adminId}/{patientId}")
	public void deletePatient(
			@PathVariable("adminId") String adminId,
			@PathVariable("patientId") String patientId) {
	}

	// Add/Update Doctor
	@PostMapping(value = "/{adminId}/addUpdateDoctor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Doctor addUpdateDoctor(
			@PathVariable("adminId") String adminId,
			@RequestBody Doctor doctor) {
		return null;
	}

	// Delete Doctor
	@DeleteMapping(value = "/{adminId}/{doctorId}")
	public void deleteDoctor(
			@PathVariable("adminId") String adminId,
			@PathVariable("doctorId") String doctorId) {
	}

}
