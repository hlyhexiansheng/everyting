package lambda;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by noodles on 16/4/10.
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        Map map = new HashMap();
        map.put("key1","val1");

        final String s = JSON.toJSONString(map);

        final Map map1 = JSON.parseObject(s, Map.class);

        System.out.println(map1);

    }
}
