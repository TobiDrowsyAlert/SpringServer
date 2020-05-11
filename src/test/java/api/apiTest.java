package api;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.exbyte.insurance.api.domain.LogVO;
import com.exbyte.insurance.api.domain.ResponseDTO;
import com.exbyte.insurance.api.persistence.LogDAO;
import com.exbyte.insurance.api.service.ApiService;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class apiTest {
	
	RestTemplate restTemplate;
	
	Logger logger = LoggerFactory.getLogger(apiTest.class);
	
	@Inject
	LogDAO logDAO;
	
	@Test
	public void testApiTemplate() throws Exception {
		ApiService apiService = new ApiService();
		
		Object result = apiService.getItemsForOpenApi("50","check");
		logger.info("data Test " + result.toString());
	}
	
	@Test
	public void createLog() throws Exception {
		ResponseDTO dto = new ResponseDTO();
		dto.setBlink(1);
		dto.setPitch(1);
		dto.setRoll(1);
		dto.setSleep_step(1);
		dto.setStatus_code(200);
		dto.setYaw(1);
		dto.setYawn(1);
		LogVO logVO = new LogVO(dto, "admin");
		logDAO.create(logVO);
	}
}
