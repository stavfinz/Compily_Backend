package com.example.demo.logic.Interface;

import com.example.demo.data.model.Doctor;
import com.example.demo.data.model.DoctorPatientChat;
import com.example.demo.data.model.Patient;

public interface AdminServiceInterface {

	Patient addUpdatePatient(String adminId,Patient patient);
	
	void deletePatient(String adminId,String patientId);
	
	Doctor addUpdateDoctor(String adminId,Doctor doctor);
	
	DoctorPatientChat initChatDoctorPatien(String adminId,String doctorId,String patientId);
	
	void deleteDoctor(String adminId,String doctorId);
	
	void deleteAllDoctors(String adminId);
	
	void deleteAllPatients(String adminId);
	
	void deleteAllTreatments(String adminId);
	
	void deleteAllComplianceMsgs(String adminId);
	
	void deleteAllChats(String adminId);
	
}
