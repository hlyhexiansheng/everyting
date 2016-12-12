import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by noodles on 16/12/4 下午7:35.
 */
public class TestJson {

    public static void main(String[] args) {
        Map map = new HashMap<>();
        map.put("key","\"");
        map.put("key2","123");
        final String s = JSON.toJSONString(map);
        System.out.println(s);
        final Map map1 = JSON.parseObject(s, Map.class);
        System.out.println(map1.get("key"));
    }
}
