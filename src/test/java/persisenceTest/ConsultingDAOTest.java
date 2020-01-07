package persisenceTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public void createTest() throws Exception {
		String strDate = "1999-11-12";
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simpleFormat.parse(strDate);
		
		ConsultingVO consultingVO = new ConsultingVO();
		consultingVO.setConsultingName("김선걸");
		consultingVO.setConsultingEmail("kims@naver.com");
		consultingVO.setConsultingSex("남");
		consultingVO.setConsultingBirthday(date);
		consultingVO.setConsultingKinds("암");
		consultingVO.setConsultingType("종합보험");
		consultingVO.setConsultingRemarks("테스트용도");
		
		consultingDAO.create(consultingVO);
	}
	
	@Test
	public void selectTest() throws Exception {
		List<ConsultingVO> consultingList = new ArrayList<ConsultingVO>();
		
		consultingList = consultingDAO.selectAll();
		
		for(ConsultingVO consultingVO : consultingList) {
			logger.info(consultingVO.toString());
		}
	}
	
	@Test
	public void updateTest() throws Exception {
		int consultingNo = 3;
		consultingDAO.updateCall(consultingNo);
		consultingDAO.updateEnd(consultingNo);
	}
	
}
