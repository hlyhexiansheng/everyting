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

        HashMap<CharSequence,CharSequence> map = new HashMap();
        map.put("key1","val1");

        final CharSequence sequence = map.get("key1");

    }
}
