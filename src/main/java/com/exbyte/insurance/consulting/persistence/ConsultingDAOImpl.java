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
	public List<ConsultingVO> selectAll(Criteria criteria, AdminVO adminVO) throws Exception {
		Map<Object, Object> paramMap = new HashMap<>();
		paramMap.put("criteria", criteria);
		paramMap.put("adminVO", adminVO);
		return sqlSession.selectList(NAMESPACE + ".selectAll", paramMap);
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
	public int countAll(Criteria criteria, AdminVO adminVO) throws Exception {
		Map<Object, Object> paramMap = new HashMap<>();
		paramMap.put("criteria", criteria);
		paramMap.put("adminVO", adminVO);
		
		return sqlSession.selectOne(NAMESPACE + ".countAll", paramMap);
	}


	@Override
	public ConsultingVO read(int consultingNo) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".read", consultingNo);
		
	}


	
	
	
}
