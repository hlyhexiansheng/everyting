package demo2;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.TopologyAssignException;
import backtype.storm.topology.TopologyBuilder;

import java.util.Map;

/**
 * Created by noodles on 16/11/11 下午4:57.
 */
public class AckTopology {

    private static Config conf = new Config();

    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException, TopologyAssignException, InterruptedException {

        conf.setNumAckers(1);
        conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 10);
        conf.setMessageTimeoutSecs(5);
        Config.setNumAckers(conf,0);

        if (args != null && args.length > 0 && args[0].equals("remote")) {
            System.out.println("remote.....");
            setRemoteTopology();
        } else {
            System.out.println("local......");
            setLocalTopology();
        }
    }

    public static void setRemoteTopology() throws AlreadyAliveException, InvalidTopologyException, TopologyAssignException {

        String topologyName = "ackTopology";

        TopologyBuilder builder = new TopologyBuilder();

        setBuilder(builder, conf);

        conf.put(Config.STORM_CLUSTER_MODE, "distributed");
        conf.setNumWorkers(2);

        StormSubmitter.submitTopology(topologyName, conf, builder.createTopology());
    }

    public static void setLocalTopology() throws InterruptedException {
        TopologyBuilder builder = new TopologyBuilder();

        setBuilder(builder, conf);

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("LearningAckStormBolt", conf, builder.createTopology());

        Thread.sleep(10000000);
        cluster.killTopology("LearningAckStormBolt");
        cluster.shutdown();
    }

    public static void setBuilder(TopologyBuilder builder, Map conf) {
        builder.setSpout("spout", new AckSpout(), 1);
        builder.setBolt("ackBolt", new AckBolt(), 1).shuffleGrouping("spout");

//        builder.setBolt("ackBolt2", new AckBolt2(), 1)
//                .shuffleGrouping("ackBolt");
    }

}
