package com.example.demo.data.repositoryDao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.data.model.ChatPK;
import com.example.demo.data.model.DoctorPatientChat;


@Transactional
public interface ChatDao extends CrudRepository<DoctorPatientChat, ChatPK> {

}
