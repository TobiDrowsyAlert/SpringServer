package com.exbyte.insurance.admin.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.LoginDTO;
import com.exbyte.insurance.admin.domain.PointDTO;
import com.exbyte.insurance.admin.exception.InvalidAuthKeyAccessException;
import com.exbyte.insurance.admin.persistence.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService {

	private final AdminDAO adminDAO;
	
	Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	
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
	public void delete(AdminVO adminVO) throws Exception {  
		adminDAO.delete(adminVO);
	}

	@Override
	public String checkPosition(String adminId) throws Exception {
		return adminDAO.checkPosition(adminId);
	}

	@Override
	public AdminVO login(LoginDTO loginDTO) throws Exception {
		AdminVO adminVO;
		
		try {
			adminVO = adminDAO.login(loginDTO);
			String loginPw = loginDTO.getAdminPw();
			String databasePw = adminVO.getAdminPw();

			logger.info("loginPw : " + loginPw + ", databasePw = " + databasePw);
			
			if(!BCrypt.checkpw(loginPw, databasePw)) {
				throw new IllegalArgumentException();
			}
			
			if(!adminVO.getAdminAuthKey().contentEquals("Y")) {
				throw new InvalidAuthKeyAccessException();
			}
			
		}catch (InvalidAuthKeyAccessException arg1) {
			throw arg1;
		}catch (IllegalArgumentException arg2) {
			throw arg2;
		}
		
		return adminVO;
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
	public List<AdminVO> selectAdmin(AdminVO adminVO) throws Exception{
		return adminDAO.selectAdmin(adminVO);
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
	public int countPosition(AdminVO adminVO) throws Exception {
		
		if(adminVO.getAdminPosition().equals("사원")) {
			return 0;
		}
		
		return adminDAO.countPosition(adminVO);
		
	}
	
	@Override
	public int count(AdminVO adminVO, String checkType) throws Exception {
		return adminDAO.count(adminVO, checkType);
	}

	@Override
	public String hashAdminPw(String adminPw) throws Exception {
		return BCrypt.hashpw(adminPw, BCrypt.gensalt());
	}
	
	@Override
	public AdminVO registerAccount(AdminVO adminVO) throws Exception {
		String hashPw = hashAdminPw(adminVO.getAdminPw());
		adminVO.setAdminPw(hashPw);
		adminVO.setSessionKey("none");
		adminVO.setAdminAuthKey("none");
		adminDAO.create(adminVO);
		
		return adminVO;
	}

	@Override
	public List<PointDTO> selectPointAdmin() throws Exception {
		return null;
		
	}
	
	
}
