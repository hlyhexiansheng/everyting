package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Administrator on 2015/12/13.
 */
public class HelloServiceImpl extends UnicastRemoteObject implements IHellowordService {


    protected HelloServiceImpl() throws RemoteException {

    }

    @Override
    public String say(String content) throws RemoteException {
        return "hello," + content;
    }

    @Override
    public ShareObject getShare() throws RemoteException {
        System.out.println("server sended the object.....");
        return ShareObject.build(1,"heliyu","123123");
    }

    @Override
    public boolean sendShareOject(ShareObject shareObject) throws RemoteException {
        if(shareObject != null){
            System.out.println("server receive the object--" + shareObject);
            return true;
        }
        return false;
    }

    @Override
    public ShareObject updateShareObject(ShareObject shareObject) throws RemoteException {
        System.out.println("server receive the object" + shareObject);
        shareObject.setPassword("newpassword" + shareObject.getPassword());
        System.out.println("server sended receive the object" + shareObject);
        return shareObject;
    }
}
