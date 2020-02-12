package admin.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.persistence.AdminDAO;
import com.exbyte.insurance.admin.service.AdminMailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@WebAppConfiguration
public class AdminMailServiceTest {
	
	final String TEST_STRING = "junitTest";
	final int TEST_POINT = 1;
	final String TEST_CONTEXT_PATH = "/test";
	final String TEST_VAILD_EMAIL = "Y";
	
	@Mock
	private AdminDAO adminDAO;
	
	@Autowired
	private JavaMailSender mailSender;
	
	AdminMailService adminMailService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		adminMailService = new AdminMailService(adminDAO, mailSender);
	}
	
	public AdminVO setAdminVO(String TEST_STRING, int TEST_POINT) {
		AdminVO admin = new AdminVO();
		admin.setAdminId(TEST_STRING);
		admin.setAdminPw(TEST_STRING);
		admin.setAdminName(TEST_STRING);
		admin.setAdminEmail("noreply@jigool.cafe24.com");
		admin.setAdminPosition(TEST_STRING);
		admin.setAdminPoint(TEST_POINT);
		admin.setSessionKey(TEST_STRING);
		admin.setAdminAuthKey(TEST_STRING);
		
		return admin;
	}

	@Test
	@Ignore
	public void mailSendTest_RegisterEmail_ShouldPass() throws Exception {
		//build
		AdminVO admin = setAdminVO(TEST_STRING, TEST_POINT);
		
		//operate
		when(adminDAO.read(admin.getAdminId())).thenReturn(admin);
		doNothing().when(adminDAO).updateAuthKey(any());
		
		adminMailService.mailSend(admin, TEST_CONTEXT_PATH);
		
		//check
		verify(adminDAO, times(1)).read(admin.getAdminId());
		verify(adminDAO, times(1)).updateAuthKey(admin);
	}
	
	@Test
	@Ignore
	public void mailSendTest_FindAccount_ShouldPass() throws Exception {
		//build
		AdminVO admin = setAdminVO(TEST_STRING, TEST_POINT);
		admin.setAdminAuthKey(TEST_VAILD_EMAIL);
		
		//operate
		when(adminDAO.read(admin.getAdminId())).thenReturn(admin);
		doNothing().when(adminDAO).updateAuthKey(any());
		
		adminMailService.mailSend(admin, TEST_CONTEXT_PATH);
		
		//check
		verify(adminDAO, times(1)).read(admin.getAdminId());
		verify(adminDAO, times(1)).updateAuthKey(admin);
	}
	
	@Test(expected = NullPointerException.class)
	@Ignore
	public void mailSendTest_Invalid_ThrowException() throws Exception {
		//build
		AdminVO admin = setAdminVO(TEST_STRING, TEST_POINT); 
		admin.setAdminAuthKey(TEST_VAILD_EMAIL);
		
		//operate
		when(adminDAO.read(admin.getAdminId())).thenReturn(null);
		doNothing().when(adminDAO).updateAuthKey(any());
		
		adminMailService.mailSend(admin, TEST_CONTEXT_PATH);
		
	}

	
	
}
