import com.xwq.dao.UserDao;
import com.xwq.entity.User;
import com.xwq.service.UserService;
import com.xwq.service.impl.UserServiceImpl;
import javafx.application.Application;
import javassist.ClassPath;
import org.aspectj.apache.bcel.classfile.ClassParser;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class T {
    @Test
    public void t() {
        ApplicationContext application = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        UserDao userDao = application.getBean("userDao", UserDao.class);
        UserService userService = application.getBean("userService", UserServiceImpl.class);
//        int userId = userService.register(new User("3","1","1","1","1",1));

//        System.out.println("TTTTTTTTTTTTTTTTTTTTT================================"+userId);
    }
}
