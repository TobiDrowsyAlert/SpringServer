package admin.persistence;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.LoginDTO;
import com.exbyte.insurance.admin.persistence.AdminDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DuplicateCountTest {
	
	AdminVO admin;
	LoginDTO loginDTO;
	
	final String TEST_STRING = "junitTest";
	final String TEST_UPDATE_STRING = "junitUpdateTest";
	int TEST_VALUE = 1;
	
	@Inject
	AdminDAO adminDAO;

	@Before
	public void init() throws Exception {

		admin = AdminVO.builder()
				.adminId(TEST_STRING)
				.adminPw(TEST_STRING)
				.adminName(TEST_STRING)
				.adminEmail(TEST_STRING)
				.adminPhone(TEST_STRING)
				.sessionKey(TEST_STRING)
				.adminCallNumber(TEST_STRING)
				.adminPosition("사원")
				.adminPoint(TEST_VALUE)
				.adminAuthKey(TEST_STRING)
				.build();
		
		loginDTO = LoginDTO.builder()
				.adminId(TEST_STRING)
				.adminPw(TEST_STRING)
				.adminPoint(TEST_VALUE)
				.useCookie(false)
				.build();
		
	}
	
	@After
	public void after() throws Exception{
		AdminVO existAdmin = adminDAO.read(TEST_STRING);
		
		if(existAdmin != null){
			adminDAO.delete(existAdmin);
		}
	}
	
	@Test
	public void countEamailSomeAdminGetOne() throws Exception {
		
		adminDAO.create(admin);
		int result = adminDAO.countEmail(TEST_STRING);
		
		assertThat(result , equalTo(1));
		
	}
}
