package com.example.demo.logic;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.data.model.ChatPK;
import com.example.demo.data.model.ComplianceCheerUpMsg;
import com.example.demo.data.model.Doctor;
import com.example.demo.data.model.DoctorPatientChat;
import com.example.demo.data.model.Message;
import com.example.demo.data.model.Patient;
import com.example.demo.data.model.Treatment;
import com.example.demo.data.model.TreatmentPK;
import com.example.demo.data.repositoryDao.ChatDao;
import com.example.demo.data.repositoryDao.ComplianceDao;
import com.example.demo.data.repositoryDao.DoctorDao;
import com.example.demo.data.repositoryDao.PatientDao;
import com.example.demo.data.repositoryDao.TreatmentDao;
import com.example.demo.logic.Interface.PatientServiceInterface;
import com.example.demo.logic.Interface.ValidatorInterface;
import com.example.demo.logic.exception.ChatNotExistException;
import com.example.demo.logic.exception.DoctorNotExistException;
import com.example.demo.logic.exception.PatientNotExistException;
import com.example.demo.logic.exception.TreatmentNotExistException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientService implements PatientServiceInterface {

	private final DoctorDao doctorDao;
	private final PatientDao patientDao;
	private final TreatmentDao treatmentDao;
	private final ChatDao chatDao;
	private final ComplianceDao complianceDao;
	private final ValidatorInterface validator;

	@Override
	public Patient getPatientDetails(String patientId) {

		this.validator.isValidId(patientId);

		Optional<Patient> optionalPatient = this.patientDao.findById(patientId);
		if (optionalPatient.isEmpty())
			throw new PatientNotExistException("Patient not found in system.");
		return optionalPatient.get();
	}

	@Override
	public DoctorPatientChat getChatWithDoctor(String patientId, String doctorId) {

		this.validator.isValidId(patientId);
		this.validator.isValidId(doctorId);

		Optional<Patient> optionalPatient = this.patientDao.findById(patientId);
		if (optionalPatient.isEmpty())
			throw new PatientNotExistException("Patient not found in system.");
		Optional<Doctor> optionalDoctor = this.doctorDao.findById(doctorId);
		if (optionalDoctor.isEmpty())
			throw new DoctorNotExistException("Doctor not found in system.");
		this.validator.isValidRelated(optionalDoctor.get(), optionalPatient.get());
		ChatPK chatPk = new ChatPK(patientId, doctorId);
		Optional<DoctorPatientChat> optionalDoctorPatientChat = this.chatDao.findById(chatPk);
		if (optionalDoctorPatientChat.isEmpty())
			throw new ChatNotExistException("Chat is not found in system.");

		return optionalDoctorPatientChat.get();
	}

	@Override
	public DoctorPatientChat patientAddMsg(String patientId, String doctorId, String msg) {

		this.validator.isValidId(patientId);
		this.validator.isValidId(doctorId);
		this.validator.isValidDescription(msg);// Same as description , should not be empty

		Optional<Patient> optionalPatient = this.patientDao.findById(patientId);
		if (optionalPatient.isEmpty())
			throw new PatientNotExistException("Patient not found in system.");
		Optional<Doctor> optionalDoctor = this.doctorDao.findById(doctorId);
		if (optionalDoctor.isEmpty())
			throw new DoctorNotExistException("Doctor not found in system.");
		this.validator.isValidRelated(optionalDoctor.get(), optionalPatient.get());
		ChatPK chatPk = new ChatPK(patientId, doctorId);
		Optional<DoctorPatientChat> optionalDoctorPatientChat = this.chatDao.findById(chatPk);
		if (optionalDoctorPatientChat.isEmpty())
			throw new ChatNotExistException("Chat is not found in system.");

		DoctorPatientChat patientChat = optionalDoctorPatientChat.get();

		Message newMsg = new Message(patientId, System.currentTimeMillis(), msg);
		patientChat.getMessages().add(newMsg);
		return this.chatDao.save(patientChat);
	}

	@Override
	public Treatment[] getPatientTreatmentsHistoryByYearMonth(String patientId, int year, int month, int size,
			int page) {

		this.validator.isValidId(patientId);
		this.validator.isValidYear(year);
		this.validator.isValidMonth(month);

		Optional<Patient> optionalPatient = this.patientDao.findById(patientId);
		if (optionalPatient.isEmpty())
			throw new PatientNotExistException("Patient not found in system.");

		List<Treatment> relaventTreatments = this.treatmentDao
				.findAllByYearAndMonth(year, month, PageRequest.of(page, size, Direction.ASC, "day", "treatmentPk"))
				.stream().filter(t -> t.getTreatmentPk().getPatientId().equals(patientId))
				.collect(Collectors.toList());
		relaventTreatments.forEach(t -> t.setDate("" + t.getYear() + "-" + t.getMonth() + "-" + t.getDay()));

		return relaventTreatments.toArray(new Treatment[0]);
	}

	@Override
	public Treatment getTreatment(String patientId, String treatmentId) {

		this.validator.isValidId(patientId);

		Optional<Patient> optionalPatient = this.patientDao.findById(patientId);
		if (optionalPatient.isEmpty())
			throw new PatientNotExistException("Patient not found in system.");

		TreatmentPK treatmentPK = new TreatmentPK(treatmentId, patientId);
		Optional<Treatment> optionalTreatment = this.treatmentDao.findById(treatmentPK);
		if (optionalTreatment.isEmpty())
			throw new TreatmentNotExistException("Treatment not found in system.");

		Treatment treatment = optionalTreatment.get();
		treatment.setDate("" + treatment.getYear() + "-" + treatment.getMonth() + "-" + treatment.getDay());
		return optionalTreatment.get();
	}

	@Override
	public boolean complianceTreatment(String patientId, String treatmentId) {
		this.validator.isValidId(patientId);

		Optional<Patient> optionalPatient = this.patientDao.findById(patientId);
		if (optionalPatient.isEmpty())
			throw new PatientNotExistException("Patient not found in system.");

		TreatmentPK treatmentPK = new TreatmentPK(treatmentId, patientId);
		Optional<Treatment> optionalTreatment = this.treatmentDao.findById(treatmentPK);
		if (optionalTreatment.isEmpty())
			throw new TreatmentNotExistException("Treatment not found in system.");

		Treatment treatment = optionalTreatment.get();
		treatment.setComplied(true);
		Treatment savedTreatmentthis = this.treatmentDao.save(treatment);

		return savedTreatmentthis.isComplied();
	}

	@Override
	public ComplianceCheerUpMsg getComplianceCheerUpMsg() {
		int amountOfMsgs = 0;
		List<ComplianceCheerUpMsg> all = new ArrayList<>();
		this.complianceDao.findAll().forEach(all::add);
		amountOfMsgs = all.size();
		Random r = new Random();
		return all.get(r.nextInt(amountOfMsgs + 1));
	}

}
