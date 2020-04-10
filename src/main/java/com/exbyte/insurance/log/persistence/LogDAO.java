package com.exbyte.insurance.log.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.exbyte.insurance.drowsy.domain.PersonalVO;
import com.exbyte.insurance.log.domain.LogVO;

@Repository
public class LogDAO {

	private static SqlSession sqlSession;
	private final String NAMESPACE = "com.exbyte.insuruance.drowsy.LogMapper";
	
	@Inject
	LogDAO(SqlSession sqlSession){
		this.sqlSession = sqlSession;
	}
	
	public void create(LogVO logVO) {
		sqlSession.insert(NAMESPACE + ".create", logVO);
	}
	
	public void update(LogVO logVO) {
		sqlSession.insert(NAMESPACE + ".update", logVO);
	}
	
	public PersonalVO read(String userId) {
		return sqlSession.selectOne(NAMESPACE + ".read", userId);
	}
	
	
}
