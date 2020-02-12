package api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.exbyte.insurance.api.service.ApiService;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class apiTest {
	
	RestTemplate restTemplate;
	
	Logger logger = LoggerFactory.getLogger(apiTest.class);
	
	@Test
	public void testApiTemplate() throws Exception {
		ApiService apiService = new ApiService();
		
		Object result = apiService.getItemsForOpenApi("50","check");
		logger.info("data Test " + result.toString());
	}
	
}
