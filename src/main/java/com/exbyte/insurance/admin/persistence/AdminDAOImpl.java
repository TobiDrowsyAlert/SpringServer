package com.exbyte.insurance.admin.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.LoginDTO;
import com.exbyte.insurance.point.domain.PointVO;

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
	public AdminVO login(LoginDTO loginDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".login", loginDTO);
		
	}
	@Override
	public void update(AdminVO adminVO) throws Exception {
		sqlSession.update(NAMESPACE + ".update", adminVO); 
	}

	@Override
	public void updatePw(AdminVO adminVO) throws Exception {
		sqlSession.update(NAMESPACE + ".updatePw", adminVO );
	}

	@Override
	public void updateAuthKey(AdminVO adminVO) throws Exception {
		sqlSession.update(NAMESPACE + ".updateAuthKey", adminVO);
	}

	@Override
	public void delete(AdminVO adminVO) throws Exception {
		sqlSession.delete(NAMESPACE + ".delete", adminVO);
	}

	@Override
	public String checkPosition(String adminId) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE + ".checkPosition", adminId);
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
	public String checkAuthKey(String adminId) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".checkAuthKey", adminId);
	}

	@Override
	public AdminVO selectPointAdmin(PointVO pointVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".selectPointAdmin", pointVO);
	}
	
	@Override
	public AdminVO selectAdminByEmail(String adminEmail) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".selectAdminByEmail", adminEmail);
	}

	@Override
	public List<AdminVO> selectAdmin(AdminVO adminVO) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".selectAdmin", adminVO);
	}
	
	@Override
	public List<PointVO> selectAllPoint() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".selectAllPoint");
		
	}

	@Override
	public List<AdminVO> selectAllAdmin() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".selectAllAdmin");
	}
	
	@Override
	public List<AdminVO> selectAdminList(String value) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".selectAdminList", value);
		
	}
	@Override
	public int countId(String adminId) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".countId", adminId);
	}

	@Override
	public int countEmail(String adminEmail) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".countEmail", adminEmail);
	}
	
	@Override
	public int countSession(String value) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".countSession", value);
	}
	
	@Override
	public int count(AdminVO adminVO, String checkType) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("adminVO", adminVO);
		paramMap.put("checkType", checkType);
		
		return sqlSession.selectOne(NAMESPACE + ".count", paramMap );
	}

	@Override
	public int countPosition(AdminVO adminVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".countPosition", adminVO);
		
	}

}
