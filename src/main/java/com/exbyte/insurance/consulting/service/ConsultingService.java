package com.exbyte.insurance.consulting.service;

import java.util.List;

import com.exbyte.insurance.consulting.domain.ConsultingVO;

public interface ConsultingService {

	void create(ConsultingVO consultingVO) throws Exception;
	ConsultingVO read(int consultingNo) throws Exception;
	List<ConsultingVO> selectAll() throws Exception;
	void updateCall(int consultingNo) throws Exception;
	void updateEnd(int consultingNo) throws Exception;
	void delete(int consultingNo) throws Exception;
	int countAll() throws Exception;
	
}
