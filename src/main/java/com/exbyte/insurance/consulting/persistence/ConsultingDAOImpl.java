package com.exbyte.insurance.consulting.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.commons.paging.Criteria;
import com.exbyte.insurance.consulting.domain.ConsultingVO;

@Repository
public class ConsultingDAOImpl implements ConsultingDAO {

	private static SqlSession sqlSession;
	private static final String NAMESPACE = "com.exbyte.insurance.consulting.ConsultingMapper";
	

	@Inject
	public ConsultingDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	@Override
	public void create(ConsultingVO consultingVO) throws Exception {
		sqlSession.insert(NAMESPACE + ".create", consultingVO);
	}

	@Override
	public List<ConsultingVO> selectAll(Criteria criteria) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".selectAll", criteria);
	}



	@Override
	public List<ConsultingVO> selectConsultingById(Criteria criteria, String adminId) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("criteria", criteria);
		paramMap.put("adminId", adminId);
		return sqlSession.selectList(NAMESPACE + ".selectConsultingById", paramMap);
		
	}


	@Override
	public List<AdminVO> selectAdminByPoint(Criteria criteria, int adminPoint) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("criteria", criteria);
		paramMap.put("adminPoint", adminPoint);
		return sqlSession.selectList(NAMESPACE + ".selectAdminByPoint", paramMap);
		
	}


	@Override
	public List<ConsultingVO> selectConsultingByPoint(Criteria criteria, int adminPoint) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("criteria", criteria);
		paramMap.put("adminPoint", adminPoint);
		return sqlSession.selectList(NAMESPACE + ".selectConsultingByPoint", paramMap);
		
	}

	@Override
	public void update(ConsultingVO consultingVO) throws Exception {
		sqlSession.update(NAMESPACE + ".update", consultingVO);
	}
	

	@Override
	public void delete(int consultingNo) throws Exception {
		sqlSession.delete(NAMESPACE + ".delete", consultingNo);
	}


	@Override
	public int countAll() throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".countAll");
	}


	@Override
	public ConsultingVO read(int consultingNo) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".read", consultingNo);
		
	}


	
	
	
}
