package redis;

import redis.clients.jedis.Jedis;

/**
 * Created by noodles on 16/12/10 上午11:01.
 */
public class RedisSimpleTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("10.40.6.130",6379,10000);
        final String s = jedis.get("foo");
        System.out.println(s);
    }
}
