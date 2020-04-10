package com.exbyte.insurance.drowsy.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.exbyte.insurance.drowsy.domain.PersonalVO;

@Repository
public class DrowsyDAO {
	
	private static SqlSession sqlSession;
	private final String NAMESPACE = "com.exbyte.insuruance.drowsy.DrowsyMapper";
	
	@Inject
	DrowsyDAO(SqlSession sqlSession){
		this.sqlSession = sqlSession;
	}
	
	public void create(PersonalVO personalVO) {
		sqlSession.insert(NAMESPACE + ".create", personalVO);
	}
	
	public void update(String userId) {
		sqlSession.insert(NAMESPACE + ".update", userId);
	}
	
	public PersonalVO read(String userId) {
		return sqlSession.selectOne(NAMESPACE + ".read", userId);
	}
	
}
