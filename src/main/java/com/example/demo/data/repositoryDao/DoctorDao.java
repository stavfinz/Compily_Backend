package com.example.demo.data.repositoryDao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.data.model.Doctor;

public interface DoctorDao extends CrudRepository<Doctor, String>{

}
