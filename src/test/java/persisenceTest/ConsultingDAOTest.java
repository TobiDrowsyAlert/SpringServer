package persisenceTest;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exbyte.insurance.consulting.domain.ConsultingVO;
import com.exbyte.insurance.consulting.persistence.ConsultingDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ConsultingDAOTest {

	private final static Logger logger = LoggerFactory.getLogger(ConsultingDAOTest.class);

	@Inject
	private ConsultingDAO consultingDAO;
	
	
	
	@Test
	public void updateTest() throws Exception {
		ConsultingVO consultingVO = consultingDAO.read(15);
		consultingVO.setAdminId("admin");
		consultingDAO.update(consultingVO);
		
	}
	
	/*
	 * @Test public void createTest() throws Exception {
	 * 
	 * for(int i = 0; i < 200; i++) { String strDate = "1999-11-12";
	 * SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd"); Date date
	 * = simpleFormat.parse(strDate); ConsultingVO consultingVO = new
	 * ConsultingVO(); consultingVO.setConsultingName("testing"+i);
	 * consultingVO.setConsultingEmail("testing"+i+"@naver.com");
	 * consultingVO.setConsultingSex("남"); consultingVO.setConsultingBirthday(date);
	 * consultingVO.setConsultingKinds("교통사고");
	 * consultingVO.setConsultingType("종합보험");
	 * consultingVO.setConsultingRemarks("테스트용도");
	 * 
	 * consultingDAO.create(consultingVO); }
	 * 
	 * }
	 */

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
