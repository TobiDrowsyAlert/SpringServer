package com.exbyte.insurance.admin.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.LoginDTO;

@Repository
public class AdminDAOImpl implements AdminDAO{
	
	private static SqlSession sqlSession;

	private static final String NAMESPACE = "com.exbyte.insurance.admin.AdminMapper";
	
	@Inject
	public AdminDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void create(AdminVO adminVO) throws Exception {
		sqlSession.insert(NAMESPACE + ".create", adminVO);
	}

	@Override
	public AdminVO read(String adminId) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".read", adminId);
	}

	@Override
	public void update(AdminVO adminVO) throws Exception {
		sqlSession.update(NAMESPACE + ".update", adminVO); 
	}

	@Override
	public void delete(String adminId) throws Exception {
		sqlSession.delete(NAMESPACE + ".delete", adminId);
	}

	@Override
	public String checkPosition(String adminId) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE + ".checkPosition", adminId);
	}

	@Override
	public AdminVO login(LoginDTO loginDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".read", loginDTO);
		
	}

	@Override
	public void keepSession(String adminId, String sessionId) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("adminId", adminId);
		paramMap.put("sessionId", sessionId);
		sqlSession.update(NAMESPACE + ".keepSession", paramMap);
		
	}

	@Override
	public String checkSession(String value) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".checkSession", value);
	}

	@Override
	public int checkDuplicateSession(String value) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".checkDuplicateSession", value);
		
	}

}
