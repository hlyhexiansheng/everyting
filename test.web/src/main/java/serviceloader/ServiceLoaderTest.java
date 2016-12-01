package serviceloader;

import java.util.ServiceLoader;

/**
 * Created by Administrator on 2015/12/22.
 */
public class ServiceLoaderTest {

    public static void main(String[] agrs){
        ServiceLoader<MessageService> serviceLoader = ServiceLoader.load(MessageService.class);
        for(MessageService service : serviceLoader) {
            System.out.println(service.getMessage());
        }
    }
}
