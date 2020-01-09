package com.exbyte.insurance.admin.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.LoginDTO;
import com.exbyte.insurance.admin.persistence.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService {

	static private AdminDAO adminDAO;
	
	@Inject
	public AdminServiceImpl(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}

	@Override
	public void create(AdminVO adminVO) throws Exception {
		adminDAO.create(adminVO);
	}

	@Override
	public AdminVO read(String adminId) throws Exception {
		return adminDAO.read(adminId);
		
	}

	@Override
	public void update(AdminVO adminVO) throws Exception {
		adminDAO.update(adminVO);
	}

	@Override
	public void delete(String adminId) throws Exception {
		adminDAO.delete(adminId);
	}

	@Override
	public String checkPosition(String adminId) throws Exception {
		return adminDAO.checkPosition(adminId);
	}

	@Override
	public AdminVO login(LoginDTO loginDTO) throws Exception {
		return adminDAO.login(loginDTO);
		
	}

	@Override
	public void keepSession(String adminId, String sessionId) throws Exception {
		adminDAO.keepSession(adminId, sessionId);
	}

	@Override
	public String checkSession(String value) throws Exception {
		return adminDAO.checkSession(value);
	}
	
	@Override
	public int checkDuplicateSession(String value) throws Exception {
		return adminDAO.checkDuplicateSession(value);
	}

	@Override
	public String checkAuthKey(String adminId) throws Exception {
		return adminDAO.checkAuthKey(adminId);
		
	}

	@Override
	public void updateAuthKey(AdminVO adminVO) throws Exception {
		adminDAO.updateAuthKey(adminVO);
	}

	@Override
	public int checkOverId(String adminId) throws Exception {
		return adminDAO.checkOverId(adminId);
		
	}

	@Override
	public int checkOverEmail(String adminEmail) throws Exception {
		return adminDAO.checkOverEmail(adminEmail);
		
	}

	@Override
	public AdminVO findAccountById(String adminEmail) throws Exception {
		return adminDAO.findAccountById(adminEmail);
	}

	@Override
	public void updatePw(AdminVO adminVO) throws Exception {
		adminDAO.updatePw(adminVO);
	}
	
	
	
}
