package com.exbyte.insurance.consulting.persistence;

import java.util.List;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.commons.paging.Criteria;
import com.exbyte.insurance.consulting.domain.ConsultingVO;

public interface ConsultingDAO {

	void create(ConsultingVO consultingVO) throws Exception;
	ConsultingVO read(int consultingNo) throws Exception;
	List<ConsultingVO> selectAll(Criteria criteria, AdminVO adminVO) throws Exception;
	
	void update(ConsultingVO consultingVO) throws Exception;
	void delete(int consultingNo) throws Exception;
	int countAll(Criteria criteria, AdminVO adminVO) throws Exception;
}
