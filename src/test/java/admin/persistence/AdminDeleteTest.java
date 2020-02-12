package admin.persistence;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.PointDTO;
import com.exbyte.insurance.admin.persistence.AdminDAO;
import com.exbyte.insurance.point.domain.PointVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminDeleteTest {
	
	final String TEST_STRING = "junitTest";
	final String TEST_UPDATE_STRING = "junitUpdateTest";
	int TEST_VALUE = 1;
	
	Logger logger = LoggerFactory.getLogger(AdminSelectTest.class);
	
	@Inject
	AdminDAO adminDAO;

	public AdminVO setAdminVO(String TEST_STRING) {
		AdminVO admin = new AdminVO();
		admin.setAdminId(TEST_STRING);
		admin.setAdminPw(TEST_STRING);
		admin.setAdminName(TEST_STRING);
		admin.setAdminEmail(TEST_STRING);
		admin.setAdminPosition("사원");
		admin.setAdminPoint(TEST_VALUE);
		admin.setSessionKey(TEST_STRING);
		admin.setAdminAuthKey(TEST_STRING);
		
		return admin;
	}
	
	@Before
	public void init() throws Exception {
		AdminVO existAdmin = adminDAO.read(TEST_STRING);
		
		if(existAdmin != null){
			adminDAO.delete(existAdmin);
		}
	}
	

	@Test
	public void testSelectAdmin() throws Exception{
		AdminVO admin = setAdminVO(TEST_STRING);
	
		List<AdminVO> list = adminDAO.selectAdmin(admin);
		
		logger.info(list.toString());
		assertNotNull(list);
		
		adminDAO.delete(admin);
	}
	
	@Test
	public void selectPointAdminTest() throws Exception{
		List<PointVO> list = adminDAO.selectAllPoint();
		PointDTO pointDTO = null;
		
		logger.info(list.toString());
		for(PointVO pointVO : list) {
			try {
			AdminVO adminVO = adminDAO.selectPointAdmin(pointVO);
			pointDTO = new PointDTO();
			pointDTO.setPointNo(pointVO.getPointNo());
			pointDTO.setPointName(pointVO.getPointName());
			pointDTO.setPointAdmin(adminVO.getAdminId());
			}catch (NullPointerException e) {
				e.printStackTrace();
				pointDTO.setPointAdmin("NULL");
			}
			logger.info(pointDTO.toString());
		}
		
	}
	
}