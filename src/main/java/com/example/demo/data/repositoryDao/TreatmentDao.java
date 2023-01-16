package com.example.demo.data.repositoryDao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.data.model.Treatment;
import com.example.demo.data.model.TreatmentPK;

@Transactional
public interface TreatmentDao extends PagingAndSortingRepository<Treatment,TreatmentPK>{

	List<Treatment> findAllByTreatmentPk_patientId(
			@Param("patientId")String patientId,
			Pageable pageble);

	
	List<Treatment> findAllByYearAndMonth(
			@Param("year") int year,
			@Param("month") int month,
			Pageable pageble);
	
}
