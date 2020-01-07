package com.exbyte.insurance.consulting.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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
	public List<ConsultingVO> selectAll() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".selectAll");
	}

	@Override
	public void updateCall(int consultingNo) throws Exception {
		sqlSession.update(NAMESPACE + ".updateCall", consultingNo);
	}

	@Override
	public void updateEnd(int consultingNo) throws Exception {
		sqlSession.update(NAMESPACE + ".updateEnd", consultingNo);
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
