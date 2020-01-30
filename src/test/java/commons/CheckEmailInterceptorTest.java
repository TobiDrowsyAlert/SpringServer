package commons;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.service.AdminService;
import com.exbyte.insurance.commons.interception.CheckEmailInterceptor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CheckEmailInterceptorTest {

	@Mock
	AdminService adminService;
	
	CheckEmailInterceptor checkEmailInterceptor;
	
	final String TEST_STRING = "junit";
	final int TEST_POINT = 1;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		checkEmailInterceptor = new CheckEmailInterceptor(adminService);
		
	}
	
	AdminVO setAdminVO() {
		AdminVO admin = new AdminVO();
		admin.setAdminId(TEST_STRING);
		admin.setAdminPw(TEST_STRING);
		admin.setAdminName(TEST_STRING);
		admin.setAdminEmail(TEST_STRING);
		admin.setAdminPosition(TEST_STRING);
		admin.setAdminPoint(TEST_POINT);
		admin.setSessionKey(TEST_STRING);
		admin.setAdminAuthKey(TEST_STRING);
		
		return admin;
	}
	
	@Test
	public void preHandleTest_ValidAccount_ShouldPass() throws Exception {
		AdminVO admin = setAdminVO();
		
		
		
	}
	
	
	
}
