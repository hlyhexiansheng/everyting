package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Administrator on 2015/12/6.
 */
public class RedisSentinelTest {

    private static JedisSentinelPool pool;

    public static void main(String[] args) throws InterruptedException {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);

        Set<String> sentinel = new HashSet<String>();
        sentinel.add("192.168.120.191:26379");
        sentinel.add("192.168.120.192:26379");
        sentinel.add("192.168.120.193:26379");

        pool = new JedisSentinelPool("mymaster", sentinel, config);

        Scanner scan = new Scanner(System.in);
        while (true) {
            String command = scan.nextLine();
            process(command);
        }
    }

    private static void process(String command) {
        if(command == null || command.length() < 5){
            return;
        }
        String[] cms = command.split(" ");
        String cmd = cms[0];
        String key = cms[1];
        String params = null;
        if(cms.length > 2){
            params = cms[2];
        }

        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = pool.getResource();
            String result = null;
            if (cmd.equals("set")) {
                result = jedis.set(key, params);
                System.out.println("set from Node " + jedis.getClient().getHost() + " result = " + result);
            } else {
                result = jedis.get(key);
                System.out.println("get from Node " + jedis.getClient().getHost() + " result = " + result);
            }
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
