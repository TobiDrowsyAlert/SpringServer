package admin.persistence;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.LoginDTO;
import com.exbyte.insurance.admin.persistence.AdminDAO;
import com.exbyte.insurance.point.domain.PointVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminDAOTest {
	
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
	
	@Before
	public void init() throws Exception {
		AdminVO existAdmin = adminDAO.read(TEST_STRING);
		
		if(existAdmin != null){
			adminDAO.delete(existAdmin.getAdminId());
		}
	}
	
	public AdminVO initAdminVO() throws Exception {
		AdminVO admin = setAdminVO(TEST_STRING);
		adminDAO.create(admin);
		return admin;
	}
	
	
	@Test
	public void testLogin() throws Exception {
		initAdminVO();
		AdminVO admin = adminDAO.read(TEST_STRING);
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setAdminId(TEST_STRING);
		loginDTO.setAdminPw(TEST_STRING);
		
		AdminVO result = adminDAO.login(loginDTO);
		assertEquals(admin.getAdminPw(), result.getAdminPw());
		
		adminDAO.delete(admin.getAdminId());
	}

	@Test
	public void testCheckPosition() throws Exception {
		AdminVO admin = initAdminVO();
		
		adminDAO.checkPosition(TEST_STRING);
		assertThat(TEST_STRING, is(admin.getAdminPosition()));
		
		adminDAO.delete(admin.getAdminId());
	}

	@Test
	public void testCountTest() throws Exception {
		AdminVO admin = initAdminVO();

		assertThat(adminDAO.count(admin, "adminEmail"), is(1));
		assertThat(adminDAO.countEmail(TEST_STRING), is(1));
		assertThat(adminDAO.countId(TEST_STRING), is(1));
		assertThat(adminDAO.countSession(TEST_STRING), is(1));
		
		adminDAO.delete(admin.getAdminId());
	}

	@Test
	public void testKeepSessionTest() throws Exception {
		AdminVO admin = initAdminVO();
		
		admin.setSessionKey(TEST_UPDATE_STRING);
		adminDAO.keepSession(admin.getAdminId(), TEST_UPDATE_STRING);
		assertThat(adminDAO.read(TEST_STRING).getSessionKey(), is(TEST_UPDATE_STRING));
		
		adminDAO.delete(admin.getAdminId());
	}
	
	@Test
	public void testSelect() throws Exception{
		
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
	public void testSelectSingle() throws Exception{
		initAdminVO();
		AdminVO result = adminDAO.selectAdminByEmail(TEST_STRING);
		assertThat(result.getAdminEmail(), is(TEST_STRING));
		
		adminDAO.delete(result.getAdminId());
	}
	
	@Test
	public void testUpdate() throws Exception {
		
		AdminVO admin = setAdminVO(TEST_UPDATE_STRING);
		admin.setAdminId(TEST_STRING);
		adminDAO.create(admin);
		
		
		adminDAO.update(admin);
		AdminVO result = adminDAO.read(TEST_STRING);

		assertEquals(admin.getAdminEmail(), result.getAdminEmail());
		
		adminDAO.delete(admin.getAdminId());
	}
	

	@Test
	public void testSelectAdminList() throws Exception{
		AdminVO admin = initAdminVO();
		int value = admin.getAdminPoint();
		
		List<AdminVO> list = adminDAO.selectAdminList(Integer.toString(value));
		assertNotNull(list);
		
		adminDAO.delete(admin.getAdminId());
	}
	
}
