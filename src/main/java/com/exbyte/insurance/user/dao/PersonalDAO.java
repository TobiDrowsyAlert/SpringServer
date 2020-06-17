package com.exbyte.insurance.user.dao;

import com.exbyte.insurance.user.domain.PersonalVO;
import com.exbyte.insurance.user.domain.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class PersonalDAO {
    private static SqlSession sqlSession;
    private static final String NAMESPACE = "com.exbyte.insurance.user.PersonalMapper";

    @Inject
    public PersonalDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public void create(UserVO userVO) throws Exception {
        sqlSession.insert(NAMESPACE + ".create", userVO);
    }

    public PersonalVO select(UserVO userVO) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".select", userVO);
    }

    public int countLog(UserVO userVO) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".countLog", userVO);
    }

    public int countMinuteLog(UserVO userVO) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".countMinuteLog", userVO);
    }

    public void update(PersonalVO personVO) throws Exception {
        sqlSession.update(NAMESPACE + ".update", personVO);
    }

    public double calculateEar(UserVO userVO) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".calculateEar", userVO);
    }

    public double calculateMar(UserVO userVO) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".calculateMar", userVO);
    }

    public double calculateAvgStage(UserVO userVO) throws Exception{
        return sqlSession.selectOne(NAMESPACE + ".calculateAvgStage", userVO);
    }

    public String calculateFrequencyReason(UserVO userVO) throws Exception{
        return sqlSession.selectOne(NAMESPACE + ".calculateFrequencyReason", userVO);
    }



}
