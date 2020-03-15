package com.exbyte.insurance.log.service;

import javax.inject.Inject;

import com.exbyte.insurance.drowsy.domain.PersonalVO;
import com.exbyte.insurance.log.domain.LogVO;
import com.exbyte.insurance.log.persistence.LogDAO;

public class LogService {
	private LogDAO logDAO;
	
	@Inject
	LogService(LogDAO logDAO){
		this.logDAO = drowsyDAO;
	}

	public void create(LogVO logVO) {
		logDAO.create(logVO);
	}
	
	public void update(LogVO logVO) {
		logDAO.update(logVO);
	}
	
	public PersonalVO read(String userId) {
		return logDAO.read(userId);
	}
	
}
