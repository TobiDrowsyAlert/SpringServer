package admin.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.persistence.AdminDAO;
import com.exbyte.insurance.admin.service.AdminMailService;
import com.exbyte.insurance.admin.service.AdminService;
import com.exbyte.insurance.admin.service.AdminServiceImpl;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@WebAppConfiguration
public class AdminServiceTest {
	
	Logger logger = LoggerFactory.getLogger(AdminServiceTest.class);
	
	@Mock
	AdminDAO adminDAO;
	
	@Mock
	AdminMailService adminMailService;
	
	@Mock
	AdminService adminService;
	
	@Autowired
	private WebApplicationContext wac;
	
	AdminVO testAdmin;
	
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		adminService = new AdminServiceImpl(adminDAO, adminMailService);
		
		testAdmin = new AdminVO();
	}
	
	@Test
	public void regsiterAccountTest() throws Exception {
		
		//setup
		AdminVO adminVO = new AdminVO();
		adminVO.setAdminId("admin");
		
		AdminVO resultAdminVO = new AdminVO();
		resultAdminVO.setAdminId("admin");
		resultAdminVO.setAdminPw("admin");
		
		Mockito.when(adminService.registerAccount(adminVO, "/"))
		.thenReturn(resultAdminVO);
		
		//when
		int value = adminService.countId("admin");
		adminVO = adminService.registerAccount(adminVO, "/");
		
		
		// then
		assertEquals(1, value);
		assertEquals(adminVO, resultAdminVO);
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
