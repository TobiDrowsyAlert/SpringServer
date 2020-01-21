package admin.persistence;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.LoginDTO;
import com.exbyte.insurance.admin.persistence.AdminDAO;
import com.exbyte.insurance.point.domain.PointVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminDAOTest {
	
	private final Logger logger = LoggerFactory.getLogger(AdminDAOTest.class);
	final String TEST_STRING = "junitTest";
	final String TEST_UPDATE_STRING = "junitUpdateTest";
	int TEST_VALUE = 3;
	
	@Inject
	AdminDAO adminDAO;

	public AdminVO setAdminVO(String TEST_STRING) {
		AdminVO admin = new AdminVO();
		admin.setAdminId(TEST_STRING);
		admin.setAdminPw(TEST_STRING);
		admin.setAdminName(TEST_STRING);
		admin.setAdminEmail(TEST_STRING);
		admin.setAdminPosition(TEST_STRING);
		admin.setAdminPoint(TEST_VALUE);
		admin.setSessionKey(TEST_STRING);
		admin.setAdminAuthKey(TEST_STRING);
		
		return admin;
	}
	
	public void checkAdminVO(AdminVO adminGet, AdminVO admin) {
		assertThat(adminGet.getAdminId(), is(admin.getAdminId()));
		assertThat(adminGet.getAdminPw(), is(admin.getAdminPw()));
		assertThat(adminGet.getAdminName(), is(admin.getAdminName()));
		assertThat(adminGet.getAdminEmail(), is(admin.getAdminEmail()));
		assertThat(adminGet.getAdminPosition(), is(admin.getAdminPosition()));
		assertThat(adminGet.getAdminPoint(), is(admin.getAdminPoint()));
		assertThat(adminGet.getSessionKey(), is(admin.getSessionKey()));
		assertThat(adminGet.getAdminAuthKey(), is(admin.getAdminAuthKey()));
	}
	
	@Test
	public void test01CreateAndRead() throws Exception{

		assertThat(adminDAO.countId(TEST_STRING), is(0));
		
		AdminVO admin = setAdminVO(TEST_STRING);
		adminDAO.create(admin);
		
		AdminVO adminGet = adminDAO.read(TEST_STRING);
		checkAdminVO(adminGet,admin);
		
		assertThat(admin.getAdminPosition(), is(adminDAO.checkPosition(TEST_STRING)));
		assertThat(admin.getSessionKey(), is(adminDAO.checkSession(TEST_STRING)));
		
		assertThat(adminDAO.countSession(TEST_STRING), is(1));
		assertThat(adminDAO.checkAuthKey(TEST_STRING), is(TEST_STRING));
		
	}
	
	@Test
	public void test02Login() throws Exception {
		AdminVO admin = setAdminVO(TEST_STRING);
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setAdminId(TEST_STRING);
		loginDTO.setAdminPw(TEST_STRING);
		
		AdminVO adminLoginGET = adminDAO.login(loginDTO);
		checkAdminVO(adminLoginGET, admin);
	}

	@Test
	public void test02CheckPosition() throws Exception {
		AdminVO admin = adminDAO.read(TEST_STRING);
		adminDAO.checkPosition(TEST_STRING);
		assertThat(TEST_STRING, is(admin.getAdminPosition()));
	}

	@Test
	public void test03CountTest() throws Exception {
		AdminVO admin = setAdminVO(TEST_STRING);

		assertThat(adminDAO.count(admin, "adminEmail"), is(1));
		assertThat(adminDAO.countEmail(TEST_STRING), is(1));
		assertThat(adminDAO.countId(TEST_STRING), is(1));
		assertThat(adminDAO.countSession(TEST_STRING), is(1));
		
	}

	@Test
	public void test04KeepSessionTest() throws Exception {
		AdminVO admin = setAdminVO(TEST_STRING);
		admin.setSessionKey(TEST_UPDATE_STRING);
		adminDAO.keepSession(admin.getAdminId(), TEST_UPDATE_STRING);
		assertThat(adminDAO.read(TEST_STRING).getSessionKey(), is(TEST_UPDATE_STRING));
	}
	
	@Test
	public void test05Select() throws Exception{
		
		int i = 0;
		List<PointVO> pointList = adminDAO.selectAllPoint();
		assertEquals(true, pointList != null);
		
		for(PointVO point : pointList) {
			assertThat(++i, is(point.getPointNo()));
		}
		
		List<AdminVO> adminList = adminDAO.selectAllAdmin();
		assertEquals(true, adminList != null);
		
		
	}
	
	@Test
	public void test06SelectSingle() throws Exception{
		AdminVO admin = adminDAO.selectAdminByEmail(TEST_STRING);
		assertThat(admin.getAdminEmail(), is(TEST_STRING));
	}
	
	@Test
	public void test07UpdateSingle() throws Exception{
		AdminVO admin = setAdminVO(TEST_STRING);
		admin.setAdminAuthKey(TEST_UPDATE_STRING);
		admin.setAdminPw(TEST_UPDATE_STRING);
		adminDAO.updateAuthKey(admin);
		adminDAO.updatePw(admin);
		assertThat(adminDAO.read(TEST_STRING).getAdminAuthKey(), is(TEST_UPDATE_STRING));
		assertThat(adminDAO.read(TEST_STRING).getAdminPw(), is(TEST_UPDATE_STRING));
	}
	
	@Test
	public void test08Update() throws Exception {
		
		AdminVO admin = setAdminVO(TEST_UPDATE_STRING);
		admin.setAdminId(TEST_STRING);
		
		adminDAO.update(admin);
		AdminVO adminGET = adminDAO.read(TEST_STRING);
		
		checkAdminVO(admin, adminGET);
	}
	

	@Test
	public void test09Delete() throws Exception {
		
		assertThat(adminDAO.countId(TEST_STRING), is(1));
		adminDAO.delete(TEST_STRING);
		
		assertEquals(true, adminDAO.read(TEST_STRING) == null);
		
	}
	
	
}
