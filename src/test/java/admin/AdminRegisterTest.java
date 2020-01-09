package admin;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.persistence.AdminDAO;
import com.exbyte.insurance.admin.service.AdminMailService;

import persisenceTest.AdminDAOTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminRegisterTest {
	
	private final Logger logger = LoggerFactory.getLogger(AdminDAOTest.class);
	
	@Inject
	AdminDAO adminDAO;
	
	@Inject
	AdminMailService adminMailService;

	
	@Test
	public void emailChecking() throws Exception{
		
		AdminVO adminVO = adminDAO.read("to");
		logger.info(adminVO.toString());
		adminMailService.mailSendWithUserKey(adminVO);
		
	}
	
	
	@Test
	public void idChecking() throws Exception{
		AdminVO adminVO = new AdminVO();
		adminVO.setAdminId("rayor");
		adminVO.setAdminEmail("testing@naver.com");
		
		logger.info("AdminIDNum : "+adminDAO.checkOverId(adminVO.getAdminId()));
		logger.info("AdminIDEmail : "+adminDAO.checkOverEmail(adminVO.getAdminEmail()));
		
		
		
		
	}
	

}
