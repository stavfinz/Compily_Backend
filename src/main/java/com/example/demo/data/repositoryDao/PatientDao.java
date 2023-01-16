package com.example.demo.data.repositoryDao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.data.model.Patient;

public interface PatientDao extends PagingAndSortingRepository<Patient, String>{
	
	List<Patient> findAllByDoctorId(
			@Param("doctorId")String doctorId,
			Pageable pageable);

}
