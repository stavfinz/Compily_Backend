package com.example.demo.logic;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.model.ChatPK;
import com.example.demo.data.model.Doctor;
import com.example.demo.data.model.DoctorPatientChat;
import com.example.demo.data.model.Message;
import com.example.demo.data.model.Patient;
import com.example.demo.data.repositoryDao.ChatDao;
import com.example.demo.data.repositoryDao.ComplianceDao;
import com.example.demo.data.repositoryDao.DoctorDao;
import com.example.demo.data.repositoryDao.PatientDao;
import com.example.demo.data.repositoryDao.TreatmentDao;
import com.example.demo.logic.Interface.AdminServiceInterface;
import com.example.demo.logic.Interface.ValidatorInterface;
import com.example.demo.logic.exception.DoctorNotExistException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminService implements AdminServiceInterface {

	private final DoctorDao doctorDao;
	private final PatientDao patientDao;
	private final TreatmentDao treatmentDao;
	private final ChatDao chatDao;
	private final ComplianceDao complianceDao;
	private final ValidatorInterface validator;

	@Override
	public Patient addUpdatePatient(String adminId, Patient patient) {
		this.validator.isValidAdminId(adminId);
		this.validator.isValidPatient(patient);

		Optional<Doctor> optionalDoctor = this.doctorDao.findById(patient.getDoctorId());
		if (optionalDoctor.isEmpty())
			throw new DoctorNotExistException("Doctor not found in system.");

		return this.patientDao.save(patient);
	}

	@Override
	public void deletePatient(String adminId, String patientId) {
		this.validator.isValidAdminId(adminId);
		this.validator.isValidId(patientId);

		this.patientDao.deleteById(patientId);

	}

	@Override
	public Doctor addUpdateDoctor(String adminId, Doctor doctor) {
		this.validator.isValidAdminId(adminId);
		this.validator.isValidDoctor(doctor);

		return this.doctorDao.save(doctor);
	}

	@Override
	public void deleteDoctor(String adminId, String doctorId) {
		this.validator.isValidAdminId(adminId);
		this.validator.isValidId(doctorId);

		this.doctorDao.deleteById(doctorId);

	}

	@Override
	public void deleteAllDoctors(String adminId) {
		this.validator.isValidAdminId(adminId);
		this.doctorDao.deleteAll();

	}

	@Override
	public void deleteAllPatients(String adminId) {
		this.validator.isValidAdminId(adminId);
		this.patientDao.deleteAll();
	}

	@Override
	public void deleteAllTreatments(String adminId) {
		this.validator.isValidAdminId(adminId);
		this.treatmentDao.deleteAll();
	}

	@Override
	public void deleteAllComplianceMsgs(String adminId) {
		this.validator.isValidAdminId(adminId);
		this.complianceDao.deleteAll();
	}

	@Override
	public void deleteAllChats(String adminId) {
		this.validator.isValidAdminId(adminId);
		this.chatDao.deleteAll();
	}

	@Override
	public DoctorPatientChat initChatDoctorPatien(String adminId, String doctorId, String patientId) {
		this.validator.isValidAdminId(adminId);

		DoctorPatientChat doctorPatientChat = new DoctorPatientChat();
		ChatPK pk = new ChatPK(patientId, doctorId);
		doctorPatientChat.setChatPk(pk);
		doctorPatientChat.setMessages(new ArrayList<Message>());

		return this.chatDao.save(doctorPatientChat);
	}

}
