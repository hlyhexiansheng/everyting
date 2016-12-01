package dubbo.usespring.provider;

import com.google.common.util.concurrent.AbstractIdleService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 使用spring
 */
public class ProviderBootStrap extends AbstractIdleService {

    private ClassPathXmlApplicationContext context;

    public static void main(String[] args){
        ProviderBootStrap bootStrap = new ProviderBootStrap();
        bootStrap.startAsync();
        try{
            Object lock = new Object();
            synchronized (lock){
                while(true){
                    lock.wait();
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    @Override
    protected void startUp() throws Exception {
        context = new ClassPathXmlApplicationContext(new String[]{"dubboframework/provide-spring-context.xml"});
        context.start();
        context.registerShutdownHook();
        System.out.println("dobbo service inited.....");
    }

    @Override
    protected void shutDown() throws Exception {
        context.stop();
    }
}
