package service;

import p.Elog;

/**
 * Created by noodles on 16/11/16 下午5:37.
 */
public class OrderService {

    private Elog elog = Elog.getLogger(OrderService.class);

    public static final OrderService instance = new OrderService();

    public void callFakeMethod(){
        elog.error("occur some error msg");
    }

}
