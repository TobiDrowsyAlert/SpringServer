package com.exbyte.insurance.api.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.exbyte.insurance.api.domain.LogVO;
import com.exbyte.insurance.api.persistence.MinuteLogDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MinuteLogService {
	
	MinuteLogDAO minuteLogDAO;
	
	@Inject
	public MinuteLogService(MinuteLogDAO minuteLogDAO) {
		this.minuteLogDAO = minuteLogDAO;
	}
	
	public void create(LogVO logVO) throws Exception {
		minuteLogDAO.create(logVO);
	}
	
	public LogVO read(int logNo) throws Exception {
		return minuteLogDAO.read(logNo);
	}
	
	public List<LogVO> selectList() throws Exception {
		return minuteLogDAO.selectList();
	}
	
	public void delete(int logNo) throws Exception {
		minuteLogDAO.delete(logNo);
	}
	
}
