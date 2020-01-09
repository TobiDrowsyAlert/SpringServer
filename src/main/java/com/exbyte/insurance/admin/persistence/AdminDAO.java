package com.exbyte.insurance.admin.persistence;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.LoginDTO;

public interface AdminDAO {

	void create(AdminVO adminVO) throws Exception;
	AdminVO read(String adminId) throws Exception;
	AdminVO login(LoginDTO loginDTO) throws Exception;
	void update(AdminVO adminVO) throws Exception;
	void delete(String adminId) throws Exception;
	String checkPosition(String adminId) throws Exception;
	void keepSession(String adminId, String sessionId) throws Exception;
	String checkSession(String value) throws Exception;
	int checkDuplicateSession(String value) throws Exception;
	String checkAuthKey(String adminId) throws Exception;
	void updateAuthKey(AdminVO adminVO) throws Exception;
	int checkOverId(String adminId) throws Exception;
	int checkOverEmail(String adminEmail) throws Exception;
	AdminVO findAccountById(String adminEmail) throws Exception;
	void updatePw(AdminVO adminVO) throws Exception;
	
}
