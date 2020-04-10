package com.exbyte.insurance.drowsy.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.exbyte.insurance.drowsy.domain.PersonalVO;
import com.exbyte.insurance.drowsy.persistence.DrowsyDAO;

@Service
public class DrowsyService {

	private DrowsyDAO drowsyDAO;
	
	@Inject
	DrowsyService(DrowsyDAO drowsyDAO){
		this.drowsyDAO = drowsyDAO;
	}

	public void create(PersonalVO personalVO) {
		drowsyDAO.create(personalVO);
	}
	
	public void update(String userId) {
		drowsyDAO.update(userId);
	}
	
	public PersonalVO read(String userId) {
		return drowsyDAO.read(userId);
	}
	
}
