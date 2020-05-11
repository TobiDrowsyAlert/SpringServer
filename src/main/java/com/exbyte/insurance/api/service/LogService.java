package com.exbyte.insurance.api.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.exbyte.insurance.api.domain.LogVO;
import com.exbyte.insurance.api.persistence.LogDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LogService {
	
	LogDAO logDAO;
	
	@Inject
	public LogService(LogDAO logDAO) {
		this.logDAO = logDAO;
	}
	
	public void create(LogVO logVO) throws Exception {
		logDAO.create(logVO);
	}
	
	public LogVO read(int logNo) throws Exception {
		return logDAO.read(logNo);
	}
	
	public List<LogDAO> selectList() throws Exception {
		return logDAO.selectList();
	}
	
	public void delete(int logNo) throws Exception {
		logDAO.delete(logNo);
	}
	
}
