package com.exbyte.insurance.consulting.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.exbyte.insurance.consulting.domain.ConsultingVO;
import com.exbyte.insurance.consulting.persistence.ConsultingDAO;

@Service
public class ConsultingServiceOutside {
	private static ConsultingDAO consultingDAO;
	
	@Inject
	public ConsultingServiceOutside(ConsultingDAO consultingDAO) {
		this.consultingDAO = consultingDAO;
	}
	
	public void updateAdminConsultingPosition(String adminId) throws Exception {
		
		List<ConsultingVO> list = consultingDAO.selectByAdmin(adminId);
		for(ConsultingVO consulting : list) {
			consulting.setAdminId("admin");
			consultingDAO.update(consulting);
		}
		
	}
}
