package admin.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.exbyte.insurance.admin.controller.AdminController;
import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.LoginDTO;
import com.exbyte.insurance.admin.service.AdminMailService;
import com.exbyte.insurance.admin.service.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		, "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class AdminIntegrationTest {	

	private static MediaType HTML_CONTENT_TYPE = new MediaType(MediaType.TEXT_HTML.getType(), MediaType.TEXT_HTML.getSubtype());
	private static MediaType JSON_CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	final String STRING_SUCCESS = "success";
	final String STRING_FAIL = "fail";
	final String STRING_AUTH_FAIL = "auth_fail";
	final String STRING_NOT_HASH_PW = "not_hash_pw";
	final String STRING_VAILD_EMAIL = "Y";
	final String STRING_INVAILD_EMAIL = "None";
	final String STRING_NULL = "null";
	

	Logger logger = LoggerFactory.getLogger(AdminControllerTest.class);
	final String TEST_STRING = "junitTest";
	final int TEST_POINT = 1;
	
	@Mock
	AdminService adminService;
	
	@Mock
	AdminMailService adminMailService;
	
	private AdminController adminController;
	
	// 웹 응용 프로그램 구성을 제공하는 인터페이스
	@Autowired
	private WebApplicationContext wac;

	// 톰캣 작동 없이도 컨트롤러 테스트를 동작가능하게 해준다.
	private MockMvc mockMvc;
	
	private LoginDTO loginDTO;
	private AdminVO admin;
	private Map<String,Object> params;
	
	private ModelAndView mv;
	
	public MockHttpSession session;
	public MockHttpServletRequest request;
	
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		adminController = new AdminController(adminService, adminMailService);
		mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
		
		mv = new ModelAndView();
		
		loginDTO = new LoginDTO();
		loginDTO.setAdminId(TEST_STRING);
		loginDTO.setAdminPw(TEST_STRING);
		
		admin = new AdminVO();
		admin.setAdminId(TEST_STRING);
		admin.setAdminPw(TEST_STRING);
		admin.setAdminName(TEST_STRING);
		admin.setAdminEmail(TEST_STRING);
		admin.setAdminPosition(TEST_STRING);
		admin.setAdminPoint(TEST_POINT);
		admin.setSessionKey(TEST_STRING);
		admin.setAdminAuthKey(TEST_STRING);
		
		session = new MockHttpSession();
		
		request = new MockHttpServletRequest();
		
	}

	@Test
	public void registerGetTest_CheckForward() throws Exception {
		
	}
	
	@Test
	public void emailSendGetTest_CheckForward() throws Exception {

		//check
		mockMvc.perform(get("/admin/email")
				.param("adminEmail", TEST_STRING))
		.andExpect(view().name("admin/email"));
		
	}
	
	@Test
	public void emailSendPostTest_ValidEmail_ShouldPass() throws Exception {
		//build
		String adminEmail = TEST_STRING;
		
		//when
		when(adminService.selectAdminByEmail(adminEmail)).thenReturn(admin);
		doNothing().when(adminMailService).mailSend(any(), anyString());;
		
		//check
		mockMvc.perform(post("/admin/email")
				.param("adminEmail", adminEmail))
		.andExpect(redirectedUrl("/"));
	}

	@Test(expected = NullPointerException.class)
	public void emailSendPostTest_InvalidEmail_ThrowException() throws Exception {
		//build
		String adminEmail = TEST_STRING;
		
		//when
		doThrow(NullPointerException.class).when(adminService).selectAdminByEmail(anyString());
		doNothing().when(adminMailService).mailSend(any(), anyString());;
		
		//check
		mockMvc.perform(post("/admin/email")
				.param("adminEmail", adminEmail))
		.andExpect(redirectedUrl("/"));
	}	
	
	@Test
	public void registerPostTest_ValidInfo_ShouldPass() throws Exception{
		// bulid
		AdminVO expectedResult = admin;
		expectedResult.setAdminPw(BCrypt.hashpw(admin.getAdminPw(), BCrypt.gensalt()));
		
		// when
		when(adminService.registerAccount(admin)).thenReturn(expectedResult);
		doNothing().when(adminMailService).mailSend(any(), anyString());
		
		// check
		mockMvc.perform(post("/admin/registerPOST", admin))
		.andExpect(redirectedUrl("/admin/login"))
		.andExpect(flash().attribute("msg", STRING_SUCCESS));
		
		verify(adminMailService).mailSend(any(), anyString());;
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = NullPointerException.class)
	public void registerPostTest_MissingInfo_ThrowException() throws Exception {

		// when
		when(adminService.registerAccount(any())).thenThrow(NullPointerException.class);
		
		// check
		mockMvc.perform(post("/admin/registerPOST", admin))
		.andExpect(redirectedUrl("/admin/login"))
		.andExpect(flash().attribute("msg", STRING_NULL));
		
	}
	
	@Test
	public void confirmEmailTest_Forward_ShouldPass() throws Exception {
		mockMvc.perform(get("/admin/confirm", admin))
		.andExpect(model().attribute("msg", STRING_SUCCESS))
		.andExpect(view().name("/admin/login"));
		
	}
}
	