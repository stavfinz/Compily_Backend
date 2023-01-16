package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.model.ComplianceCheerUpMsg;
import com.example.demo.data.model.Doctor;
import com.example.demo.data.model.DoctorPatientChat;
import com.example.demo.data.model.Patient;
import com.example.demo.data.model.Treatment;
import com.example.demo.logic.Interface.DoctorServiceInterface;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("doctor")
public class DoctorController {

	private final DoctorServiceInterface doctorService;

	// Login and Get DoctorDetails if succeeded
	@GetMapping(value = "/login/{doctorId}", produces = "application/json")
	public Doctor loginDoctor(
			@PathVariable("doctorId") String doctorId) {
		return this.doctorService.getDoctorDetails(doctorId);
	}

	// Get Patients
	@GetMapping(value = "/allPatients/{doctorId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient[] getAllDoctorsPatients(
			@PathVariable("doctorId") String doctorId,
			@RequestParam(name = "size", required = false, defaultValue = "20") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page) {
		return this.doctorService.getAllDoctorsPatients(doctorId, size, page);
	}

	// Get Specific Patient
	@GetMapping(value = "/specificPatient/{patientId}/{doctorId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient getPatientById(
			@PathVariable("patientId") String patientId,
			@PathVariable("doctorId") String doctorId) {
		return this.doctorService.getPatientById(doctorId, patientId);
	}

	// Get Patient's treatments
	@GetMapping(value = "/patientTreatments/{patientId}/{year}/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Treatment[] getPatientTreatmentsHistoryPerMonth(
			@PathVariable("patientId") String patientId,
			@PathVariable("year") int year,
			@PathVariable("month") int month,
			@RequestParam(name = "size", required = false, defaultValue = "20") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page) {
		return this.doctorService.getPatientTreatmentsHistoryByYearMonth(patientId, year, month, size, page);
	}

	// Add msg - Chat with Doctor
	@PostMapping(value = "/add/msg/chat/{patientId}/{doctorId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DoctorPatientChat addMsg(
			@PathVariable("patientId") String patientId,
			@PathVariable("doctorId") String doctorId,
			@RequestBody String msg) {
		return this.doctorService.doctorAddMsg(patientId, doctorId, msg);
	}

	// Treatments by Date(Active Treatments)
	@GetMapping(value = "/activeTreatments/{doctorId}/{year}/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Treatment[] getActiveTreatments(
			@PathVariable("doctorId") String doctorId,
			@PathVariable("year") int year,
			@PathVariable("month") int month,
			@RequestParam(name = "size", required = false, defaultValue = "20") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page) {
		return this.doctorService.getActiveTreatmentsByYearMonth(doctorId, year, month, size, page);
	}

	// Add Treatment
	@PostMapping(value = "/add/treatment/{doctorId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Treatment addTreatment(
			@PathVariable("doctorId") String doctorId,
			@RequestBody Treatment treatment) {
		return this.doctorService.addTreatment(doctorId, treatment);
	}

	// Update Treatment
	@PutMapping(value = "/update/treatment/{doctorId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateTreatment(
			@PathVariable("doctorId") String doctorId,
			@RequestBody Treatment treatment) {
		this.doctorService.updateTreatment(doctorId, treatment);
	}

	// Add/Update ComplianceCheerUpMsg
	@PostMapping(value = "/complianceCheerUpMsg/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ComplianceCheerUpMsg updateAddCheerUpMsg(
			@RequestBody ComplianceCheerUpMsg msg) {
		return this.doctorService.updateAddCheerUpMsg(msg);
	}

	// Delete Patient
	@DeleteMapping(value = "/deleteTreatment/{patientId}/{treatmentName}")
	public void deletePatient(
			@PathVariable("patientId") String patientId,
			@PathVariable("treatmentName") String treatmentName) {
		this.doctorService.deletePatientTreatment(patientId, treatmentName);
	}

}
