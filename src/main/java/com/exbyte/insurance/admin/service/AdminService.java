package com.exbyte.insurance.admin.service;

import java.util.List;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.LoginDTO;
import com.exbyte.insurance.point.domain.PointVO;

public interface AdminService {
	// 아이디 생성
	void create(AdminVO adminVO) throws Exception;
	// 아이디 읽기
	AdminVO read(String adminId) throws Exception;
	AdminVO login(LoginDTO loginDTO) throws Exception;
	// 계정정보 변경
	void update(AdminVO adminVO) throws Exception;
	// 계정 삭제
	void delete(String adminId) throws Exception;
	// 직책 확인 ( 필요없다. ) 
	String checkPosition(String adminId) throws Exception;
	// 세션 갱신 ( 세션 만료시간 추가 필요 ) 
	void keepSession(String adminId, String sessionId) throws Exception;
	// 세션 읽기 ( 필요없다. )
	String checkSession(String value) throws Exception;
	// 중복 새션 확인
	int checkDuplicateSession(String value) throws Exception;
	// 인증키 확인 ( 필요없다. ) 
	String checkAuthKey(String adminId) throws Exception;
	// 인증키 변경
	void updateAuthKey(AdminVO adminVO) throws Exception;
	// id 갯수 확인
	int checkOverId(String adminId) throws Exception;
	// 이메일 갯수 확인
	int checkOverEmail(String adminEmail) throws Exception;
	// Email에 해당하는 아이디 찾기
	AdminVO findAccountById(String adminEmail) throws Exception;
	// 비밀번호 변경 ( 필요없다. ) 
	public void updatePw(AdminVO adminVO) throws Exception;
	// 지사 목록 출력
	public List<PointVO> listPoint() throws Exception;
	// 모든 직원 목록 출력
	public List<AdminVO> listAll() throws Exception;
	
}
