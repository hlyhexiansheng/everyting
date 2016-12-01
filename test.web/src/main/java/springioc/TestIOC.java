package springioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2015/12/16.
 */
public class TestIOC {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/IOCTestApplicationContext.xml");
        IOCService iocService = context.getBean("iocService", IOCService.class);
        iocService.helloWorld();
    }
}
