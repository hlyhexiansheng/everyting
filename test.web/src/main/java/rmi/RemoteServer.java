package rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Created by Administrator on 2015/12/13.
 */
public class RemoteServer {

    public static void main(String[] args){
        try {
            IHellowordService hellowordService = new HelloServiceImpl();
            LocateRegistry.createRegistry(8888);
            Naming.bind("rmi://localhost:8888/hellowordService", hellowordService);
            System.out.println(">>>>>INFO:远程IHello对象绑定成功！");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
