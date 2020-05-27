package admin.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.LoginDTO;
import com.exbyte.insurance.admin.persistence.AdminDAO;
import com.exbyte.insurance.admin.service.AdminService;
import com.exbyte.insurance.admin.service.AdminServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@WebAppConfiguration
public class AdminServiceTest {
	
	final String TEST_STRING = "junitTest";
	final int TEST_POINT = 1;
	final String TEST_INVALID_EMAIL = "NotAuthEmailKey";
	final String TEST_VALID_EMAIL = "Y";
	
	Logger logger = LoggerFactory.getLogger(AdminServiceTest.class);
	
	@Mock
	AdminDAO adminDAO;
	
	
	AdminService adminService;
	
	@Autowired
	private WebApplicationContext wac;
	
	AdminVO admin;
	
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		adminService = new AdminServiceImpl(adminDAO);
	}
	
	public AdminVO setAdminVO(String TEST_STRING, int TEST_POINT) {
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
	public void loginTest_ValidId_ShouldPass() throws Exception {
		//build
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setAdminId(TEST_STRING);
		loginDTO.setAdminPw(TEST_STRING);
		
		AdminVO actualResult;
		
		AdminVO expectedResult = setAdminVO(TEST_STRING, TEST_POINT);
		expectedResult.setAdminAuthKey(TEST_VALID_EMAIL);
		expectedResult.setAdminPw(BCrypt.hashpw(TEST_STRING, BCrypt.gensalt()));
		
		
		//operate
		when(adminDAO.login(loginDTO)).thenReturn(expectedResult);
		actualResult = adminService.login(loginDTO);
		
		//check
		assertEquals(expectedResult, actualResult);
		verify(adminDAO, times(1)).login(any());
	}
	
	@Test(expected = NullPointerException.class)
	public void loginTest_InValidId_ThrowException() throws Exception{
		//build
		LoginDTO loginDTO = new LoginDTO();

		AdminVO expectedResult = null;
		
		//operate
		when(adminDAO.login(any())).thenReturn(expectedResult);
		adminService.login(loginDTO);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void loginTest_InValidEmail_ThrowException() throws Exception{
		//build
		LoginDTO loginDTO = new LoginDTO();

		AdminVO expectedResult = setAdminVO(TEST_STRING, TEST_POINT);
		expectedResult.setAdminAuthKey(TEST_INVALID_EMAIL);
		
		//operate
		when(adminDAO.login(any())).thenReturn(expectedResult);
		adminService.login(loginDTO);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void loginTest_DatabasePwPlaintext_ThrowException() throws Exception{
		//build
		LoginDTO loginDTO = new LoginDTO();

		AdminVO expectedResult = setAdminVO(TEST_STRING, TEST_POINT);
		
		//operate
		when(adminDAO.login(any())).thenReturn(expectedResult);
		adminService.login(loginDTO);
	}
		
	
	
	@Test
	public void hashAdminPw_ValidPw_ShouldPass() throws Exception {
		//build, 테스트 자료를 만든다.
		Boolean actualResult;
		Boolean expectedResult = true;
		
		//operate, 테스트 자료를 조작한다.
		actualResult = BCrypt.checkpw(TEST_STRING, adminService.hashAdminPw(TEST_STRING));
		
		//check, 조작한 결과가 올바른 지 확인한다.
		assertEquals(expectedResult, actualResult);
	}
	
	@Test(expected = NullPointerException.class)
	public void hashAdminPw_NullPw_ShouldThrowException() {
		//build
		Boolean actualResult;
		Boolean expectedResult = true;
		
		//operate
		actualResult = BCrypt.checkpw(TEST_STRING, null);
		
		//check
		assertEquals(expectedResult, actualResult);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void hashAdminPw_PlainPw_ShouldThrowException() {
		//build
		Boolean actualResult;
		Boolean expectedResult = true;
		
		//operate
		actualResult = BCrypt.checkpw(TEST_STRING, TEST_STRING);
		
		//check
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void verifyTest() {
		List<String> testMock = mock(ArrayList.class);
		testMock.add("1");
		testMock.add("2");
		testMock.add("3");
		
		//add가 최소한 1번 이상 호출되었나 검증
		Mockito.verify(testMock, atLeastOnce()).add(anyString());
		
		//add가 최소한 3번 이상 호출되었나 검증
		Mockito.verify(testMock, atLeast(3)).add(anyString());
		
		//add가 최대한 3번 이하 호출되었는 지 검증
		Mockito.verify(testMock, atMost(3)).add(anyString());
		
		//add()가 3번 호출되었는 지 검증
		Mockito.verify(testMock, times(3)).add(anyString());
		
		//add("1")이 1번 호출되었는 지 검증
		Mockito.verify(testMock, times(1)).add("1");
		
		
	}
	
	@Test
	public void whenThenTest() {
		@SuppressWarnings("unchecked")
		
		Map<String, String> testMock = mock(Map.class);
		
		//testMock.get("name1")호출 시, kyu1이라는 값을 리턴하도록 약속
		//testMock.put("name1","kyu1") 한 것과 같은 의미라고 보면 된다.
		Mockito.when(testMock.get("name1")).thenReturn("kyu1");
		Mockito.when(testMock.get("name2")).thenReturn("kyu2");
		Mockito.when(testMock.get("name3")).thenReturn("kyu3");
		
		assertThat("kyu1", is(testMock.get("name1")));
		assertThat("kyu2", is(testMock.get("name2")));
		assertThat("kyu3", is(testMock.get("name3")));
	}

	@Test(expected = RuntimeException.class)
	public void whenThenthrowTest() {
		Map<String, String> testMock = mock(Map.class);
		
		//get("name4")호출 시 RuntimeException 발생
		Mockito.when(testMock.get("name4")).thenThrow(new RuntimeException());

		
		testMock.get("name4");
		
		
	}
	@Test(expected = IllegalStateException.class)
	public void whenThenthrowIllegalTest() {
		Map<String, String> testMock = mock(Map.class);
		
		Mockito.doThrow(IllegalStateException.class)
		.when(testMock)
		.put(anyString(), anyString());
		
		testMock.put("1","1");
	}
	
	@Test
	public void whenThenAnswerTest() throws Exception{
		
		Mockito.when(adminDAO.selectAdminByEmail("ziho@naver.com")).thenAnswer(new Answer<AdminVO>() {
			@Override
			public AdminVO answer(InvocationOnMock invocation) throws Throwable {
				AdminVO adminVO = new AdminVO();
				adminVO.setAdminId("ziho");
				return adminVO;
			}
		});
		
		AdminVO adminVO = adminDAO.selectAdminByEmail("ziho@naver.com");
		assertThat("ziho", is(adminVO.getAdminId()));
		
	}
	
	
}
