package persisenceTest;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.LoginDTO;
import com.exbyte.insurance.admin.persistence.AdminDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminDAOTest {
	
	private final Logger logger = LoggerFactory.getLogger(AdminDAOTest.class);
	
	@Inject
	AdminDAO adminDAO;

	@Test
	public void loginTest() throws Exception{
		
	}
	
	@Test
	public void createTest() throws Exception{
		
		AdminVO admin = new AdminVO();
		admin.setAdminId("testingId");
		admin.setAdminPw("testingPw");
		admin.setAdminName("테스팅");
		admin.setAdminEmail("tesing@naver.com");
		admin.setAdminPosition("책임자");
		admin.setAdminBranch("산업기술대학교");
		adminDAO.create(admin);
		logger.info("Create Success");
		
		
	}
	
	@Test
	public void readTest() throws Exception{
		String adminId = "test_id";
		String adminPw = "test_pw";
		logger.info(adminDAO.read(adminId).toString());
		
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setAdminId(adminId);
		loginDTO.setAdminPw(adminPw);
		logger.info(adminDAO.login(loginDTO).toString());
		
	}
	
	@Test
	public void updateTest() throws Exception{
		AdminVO admin = new AdminVO();
		admin.setAdminPw("testingPwPw");
		admin.setAdminName("테스팅이름");
		admin.setAdminEmail("tesingModify@naver.com");
		admin.setAdminPosition("책임자");
		admin.setAdminBranch("산업기술대학교");
		adminDAO.update(admin);
		logger.info("Update Success");
	}
	
	@Test
	public void deleteTest() throws Exception{
		String adminId = "testingId";
		adminDAO.delete(adminId);
	}

}
