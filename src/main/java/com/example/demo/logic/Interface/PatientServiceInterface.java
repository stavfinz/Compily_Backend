package com.example.demo.logic.Interface;

import com.example.demo.data.model.ComplianceCheerUpMsg;
import com.example.demo.data.model.DoctorPatientChat;
import com.example.demo.data.model.Patient;
import com.example.demo.data.model.Treatment;

public interface PatientServiceInterface {
	
	Patient getPatientDetails(String patientId);
	
	DoctorPatientChat getChatWithDoctor(String patientId,String doctorId);
	
	DoctorPatientChat patientAddMsg(String patientId,String doctorId,String msg);
	
	Treatment[] getPatientTreatmentsHistoryByYearMonth(String patientId, int year, int month,int size,int page);
	
	Treatment getTreatment(String patientId,String treatmentId);
	
	boolean complianceTreatment(String patientId,String treatmentId);
	
	ComplianceCheerUpMsg getComplianceCheerUpMsg();

}
