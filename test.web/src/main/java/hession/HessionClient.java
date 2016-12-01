package hession;

import com.caucho.hessian.client.HessianProxyFactory;
import hession.service.HessianObject;
import hession.service.IBasicService;

/**
 * Created by Administrator on 2015/12/14.
 */
public class HessionClient {
    public static void main(String []args)
            throws Exception
    {
        String url = "http://localhost:9696/hello";

        HessianProxyFactory factory = new HessianProxyFactory();
        IBasicService basic = (IBasicService) factory.create(IBasicService.class, url);

        System.out.println("Hello: " + basic.hello());

        HessianObject object = new HessianObject();
        object.setId(1);
        object.setName("heliyu");
        object.setPassword("123");

        HessianObject object1 = basic.updateHessianObject(object);
        System.out.println("client recived " + object1);

    }
}
