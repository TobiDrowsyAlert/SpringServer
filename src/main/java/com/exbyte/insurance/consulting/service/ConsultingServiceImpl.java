package com.exbyte.insurance.consulting.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.commons.paging.Criteria;
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
	public List<ConsultingVO> selectAll(Criteria criteria) throws Exception {
		return consultingDAO.selectAll(criteria);
	}

	@Override
	public List<ConsultingVO> selectConsultingById(Criteria criteria, String adminId) throws Exception {
		return consultingDAO.selectConsultingById(criteria, adminId);
	}

	@Override
	public List<AdminVO> selectAdminByPoint(Criteria criteria, int adminPoint) throws Exception {
		return consultingDAO.selectAdminByPoint(criteria, adminPoint);
	}

	@Override
	public List<ConsultingVO> selectConsultingByPoint(Criteria criteria, int adminPoint) throws Exception {
		return consultingDAO.selectConsultingByPoint(criteria, adminPoint);
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

	@Override
	public void update(ConsultingVO consultingVO) throws Exception {
		consultingDAO.update(consultingVO);
	}


}
