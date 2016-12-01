package redis.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2015/12/6.
 */
public class RedisSubTest {

    private JedisSentinelPool pool;

    public static void main(String[] args){
        new RedisSubTest().run();
    }

    private void run(){
        this.initPool();
        Jedis jedis = pool.getResource();
        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println(message);
            }

            @Override
            public void onPMessage(String pattern, String channel, String message) {

            }

            @Override
            public void onSubscribe(String channel, int subscribedChannels) {

            }

            @Override
            public void onUnsubscribe(String channel, int subscribedChannels) {

            }

            @Override
            public void onPUnsubscribe(String pattern, int subscribedChannels) {

            }

            @Override
            public void onPSubscribe(String pattern, int subscribedChannels) {

            }
        }, "channelA");
    }

    private void initPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);

        Set<String> sentinel = new HashSet<String>();
        sentinel.add("192.168.120.191:26379");
        sentinel.add("192.168.120.192:26379");
        sentinel.add("192.168.120.193:26379");

        pool = new JedisSentinelPool("mymaster", sentinel, config);
    }
}
