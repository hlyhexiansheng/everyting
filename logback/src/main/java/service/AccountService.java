package service;

import p.Elog;

/**
 * Created by noodles on 16/11/16 下午5:33.
 */
public class AccountService {

    private Elog elog = Elog.getLogger(AccountService.class);

    public static final AccountService instance = new AccountService();

    public void callFakeMethod(){
        elog.error("occur some error msg");
    }

}
