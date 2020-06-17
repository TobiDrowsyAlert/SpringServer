package com.exbyte.insurance.api.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.exbyte.insurance.api.domain.LogVO;
import com.exbyte.insurance.api.domain.RequestFeedback;

@Repository
public class LogDAO {

	private static SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.exbyte.insurance.api.LogMapper";
	
	@Inject
	public LogDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public LogVO create(LogVO logVO) throws Exception {
		sqlSession.insert(NAMESPACE + ".create", logVO);
		return logVO;
	}
	
	public LogVO read(int logNo) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".read", logNo);
	}
	
	public List<LogDAO> selectList() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".select");
	}
	
	public void delete(int logNo) throws Exception {
		sqlSession.delete(NAMESPACE + ".delete", logNo); 
	}
	
	public void updateFeedback(RequestFeedback requestFeedback) throws Exception {
		sqlSession.update(NAMESPACE + ".updateFeedback", requestFeedback );
	}

	public int countDrowsy(LogVO logVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".countDrowsy", logVO);
	}

	public int countCorrectLogWithReason(LogVO logVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".countCorrectLogWithReason", logVO);
	}

	public int countTotalLogWithReason(LogVO logVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".countTotalLogWithReason", logVO);
	}

	public int countCorrectLog(LogVO logVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".countCorrectLog", logVO);
	}

	public int countTotalLog(LogVO logVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".countTotalLog", logVO);
	}
}
