package com.exbyte.insurance.consulting.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.exbyte.insurance.consulting.domain.ConsultingVO;
import com.exbyte.insurance.consulting.persistence.ConsultingDAO;

@Service
public class ConsultingServiceImpl implements ConsultingService {

	private static ConsultingDAO consultingDAO;
	
	@Inject
	public ConsultingServiceImpl(ConsultingDAO consultingDAO) {
		this.consultingDAO = consultingDAO;
	}
	
	@Override
	public void create(ConsultingVO consultingVO) throws Exception {
		consultingDAO.create(consultingVO);
	}

	@Override
	public List<ConsultingVO> selectAll() throws Exception {
		return consultingDAO.selectAll();
		
	}

	@Override
	public void updateCall(int consultingNo) throws Exception {
		consultingDAO.updateCall(consultingNo);
	}

	@Override
	public void updateEnd(int consultingNo) throws Exception {
		consultingDAO.updateEnd(consultingNo);
	}

	@Override
	public void delete(int consultingNo) throws Exception {
		consultingDAO.delete(consultingNo);
	}

	@Override
	public int countAll() throws Exception {
		return consultingDAO.countAll();
		
	}

	@Override
	public ConsultingVO read(int consultingNo) throws Exception {
		return consultingDAO.read(consultingNo);
		
	}

}
