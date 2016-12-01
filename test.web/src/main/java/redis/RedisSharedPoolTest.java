package redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2015/12/5.
 */
public class RedisSharedPoolTest {

    public static void main(String[] args) throws InterruptedException {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();

        JedisShardInfo info188 = new JedisShardInfo("192.168.120.188",6379);
        JedisShardInfo info189 = new JedisShardInfo("192.168.120.189",6379);
        JedisShardInfo info190 = new JedisShardInfo("192.168.120.190",6379);
        List<JedisShardInfo> list = Arrays.asList(info188,info189,info190);


        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(config,list);

        ShardedJedis resource = shardedJedisPool.getResource();
        for(int i = 0; i < 1000;i++){
//            resource.set("key" + i,"value" + i);
            if(resource == null){
                resource = shardedJedisPool.getResource();
            }
            try{
                String key = "key" + i;
                String value = resource.get(key);
                System.out.println(value + "---from Node:" + resource.getShard(key).getClient().getHost());
            }catch (Exception e){
                shardedJedisPool.returnBrokenResource(resource);
                resource = null;
                System.out.println("lost connect" + resource);
            }
            Thread.sleep(1000);
        }

    }
}
