package com.exbyte.insurance.admin.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.persistence.AdminDAO;

@Service
public class AdminCountService {

	AdminDAO adminDAO;
	
	@Inject
	public AdminCountService(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}

	public int countId(String adminId) throws Exception {
		return adminDAO.countId(adminId);
		
	}

	public int countEmail(String adminEmail) throws Exception {
		return adminDAO.countEmail(adminEmail);
		
	}
	
	public int countSession(String value) throws Exception {
		return adminDAO.countSession(value);
	}
	
	public int countPosition(AdminVO adminVO) throws Exception {
		
		if(adminVO.getAdminPosition().equals("사원")) {
			return 0;
		}
		
		return adminDAO.countPosition(adminVO);
		
	}
	
	public int count(AdminVO adminVO, String checkType) throws Exception {
		return adminDAO.count(adminVO, checkType);
	}

	
	
}
