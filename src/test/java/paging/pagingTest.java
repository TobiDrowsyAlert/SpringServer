package paging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exbyte.insurance.commons.paging.Criteria;
import com.exbyte.insurance.commons.paging.PageMaker;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class pagingTest {

	PageMaker pageMaker;
	Criteria criteria;
	
	public pagingTest() {
		criteria = new Criteria();
		criteria.setPage(3);
		criteria.setPerPageNum(10);
		pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalPageNum(200);
	}
	
	@Test
	public void criteriaValueTest() {
		
		Criteria criteria = new Criteria();
		criteria.setPage(-1);
		criteria.setPerPageNum(-5);
		criteria.setPerPageNum(101);
		criteria.getPageStart();
	}
	
	@Test
	public void pageLowerThenMaxPageTest() {
		criteria.setPage(11);
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalPageNum(150);
	}

}
