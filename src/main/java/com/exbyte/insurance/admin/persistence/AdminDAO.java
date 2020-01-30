package com.exbyte.insurance.admin.persistence;

import java.util.List;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.LoginDTO;
import com.exbyte.insurance.point.domain.PointVO;

public interface AdminDAO {

	void create(AdminVO adminVO) throws Exception;
	AdminVO read(String adminId) throws Exception;
	AdminVO login(LoginDTO loginDTO) throws Exception;
	void update(AdminVO adminVO) throws Exception;
	void delete(String adminId) throws Exception;
	String checkPosition(String adminId) throws Exception;
	void keepSession(String adminId, String sessionId) throws Exception;
	String checkSession(String value) throws Exception;
	int countSession(String value) throws Exception;
	String checkAuthKey(String adminId) throws Exception;
	void updateAuthKey(AdminVO adminVO) throws Exception;
	int countId(String adminId) throws Exception;
	int countEmail(String adminEmail) throws Exception;
	AdminVO selectAdminByEmail(String adminEmail) throws Exception;
	void updatePw(AdminVO adminVO) throws Exception;
	List<PointVO> selectAllPoint() throws Exception;
	List<AdminVO> selectAllAdmin() throws Exception;
	List<AdminVO> selectAdminList(String value) throws Exception;
	
	int count(AdminVO adminVO, String checkType) throws Exception;

}
