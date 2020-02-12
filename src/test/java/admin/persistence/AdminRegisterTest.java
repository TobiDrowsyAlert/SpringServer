package admin.persistence;

import javax.inject.Inject;

import org.junit.Before;
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
public class AdminRegisterTest {
	
	final String TEST_STRING = "registerCheck";
	final String TEST_UPDATE_STRING = "junitUpdateTest";
	int TEST_VALUE = 3;
	
	AdminVO admin;
	LoginDTO login;
	
	Logger logger = LoggerFactory.getLogger(AdminRegisterTest.class);
	
	@Inject
	AdminDAO adminDAO;


	public AdminVO setAdminVO(String TEST_STRING) {
//		adminName=김진관12, adminEmail=zihoyuno1@daum.net, adminPosition=관리자, sessionKey=none, 
//		adminJoinDate=null, adminLoginDate=null, adminPoint=0, adminAuthKey=none
		
		AdminVO admin = new AdminVO();
		admin.setAdminId("gingwan12");
		admin.setAdminPw("1234512345");
		admin.setAdminName("김진관12");
		admin.setAdminEmail("zihoyuno1@daum.net");
		admin.setAdminPosition("사원");
		admin.setAdminPoint(1);
		admin.setSessionKey("none");
		admin.setAdminAuthKey("none");
		
		return admin;
	}
	
	@Before
	public void init() throws Exception {
		AdminVO existAdmin = adminDAO.read(TEST_STRING);

		admin = AdminVO.builder()
				.adminId(TEST_STRING)
				.adminPw(TEST_STRING)
				.adminName(TEST_STRING)
				.adminEmail(TEST_STRING)
				.adminPhone(TEST_STRING)
				.sessionKey(TEST_STRING)
				.adminCallNumber(TEST_STRING)
				.adminPosition("사원")
				.adminPoint(1)
				.adminAuthKey(TEST_STRING)
				.build();
		
		login = LoginDTO.builder()
				.adminId(TEST_STRING)
				.adminPw(TEST_STRING)
				.adminPoint(1)
				.useCookie(false)
				.build();
		
		
		if(existAdmin != null){
			adminDAO.delete(existAdmin);
		}
	}
	
	
	@Test
	public void registerTest() throws Exception {
		adminDAO.create(admin);
	}
	
	@Test
	public void selectRootTest() throws Exception {
		AdminVO adminVO = setAdminVO(TEST_STRING);
		logger.info("RootCount : " + adminDAO.countPosition(adminVO));
	}
}