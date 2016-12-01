package hession.service;

/**
 * Created by Administrator on 2015/12/14.
 */
public class BasicServiceImpl implements IBasicService{

    public String hello() {
        return "hello,fuck..from hession";
    }

    public HessianObject updateHessianObject(HessianObject hessianObject) {
        System.out.println(hessianObject);
        hessianObject.setPassword("newPasww" + hessianObject.getPassword());
        return hessianObject;
    }
}
