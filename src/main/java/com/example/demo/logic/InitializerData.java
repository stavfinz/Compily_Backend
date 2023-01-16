package com.example.demo.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.demo.data.model.Doctor;
import com.example.demo.data.model.Patient;

@Component
@Profile("init_data")
public class InitializerData implements CommandLineRunner {


	private AdminService adminService;
	
	@Autowired
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	@Override
	public void run(String... args) throws Exception {
		String adminId = "aristo123";
		
		Doctor doctor1 = new Doctor();
		doctor1.setDoctorId("135791357");
		doctor1.setFirstName("Ronen");
		doctor1.setLastName("Cohen");
		
		Doctor doctor2 = new Doctor();
		doctor2.setDoctorId("246824682");
		doctor2.setFirstName("Haim");
		doctor2.setLastName("Goldberg");
		
		this.adminService.addUpdateDoctor(adminId, doctor1);
		this.adminService.addUpdateDoctor(adminId, doctor2);
		
		Patient patient1 = new Patient();
		patient1.setPatientId("313315317");
		patient1.setFirstName("Gal");
		patient1.setLastName("Baruh");
		patient1.setAge(25);
		patient1.setDoctorId("135791357");
		
		Patient patient2 = new Patient();
		patient2.setPatientId("424426428");
		patient2.setFirstName("Tzvika");
		patient2.setLastName("Shaul");
		patient2.setAge(48);
		patient2.setDoctorId("135791357");
		
		Patient patient3 = new Patient();
		patient3.setPatientId("212213214");
		patient3.setFirstName("Lior");
		patient3.setLastName("Menashe");
		patient3.setAge(32);
		patient3.setDoctorId("135791357");
		
		Patient patient4 = new Patient();
		patient4.setPatientId("414415416");
		patient4.setFirstName("Hanoch");
		patient4.setLastName("Rozenshtein");
		patient4.setAge(37);
		patient4.setDoctorId("246824682");
		
		Patient patient5 = new Patient();
		patient5.setPatientId("515516517");
		patient5.setFirstName("Rivka");
		patient5.setLastName("Shalev");
		patient5.setAge(51);
		patient5.setDoctorId("246824682");
		
		this.adminService.addUpdatePatient(adminId, patient1);
		this.adminService.addUpdatePatient(adminId, patient2);
		this.adminService.addUpdatePatient(adminId, patient3);
		this.adminService.addUpdatePatient(adminId, patient4);
		this.adminService.addUpdatePatient(adminId, patient5);
		
		this.adminService.initChatDoctorPatien(adminId, doctor1.getDoctorId(), patient1.getPatientId());
		this.adminService.initChatDoctorPatien(adminId, doctor1.getDoctorId(), patient2.getPatientId());
		this.adminService.initChatDoctorPatien(adminId, doctor1.getDoctorId(), patient3.getPatientId());
		
		this.adminService.initChatDoctorPatien(adminId, doctor2.getDoctorId(), patient4.getPatientId());
		this.adminService.initChatDoctorPatien(adminId, doctor2.getDoctorId(), patient5.getPatientId());
		
	
	}
}
