package admin.persistence;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.persistence.AdminDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminSelectTest {
	
	final String TEST_STRING = "junitTest";
	final String TEST_UPDATE_STRING = "junitUpdateTest";
	int TEST_VALUE = 1;
	
	AdminVO admin;
	Logger logger = LoggerFactory.getLogger(AdminSelectTest.class);
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	
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
		admin = setAdminVO(TEST_STRING);
		
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
	public void testSelectAdmin_NullData_ExpectException() throws Exception {
		thrown.expect(InsufficientAuthenticationException.class);
		thrown.expectMessage("None List");
		
		List<AdminVO> list = adminDAO.selectAdmin(admin);
	}
	
}
