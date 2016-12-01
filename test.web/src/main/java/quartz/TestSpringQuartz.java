package quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import springioc.IOCService;

/**
 * Created by noodles on 16/3/9.
 */
public class TestSpringQuartz {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-quartz.xml");
    }
}
