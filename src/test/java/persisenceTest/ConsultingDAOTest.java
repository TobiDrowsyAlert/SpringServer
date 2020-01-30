package persisenceTest;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.commons.paging.Criteria;
import com.exbyte.insurance.commons.paging.PageMaker;
import com.exbyte.insurance.consulting.domain.ConsultingVO;
import com.exbyte.insurance.consulting.persistence.ConsultingDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ConsultingDAOTest {

	private final int INT_TEST_NO = 50;
	private final String STRING_TEST = "junitTest";
	private final String STRING_UPDATE_TEST = "junitUpdateTest";
	
	private final static Logger logger = LoggerFactory.getLogger(ConsultingDAOTest.class);

	@Inject
	private ConsultingDAO consultingDAO;

	@Before
	public void init() throws Exception {
		if(consultingDAO.read(INT_TEST_NO) != null) {
			consultingDAO.delete(INT_TEST_NO);
		}
	}
	
	public AdminVO setAdmin() {
		AdminVO adminVO = new AdminVO();
		adminVO.setAdminId("testing1");
		adminVO.setAdminPosition("관리자");
		adminVO.setAdminPoint(2);
		
		return adminVO;
	}
	
	public ConsultingVO setConsulting(String str) {
		ConsultingVO consultingVO = new ConsultingVO();
		consultingVO.setConsultingNo(INT_TEST_NO);
		consultingVO.setConsultingName(STRING_TEST);
		consultingVO.setConsultingPhone(STRING_TEST);
		consultingVO.setConsultingJob(STRING_TEST);
		consultingVO.setConsultingBirthday(STRING_TEST);
		consultingVO.setConsultingCallTime(STRING_TEST);
		consultingVO.setConsultingFavoriteType(STRING_TEST);
		consultingVO.setConsultingRegion(STRING_TEST);
		consultingVO.setConsultingRemarks(STRING_TEST);
		
		return consultingVO;
	}
	
	@Test
	public void update_ValidInfo_ShouldPass() throws Exception {
		ConsultingVO consultingVO = setConsulting(STRING_TEST);
		consultingDAO.create(consultingVO);
		
		consultingVO = consultingDAO.read(INT_TEST_NO);
		consultingVO.setConsultingName(STRING_UPDATE_TEST);
		consultingVO.setConsultingFavoriteType(STRING_UPDATE_TEST);
		consultingDAO.update(consultingVO);
		
		logger.warn("ConsultingNo : " + consultingVO.getConsultingNo());
	
		ConsultingVO result = consultingDAO.read(consultingVO.getConsultingNo());
		
		assertEquals(consultingVO.getConsultingName(), result.getConsultingName());
		assertEquals(consultingVO.getConsultingFavoriteType(), result.getConsultingFavoriteType());
		
		consultingDAO.delete(consultingVO.getConsultingNo());
	}
	
	@Test
	public void selectAll() throws Exception {
		AdminVO adminVO = setAdmin();

		PageMaker pageMaker = new PageMaker();
		Criteria criteria = new Criteria();
		criteria.setSearchType("");
		List<ConsultingVO> list = consultingDAO.selectAll(criteria, adminVO);

		for (ConsultingVO consultingVO : list) {
			logger.info(consultingVO.toString());
		}
	}

	@Test
	public void countAll() throws Exception {

		AdminVO adminVO = setAdmin();

		PageMaker pageMaker = new PageMaker();
		Criteria criteria = new Criteria();
		criteria.setSearchType("");
		pageMaker.setCriteria(criteria);

		logger.info("count : " + consultingDAO.countAll(criteria, adminVO));
	}

//
//	@Test
//	public void selectConsultingWithSearch() throws Exception {
//		AdminVO adminVO = setAdmin();
//		List<ConsultingVO> consultingList = new ArrayList<ConsultingVO>();
//		Criteria criteria = new Criteria();
//
//		criteria.setSortType("call");
//		criteria.setSearchType("n");
//		criteria.setKeyword("testing33");
//
//		AdminVO admin = new AdminVO();
//		admin.setAdminPoint(1);
//
//		consultingList = consultingDAO.selectAll(criteria, adminVO);
//
//		PageMaker pageMaker = new PageMaker();
//		pageMaker.setCriteria(criteria);
//		pageMaker.setTotalPageNum(consultingDAO.countAll(criteria, adminVO));
//
//		for (ConsultingVO consultingVO : consultingList) {
//			logger.info(consultingVO.toString());
//		}
//		logger.info(pageMaker.toString());
//	}


	@Test
	public void sampleData() throws Exception {

		for (int i = 0; i < 3; i++) {
			String strDate = "1999-11-12";
			ConsultingVO consultingVO = new ConsultingVO();
			consultingVO.setConsultingName("testing1" + i);
			consultingVO.setConsultingPhone("010-5050-995" + i);
			if (i % 2 == 0)
				consultingVO.setConsultingJob("대학생");
			else
				consultingVO.setConsultingJob("회사원");
			consultingVO.setConsultingBirthday(strDate);
			consultingVO.setConsultingCallTime("07-10시");
			consultingVO.setConsultingFavoriteType("100세 보장 종합보험");
			consultingVO.setConsultingRegion("서울특별시");
			consultingVO.setConsultingRemarks("테스트");

			consultingDAO.create(consultingVO);
		}
		for (int i = 0; i < 50; i++) {
			Random ran = new Random();

			String year = String.valueOf(ran.nextInt(51) + 1950); // 1950 ~ 2000년생
			String month = String.valueOf(ran.nextInt(12) + 1); // 1 ~ 12월
			if (Integer.parseInt(month) < 10) {
				month = "0" + month;
			}
			String day = String.valueOf(ran.nextInt(31) + 1); // 1 ~ 31일
			if (Integer.parseInt(day) < 10) {
				day = "0" + day;
			}
			String strDate = year + "-" + month + "-" + day;
			ConsultingVO consultingVO = new ConsultingVO();
			consultingVO.setConsultingName("testing1" + i);
			consultingVO.setConsultingPhone("010-5052-99" + i);
			if (i % 2 == 0)
				consultingVO.setConsultingJob("대학생");
			else
				consultingVO.setConsultingJob("회사원");
			consultingVO.setConsultingBirthday(strDate);
			consultingVO.setConsultingCallTime("07-10시");
			consultingVO.setConsultingFavoriteType("100세 보장 종합보험");
			consultingVO.setConsultingRegion("서울특별시");
			consultingVO.setConsultingRemarks("테스트");

			consultingDAO.create(consultingVO);
		}

	}

}
