package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Administrator on 2015/12/13.
 */
public interface IHellowordService extends Remote{

    String say(String content) throws RemoteException;

    ShareObject getShare() throws RemoteException;

    boolean sendShareOject(ShareObject shareObject) throws RemoteException;

    ShareObject updateShareObject(ShareObject shareObject) throws RemoteException;

}
