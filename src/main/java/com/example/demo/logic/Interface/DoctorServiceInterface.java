package com.example.demo.logic.Interface;

import com.example.demo.data.model.ComplianceCheerUpMsg;
import com.example.demo.data.model.Doctor;
import com.example.demo.data.model.DoctorPatientChat;
import com.example.demo.data.model.Patient;
import com.example.demo.data.model.Treatment;

public interface DoctorServiceInterface {
	
	DoctorPatientChat doctorAddMsg(String patientId,String doctorId,String msg);

	Patient[] getAllDoctorsPatients(String doctorId,int size,int page) ;
	
	Patient getPatientById(String doctorId,String patientId);
	
	Treatment[] getActiveTreatmentsByYearMonth(String doctorId,int year, int month,int size,int page);
	
	Treatment addTreatment(String doctorId,Treatment treatment);
	
	ComplianceCheerUpMsg updateAddCheerUpMsg(ComplianceCheerUpMsg msg);

	Doctor getDoctorDetails(String doctorId);

	void updateTreatment(String doctorId, Treatment treatment);

	Treatment[] getPatientTreatmentsHistoryByYearMonth(String patientId, int year, int month, int size, int page);

	void deletePatientTreatment(String patientId, String treatmentName);

	
	
}
