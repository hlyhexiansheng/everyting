package dubbo.usespring.cosumer;

import com.alibaba.dubbo.rpc.RpcContext;
import dubbo.service.TestService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2015/12/19.
 * 使用spring
 */
public class CosumerBootStrap {

    public static void main(String[] agrs) throws ExecutionException, InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubboframework/cunsumer-spring-context.xml");
        TestService testService = (TestService) context.getBean("testService");

        testService.getNameList(); //异步调用


        Future<List<String>> future = RpcContext.getContext().getFuture();
        List<String> nameList = future.get();

        for(Iterator iterator = nameList.iterator(); iterator.hasNext();){
            System.out.println(iterator.next());
        }
    }
}
