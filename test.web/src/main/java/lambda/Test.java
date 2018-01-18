package lambda;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * Created by noodles on 16/4/10.
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        EBean bean = new EBean();
        bean.age = 1;
        bean.userName = "hleiyu";
        bean.password = "fasdjoi";

        final String s = JSON.toJSONString(bean);

        System.out.println(s);
    }

}

class EBean {

    public String userName;

    public String password;

    public int age;
}
