package springioc;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/12/16.
 */
public class IOCServiceImpl implements IOCService {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public IOCRef getIocRef() {
        return iocRef;
    }

    public void setIocRef(IOCRef iocRef) {
        this.iocRef = iocRef;
    }

    private IOCRef iocRef;

    public IOCServiceImpl() {
        System.out.println();
    }

    public void helloWorld() {
        System.out.println("fuck,IOC");
        System.out.println(iocRef);
        System.out.println(name);
    }
}
