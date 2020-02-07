package persisenceTest;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exbyte.insurance.consulting.domain.ConsultingVO;
import com.exbyte.insurance.consulting.persistence.ConsultingDAO;
import com.exbyte.insurance.consulting.service.ConsultingServiceOutside;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ConsultingUpdateTest {

	private final int INT_TEST_NO = 50;
	private final String STRING_TEST = "junitTest";
	private final String STRING_ADMIN = "testing";
	private final String STRING_UPDATE_TEST = "junitUpdateTest";
	
	private final static Logger logger = LoggerFactory.getLogger(ConsultingUpdateTest.class);

	@Inject
	private ConsultingDAO consultingDAO;
	
	ConsultingServiceOutside consultingServiceOutside;

	public ConsultingUpdateTest() {
		consultingServiceOutside = new ConsultingServiceOutside(consultingDAO);
	}
	
	@Before
	public void init() throws Exception {
		if(consultingDAO.read(INT_TEST_NO) != null) {
			consultingDAO.delete(INT_TEST_NO);
		}
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
	public void updateTest() throws Exception {
		ConsultingVO consulting = setConsulting(STRING_TEST);
		consultingDAO.create(consulting);
		consulting.setAdminId(STRING_ADMIN);
		consultingDAO.update(consulting);
		consultingServiceOutside.updateAdminConsultingPosition(STRING_ADMIN);
		
		
	}
}
