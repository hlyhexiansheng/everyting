package redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/12/4.
 */
public class RedisPoolTest {


    public static void main(String[] args){
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxWaitMillis(1000);
        config.setMaxTotal(100);
//        JedisPool pool = new JedisPool("192.168.120.188",6379);
        JedisPool pool = new JedisPool(config,"192.168.120.188",6379);
        ArrayList list = new ArrayList();
        Object object = new Object();

        for (int i = 0;i < 100/*Integer.MAX_VALUE*/;i++){
            try{
                list.add(pool.getResource());
            }catch (JedisConnectionException e){
                System.out.println("jedis get connection fail!!!");
            }

        }
        synchronized (object){
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Jedis jedis = pool.getResource();
        System.out.println(jedis.get("key1"));
    }
}
