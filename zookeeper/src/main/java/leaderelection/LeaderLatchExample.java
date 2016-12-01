package leaderelection;


import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.Participant;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by noodles on 16/11/20 上午5:31.
 */
public class LeaderLatchExample {
    private static final int CLIENT_QTY = 10;
    private static final String PATH = "/leaderelecion/leader";

    public static void main(String[] args) throws Exception {

        List<CuratorFramework> clients = Lists.newArrayList();

        List<LeaderLatch> latches = Lists.newArrayList();

        TestingServer server = new TestingServer();

        try {
            for (int i = 0; i < CLIENT_QTY; ++i) {

                CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000, 3));

                LeaderLatch leaderLatch = new LeaderLatch(client, PATH, "Client #" + i);

                clients.add(client);

                latches.add(leaderLatch);

                client.start();

                leaderLatch.start();
            }

            Thread.sleep(20000);

            LeaderLatch currentLeader = null;
            for (int i = 0; i < CLIENT_QTY; ++i) {
                LeaderLatch leaderLatch = latches.get(i);
                if (leaderLatch.hasLeadership()){
                    currentLeader = leaderLatch;
                }

                if(i == 0){
                    final Collection<Participant> participants = leaderLatch.getParticipants();
                    System.out.println("participants size = " + participants.size());
                    for(Participant participant : participants){
                        System.out.println(participant);
                    }
                }

            }
            System.out.println("current leader is " + currentLeader.getId());
            System.out.println("release the leader " + currentLeader.getId());

            currentLeader.close();

            latches.get(0).await(2, TimeUnit.SECONDS);
            System.out.println("Client #0 maybe is elected as the leader or not although it want to be");
            System.out.println("the new leader is " + latches.get(0).getLeader().getId());

            System.out.println("Press enter/return to quit\n");
            new BufferedReader(new InputStreamReader(System.in)).readLine();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Shutting down...");
            for (LeaderLatch exampleClient : latches) {
                CloseableUtils.closeQuietly(exampleClient);
            }
            for (CuratorFramework client : clients) {
                CloseableUtils.closeQuietly(client);
            }
            CloseableUtils.closeQuietly(server);
        }
    }
}
