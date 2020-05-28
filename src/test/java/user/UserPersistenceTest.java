package user;

import com.exbyte.insurance.api.persistence.LogDAO;
import com.exbyte.insurance.user.dao.UserDAO;
import com.exbyte.insurance.user.domain.UserVO;
import com.exbyte.insurance.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserPersistenceTest {

    @Inject
    UserDAO userDAO;

    @Inject
    UserService userService;


    @Test
    public void createUser() throws Exception {
        UserVO userVO = new UserVO();
        userVO.setUserId("zihoyuno1");
        userVO.setUserPassword("123123");
        userVO.setUserEmail("zihoyuno1@naver.com");
        userVO.setUserName("최지호");
        String hashedPassword = BCrypt.hashpw(userVO.getUserPassword(), BCrypt.gensalt());
        System.out.println(hashedPassword);

        userVO.setUserPassword(hashedPassword);

        userDAO.create(userVO);
        userDAO.delete(userVO);
    }

    @Test
    public void userNameingRuleTest(){
        UserVO userVO = new UserVO();
        userVO.setUserId("zihoyuno1");
        userVO.setUserPassword("123!");
        userVO.setUserEmail("zihoyuno1@naver.com");
        userVO.setUserName("최지호");

        userService.isCorrectNamingRule(userVO);
    }

}
