package com.exbyte.insurance.api.service;

import java.util.List;

import javax.inject.Inject;

import com.exbyte.insurance.user.domain.UserVO;
import org.springframework.stereotype.Service;

import com.exbyte.insurance.api.domain.LogVO;
import com.exbyte.insurance.api.domain.RequestFeedback;
import com.exbyte.insurance.api.persistence.LogDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LogService {
	
	LogDAO logDAO;
	
	@Inject
	public LogService(LogDAO logDAO) {
		this.logDAO = logDAO;
	}
	
	public LogVO create(LogVO logVO) throws Exception {
		return logDAO.create(logVO);
	}
	
	public LogVO read(int logNo) throws Exception {
		return logDAO.read(logNo);
	}
	
	public List<LogDAO> selectList() throws Exception {
		return logDAO.selectList();
	}
	
	public void delete(int logNo) throws Exception {
		logDAO.delete(logNo);
	}
	
	public void updateFeedabck(RequestFeedback requestFeedback) throws Exception{
		logDAO.updateFeedback(requestFeedback);
	}

	public int[] countDrowsy(UserVO userVO) throws Exception{
		int[] drowsyCount = new int[4];
		LogVO logVO =new LogVO();
		logVO.setUserId(userVO.getUserId());
		logVO.setReason("눈 감김");
		drowsyCount[0] = logDAO.countDrowsy(logVO);

		logVO.setReason("눈 깜빡임");
		drowsyCount[1] = logDAO.countDrowsy(logVO);

		logVO.setReason("하품");
		drowsyCount[2] = logDAO.countDrowsy(logVO);

		logVO.setReason("운전자 이탈");
		drowsyCount[3] = logDAO.countDrowsy(logVO);

		return drowsyCount;
	}

	public int[] calculateSuccessRate(UserVO userVO) throws Exception{
		int[] successLog = new int[4];
		int[] totalLog = new int[4];
		int[] successRate = new int[5];
		LogVO logVO = new LogVO();
		logVO.setUserId(userVO.getUserId());
		logVO.setReason("눈 감김");
		successLog[0] = logDAO.countCorrectLogWithReason(logVO);
		totalLog[0] = logDAO.countTotalLogWithReason(logVO);

		logVO.setReason("눈 깜빡임");
		successLog[1] = logDAO.countCorrectLogWithReason(logVO);
		totalLog[1] = logDAO.countTotalLogWithReason(logVO);

		logVO.setReason("하품");
		successLog[2] = logDAO.countCorrectLogWithReason(logVO);
		totalLog[2] = logDAO.countTotalLogWithReason(logVO);

		logVO.setReason("운전자 이탈");
		successLog[3] = logDAO.countCorrectLogWithReason(logVO);
		totalLog[3] = logDAO.countTotalLogWithReason(logVO);

		for (int i = 0; i < successRate.length - 1; i++) {
			double value = (totalLog[i] != 0 ? successLog[i] / (double)totalLog[i] : 0 );
			successRate[i] = (int)Math.round(value*100);
			System.out.println(successRate[i] + "= " +successLog[i] + "/" + totalLog[i]);
		}

		int totalCorrectCount = logDAO.countCorrectLog(logVO);
		int totalCount = logDAO.countTotalLog(logVO);

		double value = (totalCount != 0 ? totalCorrectCount / (double)totalCount : 0 );
		successRate[4] = (int)Math.round(value*100);

		return successRate;
	}
}
