package com.exbyte.insurance.api.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.exbyte.insurance.api.domain.LogVO;

@Repository
public class MinuteLogDAO {

	private static SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.exbyte.insurance.api.MinuteLogMapper";
	
	@Inject
	public MinuteLogDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public void create(LogVO logVO) throws Exception {
		sqlSession.insert(NAMESPACE + ".create", logVO);
	}
	
	public LogVO read(int logNo) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".read", logNo);
	}
	
	public List<LogVO> selectList() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".select");
	}
	
	public void delete(int logNo) throws Exception {
		sqlSession.delete(NAMESPACE + ".delete", logNo);
	}
}
