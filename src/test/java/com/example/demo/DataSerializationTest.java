package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.demo.data.model.ChatPK;
import com.example.demo.data.model.Doctor;
import com.example.demo.data.model.DoctorPatientChat;
import com.example.demo.data.model.Message;
import com.example.demo.data.model.Patient;
import com.example.demo.data.model.Treatment;
import com.example.demo.data.model.TreatmentPK;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class DataSerializationTest {

	@Test
	void serializeToJson_deserializeFromJson_Patient() throws JsonProcessingException {
		Patient patient = new Patient();
		patient.setPatientId("313588394");
		patient.setFirstName("Stav");
		patient.setLastName("Finz");
		patient.setAge(27);
		patient.setDoctorId("057049090");

		ObjectMapper mapper = new ObjectMapper();
		String jsonWritten = mapper.writeValueAsString(patient);
		// System.out.println(jsonWritten);
		String jsonExpected = "{\"patientId\":\"313588394\",\"firstName\":\"Stav\",\"lastName\":\"Finz\",\"age\":27,\"doctorId\":\"057049090\"}";
		assertEquals(jsonWritten, jsonExpected);

		Patient expectedPatient = mapper.readValue(jsonWritten, Patient.class);
		assertTrue(patient.equals(expectedPatient));
	}

	@Test
	void serializeToJson_deserializeFromJson_Doctor() throws JsonProcessingException {
		Doctor doctor = new Doctor();
		doctor.setDoctorId("057049090");
		doctor.setFirstName("Stav");
		doctor.setLastName("Finz");

		ObjectMapper mapper = new ObjectMapper();
		String jsonWritten = mapper.writeValueAsString(doctor);
		// System.out.println(jsonWritten);
		String jsonExpected = "{\"doctorId\":\"057049090\",\"firstName\":\"Stav\",\"lastName\":\"Finz\"}";
		assertEquals(jsonWritten, jsonExpected);

		Doctor expectedDoctor = mapper.readValue(jsonWritten, Doctor.class);
		assertTrue(doctor.equals(expectedDoctor));
	}

	@Test
	void serializeToJson_deserializeFromJson_Treatment() throws JsonProcessingException {
		Treatment treatment = new Treatment();

		TreatmentPK treatmentPk = new TreatmentPK();
		treatmentPk.setPatientId("313588394");
		treatmentPk.setTreatmentName("Sleep Pill");

		treatment.setTreatmentPk(treatmentPk);
		treatment.setDate("2022-05-21");
		treatment.setYear(2022);
		treatment.setMonth(5);
		treatment.setDay(21);
		treatment.setComplied(false);
		treatment.setDescription("Grab pill after dinner , and before going to sleep");

		ObjectMapper mapper = new ObjectMapper();
		String jsonWritten = mapper.writeValueAsString(treatment);
		// System.out.println(jsonWritten);
		String jsonExpected = "{\"treatmentPk\":{\"treatmentName\":\"Sleep Pill\",\"patientId\":\"313588394\"},\"date\":\"2022-05-21\",\"description\":\"Grab pill after dinner , and before going to sleep\",\"complied\":false}";
		assertEquals(jsonWritten, jsonExpected);

		Treatment expectedTreatment = mapper.readValue(jsonExpected, Treatment.class);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate validDateForYear = LocalDate.parse(expectedTreatment.getDate(), formatter);
		expectedTreatment.setDay(validDateForYear.getDayOfMonth());
		expectedTreatment.setMonth(validDateForYear.getMonthValue());
		expectedTreatment.setYear(validDateForYear.getYear());
		assertTrue(treatment.equals(expectedTreatment));
	}

	@Test
	void serializeToJson_deserializeFromJson_Chat() throws JsonProcessingException {
		DoctorPatientChat chat = new DoctorPatientChat();
		ChatPK pk = new ChatPK();
		ArrayList<Message> messages = new ArrayList<>();

		pk.setDoctorId("313588394");
		pk.setPatientId("057049090");
		chat.setChatPk(pk);

		Message msg1 = new Message("313588394", 1653136371160L, "Please take today's pill");
		Message msg2 = new Message("057049090", 1653136371160L, "Ok i will");
		Message msg3 = new Message("313588394", 1653136371160L, "Good work");
		Message msg4 = new Message("057049090", 1653136371160L, "Thanks");

		messages.add(msg1);
		messages.add(msg2);
		messages.add(msg3);
		messages.add(msg4);

		chat.setMessages(messages);

		ObjectMapper mapper = new ObjectMapper();
		String jsonWritten = mapper.writeValueAsString(chat);
		// System.out.println(jsonWritten);
		String jsonExpected = "{\"chatPk\":{\"patientId\":\"057049090\",\"doctorId\":\"313588394\"},\"messages\":[{\"timeSent\":1653136371160,\"content\":\"Please take today's pill\",\"senderId\":\"313588394\"},{\"timeSent\":1653136371160,\"content\":\"Ok i will\",\"senderId\":\"057049090\"},{\"timeSent\":1653136371160,\"content\":\"Good work\",\"senderId\":\"313588394\"},{\"timeSent\":1653136371160,\"content\":\"Thanks\",\"senderId\":\"057049090\"}]}";
		assertEquals(jsonWritten, jsonExpected);

		DoctorPatientChat expectedChat = mapper.readValue(jsonWritten, DoctorPatientChat.class);
		assertTrue(chat.equals(expectedChat));

	}
}
