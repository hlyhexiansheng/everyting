package leaderelection;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.leader.Participant;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by noodles on 16/11/20 上午7:15.
 */
public class LeaderLatchTest {


    public static void main(String[] args) {

        final String PATH = "/leadertest/LeaderLatch";

        final String ClientId = "client-" + new Random().nextInt(100000);

        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    final CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.56.103:2181",new ExponentialBackoffRetry(1000, 3));

                    final LeaderLatch leaderLatch = new LeaderLatch(client, PATH, ClientId);

                    leaderLatch.addListener(new LeaderLatchListener() {
                        public void isLeader() {
                            System.out.println("leader is " + ClientId);
                            try {
                                final Collection<Participant> participants = leaderLatch.getParticipants();
                                System.out.println("participants size = " + participants.size());
                                for(Participant participant : participants){
                                    System.out.println(participant);
                                }

                            } catch (Exception e) {

                            }
                        }

                        public void notLeader() {
                            System.out.println("lost leader" + ClientId);
                        }
                    });

                    client.start();

                    leaderLatch.start();

                    leaderLatch.await(1, TimeUnit.SECONDS);

                    System.out.println("isLeader-" + leaderLatch.hasLeadership());

                    Thread.sleep(10000);

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        };


        Thread t = new Thread(runnable);
        t.run();

        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
