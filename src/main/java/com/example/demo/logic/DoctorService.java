package com.example.demo.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
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
import com.example.demo.logic.Interface.DoctorServiceInterface;
import com.example.demo.logic.Interface.ValidatorInterface;
import com.example.demo.logic.exception.ChatNotExistException;
import com.example.demo.logic.exception.DoctorNotExistException;
import com.example.demo.logic.exception.PatientNotExistException;
import com.example.demo.logic.exception.TreatmentNotExistException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DoctorService implements DoctorServiceInterface {

	private final DoctorDao doctorDao;
	private final PatientDao patientDao;
	private final TreatmentDao treatmentDao;
	private final ChatDao chatDao;
	private final ComplianceDao complianceDao;
	private final ValidatorInterface validator;

	@Override
	public DoctorPatientChat doctorAddMsg(String patientId, String doctorId, String msg) {
		this.validator.isValidId(patientId);
		this.validator.isValidId(doctorId);
		this.validator.isValidDescription(msg);// Same as description , should not be empty

		Optional<Patient> optionalPatient = this.patientDao.findById(patientId);
		if (optionalPatient.isEmpty())
			throw new PatientNotExistException("Patient not found in system.");
		Optional<Doctor> optionalDoctor = this.doctorDao.findById(doctorId);
		if (optionalDoctor.isEmpty())
			throw new DoctorNotExistException("Doctor not found in system.");
		ChatPK chatPk = new ChatPK(patientId, doctorId);
		this.validator.isValidRelated(optionalDoctor.get(), optionalPatient.get());
		Optional<DoctorPatientChat> optionalDoctorPatientChat = this.chatDao.findById(chatPk);
		if (optionalDoctorPatientChat.isEmpty())
			throw new ChatNotExistException("Chat is not found in system.");

		DoctorPatientChat doctorChat = optionalDoctorPatientChat.get();
		Message newMsg = new Message(doctorId, System.currentTimeMillis(), msg);

		doctorChat.getMessages().add(newMsg);
		return this.chatDao.save(doctorChat);
	}

	@Override
	public Patient[] getAllDoctorsPatients(String doctorId, int size, int page) {
		this.validator.isValidId(doctorId);

		Optional<Doctor> optionalDoctor = this.doctorDao.findById(doctorId);
		if (optionalDoctor.isEmpty())
			throw new DoctorNotExistException("Doctor not found in system.");

		return this.patientDao.findAllByDoctorId(doctorId,
				PageRequest.of(page, size, Direction.DESC, "firstName", "lastName")).stream()
				.collect(Collectors.toList())
				.toArray(new Patient[0]);
	}

	@Override
	public Patient getPatientById(String doctorId, String patientId) {
		this.validator.isValidId(patientId);
		this.validator.isValidId(doctorId);

		Optional<Patient> optionalPatient = this.patientDao.findById(patientId);
		if (optionalPatient.isEmpty())
			throw new PatientNotExistException("Patient not found in system.");
		Optional<Doctor> optionalDoctor = this.doctorDao.findById(doctorId);
		if (optionalDoctor.isEmpty())
			throw new DoctorNotExistException("Doctor not found in system.");
		this.validator.isValidRelated(optionalDoctor.get(), optionalPatient.get());
		return optionalPatient.get();
	}

	@Override
	public Treatment[] getActiveTreatmentsByYearMonth(String doctorId, int year, int month, int size, int page) {

		this.validator.isValidId(doctorId);
		this.validator.isValidYear(year);
		this.validator.isValidMonth(month);

		Optional<Doctor> optionalDoctor = this.doctorDao.findById(doctorId);
		if (optionalDoctor.isEmpty())
			throw new DoctorNotExistException("Doctor not found in system.");

		Patient[] doctorPatients = this.patientDao.findAllByDoctorId(doctorId,
				PageRequest.of(page, size, Direction.DESC, "firstName", "lastName")).stream()
				.collect(Collectors.toList())
				.toArray(new Patient[0]);

		List<Treatment> relaventTreatments = this.treatmentDao
				.findAllByYearAndMonth(year, month, PageRequest.of(page, size, Direction.ASC, "day", "treatmentPk"))
				.stream().filter(t -> isPatientIdRelavent(doctorPatients, t))
				.collect(Collectors.toList());
		relaventTreatments.forEach(t -> t.setDate("" + t.getYear() + "-" + t.getMonth() + "-" + t.getDay()));

		return relaventTreatments.toArray(new Treatment[0]);

	}

	private boolean isPatientIdRelavent(Patient[] patients, Treatment treatment) {
		for (int i = 0; i < patients.length; i++) {
			if (patients[0].getPatientId().compareTo(treatment.getTreatmentPk().getPatientId()) == 0)
				return true;
		}
		return false;
	}

	@Override
	public Treatment addTreatment(String doctorId, Treatment treatment) {
		this.validator.isValidId(doctorId);
		this.validator.isValidTreatment(treatment);

		Optional<Doctor> optionalDoctor = this.doctorDao.findById(doctorId);
		if (optionalDoctor.isEmpty())
			throw new DoctorNotExistException("Doctor not found in system.");
		Optional<Patient> optionalPatient = this.patientDao.findById(treatment.getTreatmentPk().getPatientId());
		if (optionalPatient.isEmpty())
			throw new PatientNotExistException("Patient not found in system.");
		this.validator.isValidRelated(optionalDoctor.get(), optionalPatient.get());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate validDateForYear = LocalDate.parse(treatment.getDate(), formatter);
		treatment.setDay(validDateForYear.getDayOfMonth());
		treatment.setMonth(validDateForYear.getMonthValue());
		treatment.setYear(validDateForYear.getYear());
		Treatment saved = this.treatmentDao.save(treatment);
		saved.setDate("" + saved.getYear() + "-" + saved.getMonth() + "-" + saved.getDay());
		return this.treatmentDao.save(treatment);
	}

	@Override
	public ComplianceCheerUpMsg updateAddCheerUpMsg(ComplianceCheerUpMsg msg) {
		this.validator.isValidDescription(msg.getMsg());
		return this.complianceDao.save(msg);
	}

	@Override
	public Doctor getDoctorDetails(String doctorId) {
		this.validator.isValidId(doctorId);

		Optional<Doctor> optionalDoctor = this.doctorDao.findById(doctorId);
		if (optionalDoctor.isEmpty())
			throw new DoctorNotExistException("Doctor not found in system.");

		return optionalDoctor.get();
	}

	@Override
	public void updateTreatment(String doctorId, Treatment treatment) {
		this.validator.isValidId(doctorId);
		this.validator.isValidTreatmentFieldsToUpdate(treatment);

		Optional<Doctor> optionalDoctor = this.doctorDao.findById(doctorId);
		if (optionalDoctor.isEmpty())
			throw new DoctorNotExistException("Doctor not found in system.");
		Optional<Patient> optionalPatient = this.patientDao.findById(treatment.getTreatmentPk().getPatientId());
		if (optionalPatient.isEmpty())
			throw new PatientNotExistException("Patient not found in system.");
		this.validator.isValidRelated(optionalDoctor.get(), optionalPatient.get());
		Optional<Treatment> optionalTreatment = this.treatmentDao.findById(treatment.getTreatmentPk());
		if (optionalTreatment.isEmpty())
			throw new TreatmentNotExistException("Treatment not found in system.");

		Treatment toUpdate = optionalTreatment.get();
		if (!treatment.getDescription().isEmpty()) {
			toUpdate.setDescription(treatment.getDescription());
		}

		this.treatmentDao.save(toUpdate);

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
	public void deletePatientTreatment(String patientId, String treatmentName) {
		this.validator.isValidId(patientId);
		this.validator.isValidName(treatmentName);

		Optional<Patient> optionalPatient = this.patientDao.findById(patientId);
		if (optionalPatient.isEmpty())
			throw new PatientNotExistException("Patient not found in system.");

		TreatmentPK pk = new TreatmentPK(treatmentName, patientId);
		Optional<Treatment> optionalTreatment = this.treatmentDao.findById(pk);
		if (optionalTreatment.isEmpty())
			throw new TreatmentNotExistException("Treatment not found in system.");

		this.treatmentDao.deleteById(pk);

	}

}
