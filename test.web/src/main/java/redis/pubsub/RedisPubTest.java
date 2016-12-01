package redis.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Administrator on 2015/12/6.
 */
public class RedisPubTest {

    private  JedisSentinelPool pool;

    public static void main(String[] args){
        new RedisPubTest().run();
    }

    private void run(){
        this.initPool();
        PubThread thread = new PubThread(this.pool);
        thread.start();
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

class PubThread extends Thread{

    private JedisSentinelPool pool;

    public PubThread(JedisSentinelPool pool){
        this.pool = pool;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            String cms = scanner.nextLine();
            this.process(cms);
        }
    }

    private void process(String command) {
        if(command == null || command.length() < 5){
            return;
        }
        String[] cms = command.split(" ");
        String channels = cms[0];
        String params = cms[1];

        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = pool.getResource();
            jedis.publish(channels,params);
        } catch (Exception e) {
            System.out.println("get a exception.....");
            isBroken = true;
        } finally {
            if (isBroken) {
                pool.returnBrokenResource(jedis);
            } else {
                pool.returnResource(jedis);
            }
        }
    }
}
