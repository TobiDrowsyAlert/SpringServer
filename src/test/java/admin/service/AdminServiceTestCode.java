package admin.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.persistence.AdminDAO;
import com.exbyte.insurance.admin.service.AdminService;

import commons.MockTest;

public class AdminServiceTestCode extends MockTest{

	@Mock
	private AdminDAO adminDAO;
	
	@Autowired
	@InjectMocks
	private AdminService adminService;
	

	@Before
	public void setUp() throws Exception{
		
	}
	
	@Test
	public void	testListAll() throws Exception {
		AdminVO adminVO = adminDAO.read("admin");
		when(adminDAO.read("admin")).thenReturn(new AdminVO());
		assertTrue(adminService.read("admin") == adminVO);
		
		
	}
	
}
