package paging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exbyte.insurance.commons.paging.Criteria;
import com.exbyte.insurance.commons.paging.PageMaker;

import persisenceTest.ConsultingDAOTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class pagingTest {

	private final static Logger logger = LoggerFactory.getLogger(ConsultingDAOTest.class);
	
	PageMaker pageMaker;
	
	@Test
	public void pageMakerTest() {
		Criteria criteria = new Criteria();
		criteria.setPage(3);
		criteria.setPerPageNum(10);
		
		pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalPageNum(100);
		pageMaker.makeSearch(3);
		
		logger.info(pageMaker.toString());
		
	}
	
	
	
}
