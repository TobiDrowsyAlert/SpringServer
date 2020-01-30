package admin.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
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
import com.exbyte.insurance.point.domain.PointVO;

import admin.domain.TestPointVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		, "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class AdminListValueTest {

	private static MediaType HTML_CONTENT_TYPE = new MediaType(MediaType.TEXT_HTML.getType(), MediaType.TEXT_HTML.getSubtype());
	private static MediaType JSON_CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	final String STRING_SUCCESS = "success";
	final String STRING_FAIL = "fail";
	final String STRING_AUTH_FAIL = "auth_fail";
	final String STRING_NOT_HASH_PW = "not_hash_pw";
	final String STRING_VAILD_EMAIL = "Y";
	final String STRING_INVAILD_EMAIL = "None";
	final String STRING_LOGIN = "login";

	Logger logger = LoggerFactory.getLogger(AdminListValueTest.class);
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
	
	private MockMvc mockMvc2;
	
	private LoginDTO loginDTO;
	private AdminVO admin;
	private Map<String,Object> params;
	List<PointVO> pointList;
	
	private ModelAndView mv;
	
	public MockHttpSession session;
	public MockHttpServletRequest request;
	
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		adminController = new AdminController(adminService, adminMailService);
		mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
		
		mv = new ModelAndView();
		
		session = new MockHttpSession();
		
		request = new MockHttpServletRequest();
		
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
	
	LoginDTO setLoginDTO() {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setAdminId(TEST_STRING);
		loginDTO.setAdminPw(TEST_STRING);
		return loginDTO;
	}
	
	@Test
	public void loginGetTest_HaveCookie_ShouldPass() throws Exception {
		
		//build
		AdminVO admin = setAdminVO();
		LoginDTO loginDTO = setLoginDTO();
		session.setAttribute("login", STRING_LOGIN);
		Cookie loginCookie = new Cookie("loginCookie", session.getId());
		
		//when
		when(adminService.checkSession(loginCookie.getValue())).thenReturn(loginDTO.getAdminId());
		
		//check
		mockMvc.perform(get("/admin/login", loginDTO)
				.cookie(loginCookie))
		.andExpect(view().name("admin/login"))
		.andExpect(model().attribute("adminId", loginDTO.getAdminId()))
		.andExpect(model().attribute("msg", STRING_SUCCESS));
		
		verify(adminService, times(1)).checkSession(loginCookie.getValue());
	}
	
	@Test
	public void listPointGetTest_HavePoint_ShouldPass() throws Exception {
		//build
		List<PointVO> pointListInDB = new ArrayList<PointVO>();
		
		pointListInDB.add(new TestPointVO(1, "서울"));
		pointListInDB.add(new TestPointVO(2, "강남"));
		pointListInDB.add(new TestPointVO(3, "용인"));
		
		//when
		when(adminService.selectAllPoint()).thenReturn(pointListInDB);
		
		//check
		ResponseEntity<List<PointVO>> json = adminController.listPoint();
		verify(adminService, atLeast(1)).selectAllPoint();
		assertEquals(pointListInDB, json.getBody());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = NullPointerException.class)
	public void listPointGetTest_EmptyPointList_ThrowException() throws Exception {
		//when
		when(adminService.selectAllPoint()).thenThrow(NullPointerException.class);
		
		//check
		ResponseEntity<List<PointVO>> result = adminController.listPoint();
	}
	
	@Test
	public void listAdminGetTest_ValidPoint_ShouldPass() throws Exception {
		//build
		List<AdminVO> adminList = new ArrayList<AdminVO>();
		AdminVO admin = setAdminVO();
		adminList.add(admin);
		
		//when
		when(adminService.selectAllAdmin()).thenReturn(adminList);
		
		//check
		ResponseEntity<List<AdminVO>> result = adminController.listAdmin("1");

		assertEquals(adminList, result.getBody());
		verify(adminService, atLeast(1)).selectAllAdmin();
	}
	
	@Test(expected = NullPointerException.class)
	public void listAdminGetTest_Invalid_ThrowException() throws Exception {
		//when
		when(adminService.selectAllAdmin()).thenThrow(NullPointerException.class);
		
	}
	
	
}
