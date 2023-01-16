package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.model.ComplianceCheerUpMsg;
import com.example.demo.data.model.DoctorPatientChat;
import com.example.demo.data.model.Patient;
import com.example.demo.data.model.Treatment;
import com.example.demo.logic.Interface.PatientServiceInterface;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("patient")
public class PatientController {

	private final PatientServiceInterface patientService;

	// Login and Get PatientDetails if succeeded
	@GetMapping(value = "/login/{patientId}", produces = "application/json")
	public Patient loginPatient(
			@PathVariable("patientId") String patientId) {
		return this.patientService.getPatientDetails(patientId);
	}

	// Chat with Doctor
	@GetMapping(value = "/chat/{patientId}/{doctorId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public DoctorPatientChat getChat(
			@PathVariable("patientId") String patientId,
			@PathVariable("doctorId") String doctorId) {
		return this.patientService.getChatWithDoctor(patientId, doctorId);
	}

	// Add msg - Chat with Doctor
	@PostMapping(value = "/add/msg/chat{patientId}/{doctorId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DoctorPatientChat addMsg(
			@PathVariable("patientId") String patientId,
			@PathVariable("doctorId") String doctorId,
			@RequestBody String msg) {
		return this.patientService.patientAddMsg(patientId, doctorId, msg);
	}

	// Treatments history by Date
	@GetMapping(value = "/allTreatments/{patientId}/{year}/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Treatment[] getTreatmentsHistoryPerMonth(
			@PathVariable("patientId") String patientId,
			@PathVariable("year") int year,
			@PathVariable("month") int month,
			@RequestParam(name = "size", required = false, defaultValue = "20") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page) {
		return this.patientService.getPatientTreatmentsHistoryByYearMonth(patientId, year, month, size, page);
	}

	// Get Treatment
	@GetMapping(value = "/specificTreatment/{treatmentName}/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Treatment getTreatment(
			@PathVariable("patientId") String patientId,
			@PathVariable("treatmentName") String treatmentName) {
		return this.patientService.getTreatment(patientId, treatmentName);
	}

	// Compliance to treatment
	@PostMapping(value = "/complieTreatment/{treatmentName}/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean complianceTreatment(
			@PathVariable("patientId") String patientId,
			@PathVariable("treatmentName") String treatmentName) {
		return this.patientService.complianceTreatment(patientId, treatmentName);
	}

	// Get CheerUpMsg(Randomly)
	@GetMapping(value = "/complianceCheerUpMsg", produces = MediaType.APPLICATION_JSON_VALUE)
	public ComplianceCheerUpMsg getComplianceCheerUpMsg() {
		return this.patientService.getComplianceCheerUpMsg();
	}

}
