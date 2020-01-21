package com.exbyte.insurance.admin.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.LoginDTO;
import com.exbyte.insurance.admin.persistence.AdminDAO;
import com.exbyte.insurance.point.domain.PointVO;

@Service
public class AdminServiceImpl implements AdminService {

	private final AdminDAO adminDAO;
	private final AdminMailService adminMailService;
	
	@Inject
	public AdminServiceImpl(AdminDAO adminDAO, AdminMailService adminMailService) {
		this.adminDAO = adminDAO;
		this.adminMailService = adminMailService;
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
	public String checkAuthKey(String adminId) throws Exception {
		return adminDAO.checkAuthKey(adminId);
		
	}

	@Override
	public void updateAuthKey(AdminVO adminVO) throws Exception {
		adminDAO.updateAuthKey(adminVO);
	}

	@Override
	public AdminVO selectAdminByEmail(String adminEmail) throws Exception {
		return adminDAO.selectAdminByEmail(adminEmail);
	}

	@Override
	public void updatePw(AdminVO adminVO) throws Exception {
		adminDAO.updatePw(adminVO);
	}
	
	@Override
	public List<PointVO> selectAllPoint() throws Exception {
		List<PointVO> points = adminDAO.selectAllPoint();
		return points;
	}

	@Override
	public List<AdminVO> selectAllAdmin() throws Exception {
		return adminDAO.selectAllAdmin();
	}

	@Override
	public int countId(String adminId) throws Exception {
		return adminDAO.countId(adminId);
		
	}

	@Override
	public int countEmail(String adminEmail) throws Exception {
		return adminDAO.countEmail(adminEmail);
		
	}
	
	@Override
	public int countSession(String value) throws Exception {
		return adminDAO.countSession(value);
	}
	
	@Override
	public int count(AdminVO adminVO, String checkType) throws Exception {
		return adminDAO.count(adminVO, checkType);
	}

	@Override
	@Transactional
	public AdminVO registerAccount(AdminVO adminVO, String contextPath) throws Exception {
		String hashPw = BCrypt.hashpw(adminVO.getAdminPw(), BCrypt.gensalt());
		adminVO.setAdminPw(hashPw);
		adminDAO.create(adminVO);
		adminMailService.mailSend(adminVO, contextPath);
		
		return adminVO;
	}
	
	
}
