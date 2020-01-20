package persisenceTest;

import java.util.List;

import javax.inject.Inject;

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

	private final static Logger logger = LoggerFactory.getLogger(ConsultingDAOTest.class);

	@Inject
	private ConsultingDAO consultingDAO;

	@Test
	public void selectAll() throws Exception {
		AdminVO adminVO = new AdminVO();
		adminVO.setAdminId("gingwan");
		adminVO.setAdminPosition("직원");
		adminVO.setAdminPoint(2);
		
		PageMaker pageMaker = new PageMaker();
		Criteria criteria = new Criteria();
		criteria.setSearchType("");
		List<ConsultingVO> list = consultingDAO.selectAll(criteria, adminVO);
		
		for(ConsultingVO consultingVO : list) {
			logger.info(consultingVO.toString());
		}
	}
	
	@Test
	public void countAll() throws Exception {
		
		AdminVO adminVO = new AdminVO();
		adminVO.setAdminId("gingwan");
		adminVO.setAdminPosition("직원");
		adminVO.setAdminPoint(2);
		
		PageMaker pageMaker = new PageMaker();
		Criteria criteria = new Criteria();
		criteria.setSearchType("");
		pageMaker.setCriteria(criteria);
		
		logger.info("count : " + consultingDAO.countAll(criteria, adminVO));		
	}
	
	@Test
	public void createTest() throws Exception {
//
//		for (int i = 0; i < 3; i++) {
//			String strDate = "1999-11-12";
//			SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = simpleFormat.parse(strDate);
//			ConsultingVO consultingVO = new ConsultingVO();
//			consultingVO.setConsultingName("testing1" + i);
//			consultingVO.setConsultingEmail("testing1" + i + "@naver.com");
//			if(i%2 == 0)
//				consultingVO.setConsultingSex("남");
//			else
//				consultingVO.setConsultingSex("여");
//			consultingVO.setConsultingBirthday(date);
//			consultingVO.setConsultingKinds("종합보험");
//			consultingVO.setConsultingType("푸른상품");
//			consultingVO.setConsultingRemarks("테스트");
//			consultingVO.setConsultingFavoriteType("100세 보장 종합보험");
//			consultingVO.setConsultingRegion("서울특별시");
//
//			consultingDAO.create(consultingVO);
//		}
//		for (int i = 0; i < 3; i++) {
//			String strDate = "1976-01-03";
//			SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = simpleFormat.parse(strDate);
//			ConsultingVO consultingVO = new ConsultingVO();
//			consultingVO.setConsultingName("testing2" + i);
//			consultingVO.setConsultingEmail("testing2" + i + "@naver.com");
//			if(i%2 == 0)
//				consultingVO.setConsultingSex("남");
//			else
//				consultingVO.setConsultingSex("여");
//			consultingVO.setConsultingBirthday(date);
//			consultingVO.setConsultingKinds("암보험");
//			consultingVO.setConsultingType("실버상품");
//			consultingVO.setConsultingFavoriteType("정년 보장 보험");
//			consultingVO.setConsultingRegion("대전광역시");
//			consultingVO.setConsultingRemarks("테스트");
//
//			consultingDAO.create(consultingVO);
//		}
//		for (int i = 0; i < 3; i++) {
//			String strDate = "1986-05-21";
//			SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = simpleFormat.parse(strDate);
//			ConsultingVO consultingVO = new ConsultingVO();
//			consultingVO.setConsultingName("testing3" + i);
//			consultingVO.setConsultingEmail("testing3" + i + "@naver.com");
//			if(i%2 == 0)
//				consultingVO.setConsultingSex("남");
//			else
//				consultingVO.setConsultingSex("여");
//			consultingVO.setConsultingBirthday(date);
//			consultingVO.setConsultingKinds("교통보험");
//			consultingVO.setConsultingType("연상상품");
//			consultingVO.setConsultingFavoriteType("가성비 패키지");
//			consultingVO.setConsultingRegion("경기도 용인시");
//			consultingVO.setConsultingRemarks("테스트용도");
//
//			consultingDAO.create(consultingVO);
//		}
//		for (int i = 0; i < 100; i++) {
//			Random ran = new Random();
//			StringBuffer sb = new StringBuffer();
//			int size = 10;
//			int num = 0;
//			
//			String year = String.valueOf(ran.nextInt(51) + 1950); // 1950 ~ 2000년생
//			String month = String.valueOf(ran.nextInt(12) + 1); // 1 ~ 12월
//			if(Integer.parseInt(month) < 10) {
//				month = "0" + month;
//			}
//			String day = String.valueOf(ran.nextInt(31) + 1); // 1 ~ 31일
//			if(Integer.parseInt(day) < 10) {
//				day = "0" + day;
//			}
//			String strDate = year + "-" + month + "-" + day;
//			SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = simpleFormat.parse(strDate);
//			ConsultingVO consultingVO = new ConsultingVO();
//			consultingVO.setConsultingName("testing4" + i);
//			consultingVO.setConsultingEmail("testing4" + i + "@naver.com");
//			if(i%2 == 0)
//				consultingVO.setConsultingSex("남");
//			else
//				consultingVO.setConsultingSex("여");
//			consultingVO.setConsultingBirthday(date);
//			int selType = ran.nextInt(3);
//			if(selType == 0) {
//				consultingVO.setConsultingKinds("교통보험");
//				consultingVO.setConsultingType("연상상품");
//			}
//			else if(selType == 1) {
//				consultingVO.setConsultingKinds("암보험");
//				consultingVO.setConsultingType("실버상품");
//			}else {
//				consultingVO.setConsultingKinds("종합보험");
//				consultingVO.setConsultingType("푸른상품");
//			}
//			int selFavoriteType = ran.nextInt(3);
//			if(selFavoriteType == 0){
//				consultingVO.setConsultingFavoriteType("자녀 안심 패키지");
//				consultingVO.setConsultingRegion("서울특별시");
//			} else if(selFavoriteType == 1) {
//				consultingVO.setConsultingFavoriteType("특수보험");
//				consultingVO.setConsultingRegion("부산광역시");
//			} else {
//				consultingVO.setConsultingFavoriteType("자녀 안심 패키지");
//				consultingVO.setConsultingRegion("인천광역시");
//			}
//			consultingVO.setConsultingRemarks("테스트용도");
//			consultingDAO.create(consultingVO);
//		}
//		
//		

	}

	/*
	 * @Test public void selectConsultingWithSearch() throws Exception {
	 * List<ConsultingVO> consultingList = new ArrayList<ConsultingVO>(); Criteria
	 * criteria = new Criteria();
	 * 
	 * criteria.setSortType("call"); criteria.setSearchType("n");
	 * criteria.setKeyword("testing33");
	 * 
	 * 
	 * AdminVO admin = new AdminVO(); admin.setAdminPoint(1);
	 * 
	 * consultingList = consultingDAO.selectAll(criteria);
	 * 
	 * PageMaker pageMaker = new PageMaker(); pageMaker.setCriteria(criteria);
	 * pageMaker.setTotalPageNum(consultingDAO.countAll());
	 * 
	 * for (ConsultingVO consultingVO : consultingList) {
	 * logger.info(consultingVO.toString()); } logger.info(pageMaker.toString()); }
	 * 
	 * @Test public void selectConsultingByPointTest() throws Exception {
	 * List<ConsultingVO> consultingList = new ArrayList<ConsultingVO>(); Criteria
	 * criteria = new Criteria();
	 * 
	 * criteria.setSortType("call");
	 * 
	 * criteria.setSearchType("n"); criteria.setKeyword("testing33");
	 * 
	 * 
	 * AdminVO admin = new AdminVO(); admin.setAdminPoint(1);
	 * 
	 * consultingList = consultingDAO.selectConsultingByPoint(criteria,
	 * admin.getAdminPoint());
	 * 
	 * PageMaker pageMaker = new PageMaker(); pageMaker.setCriteria(criteria);
	 * pageMaker.setTotalPageNum(consultingDAO.countAll());
	 * 
	 * for (ConsultingVO consultingVO : consultingList) {
	 * logger.info(consultingVO.toString()); } logger.info(pageMaker.toString()); }
	 * 
	 * @Test public void selectTest() throws Exception { List<ConsultingVO>
	 * consultingList = new ArrayList<ConsultingVO>(); Criteria criteria = new
	 * Criteria();
	 * 
	 * criteria.setSortType("call"); criteria.setSearchType("n");
	 * criteria.setKeyword("testing33");
	 * 
	 * consultingList = consultingDAO.selectAll(criteria);
	 * 
	 * PageMaker pageMaker = new PageMaker(); pageMaker.setCriteria(criteria);
	 * pageMaker.setTotalPageNum(consultingDAO.countAll());
	 * 
	 * for (ConsultingVO consultingVO : consultingList) {
	 * logger.info(consultingVO.toString()); } logger.info(pageMaker.toString()); }
	 * 
	 * @Test public void selectConsultingByIdTest() throws Exception {
	 * 
	 * AdminVO adminVO = new AdminVO(); adminVO.setAdminId("jiho");
	 * 
	 * 
	 * Criteria criteria = new Criteria();
	 * 
	 * List<ConsultingVO> list = consultingDAO.selectConsultingById(criteria,
	 * adminVO.getAdminId());
	 * 
	 * for (ConsultingVO consulting : list) { logger.info(consulting.toString()); }
	 * }
	 */
}
