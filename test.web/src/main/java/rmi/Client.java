package rmi;

import java.rmi.Naming;

/**
 * Created by Administrator on 2015/12/13.
 */
public class Client {

    public static void main(String[] agrs){
        try {
            //在RMI服务注册表中查找名称为RHello的对象，并调用其上的方法
            IHellowordService hellowordService =(IHellowordService) Naming.lookup("rmi://localhost:8888/hellowordService");
            System.out.println(hellowordService.say("fuck"));
            System.out.println(hellowordService.getShare());
            System.out.println(hellowordService.sendShareOject(ShareObject.build(11,"fasd","fdasf")));
            ShareObject object = ShareObject.build(11, "fasd", "123");
            ShareObject object1 = hellowordService.updateShareObject(object);
            System.out.println( object1 + "\t" + object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
