package com.exbyte.insurance.user.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.exbyte.insurance.user.domain.UserVO;

@Repository
public class UserDAO {

	private static SqlSession sqlSession;
	private static final String NAMESPACE = "com.exbyte.insurance.user.UserMapper";
	
	@Inject
	public UserDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public void create(UserVO userVO) throws Exception {
		sqlSession.insert(NAMESPACE + ".create", userVO);
	}
	
	public UserVO select(UserVO userVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".select", userVO);
	}
}
