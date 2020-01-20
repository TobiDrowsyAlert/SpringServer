package admin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.exbyte.insurance.admin.controller.AdminController;
import com.exbyte.insurance.admin.service.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		, "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class AdminControllerTest {	

	private static MediaType HTML_CONTENT_TYPE = new MediaType(MediaType.TEXT_HTML.getType(), MediaType.TEXT_HTML.getSubtype());
	private static MediaType JSON_CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


	Logger logger = LoggerFactory.getLogger(AdminControllerTest.class);
	
	@Mock
	AdminService adminService;
	
	@InjectMocks
	private AdminController adminController;
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
	}

	
	@Test
	public void testAdminControllerLogin() throws Exception {
		mockMvc.perform(post("/admin/loginPOST").param("adminId", "admin").param("adminPw", "admin"))
		.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void testAdminControllerListPoint() throws Exception {
		
		mockMvc.perform(get("/admin/checkEmail").contentType(JSON_CONTENT_TYPE).param("adminEmail", "1"));
		
	}
	
	@Test
	public void testAdminControllerEmailSend() throws Exception {
		mockMvc.perform(get("/admin/emailSend").param("adminEmail", "222@naver.com"))
		.andDo(print()).andExpect(status().isOk());
	}
	
	
	
	
}
