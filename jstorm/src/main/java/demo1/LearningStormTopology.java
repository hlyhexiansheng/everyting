package demo1;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.TopologyAssignException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

import java.util.Map;

/**
 * Created by noodles on 16/11/3 上午11:06.
 */
public class LearningStormTopology {

    private static Config conf = new Config();

    private static int randomNumber = 10000;

    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException, TopologyAssignException, InterruptedException {

        if(args != null && args.length > 0 && args[0].equals("remote")){
            System.out.println("remote.....");

            if(args.length == 2){
                randomNumber = Integer.valueOf(args[1]);
            }
            setRemoteTopology();
        }else {
            System.out.println("local......");
            setLocalTopology();
        }

    }

    public static void setRemoteTopology() throws AlreadyAliveException, InvalidTopologyException, TopologyAssignException {

        String streamName = "LearningStormBolt";

        TopologyBuilder builder = new TopologyBuilder();

        setBuilder(builder, conf);

        conf.put(Config.STORM_CLUSTER_MODE, "distributed");
        conf.setNumWorkers(2);

        StormSubmitter.submitTopology(streamName, conf, builder.createTopology());
    }

    public static void setLocalTopology() throws InterruptedException {
        TopologyBuilder builder = new TopologyBuilder();

        setBuilder(builder, conf);

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("LearningStormBolt", conf, builder.createTopology());

        Thread.sleep(10000000);
        cluster.killTopology("LearningStormBolt");
        cluster.shutdown();
    }

    public static void setBuilder(TopologyBuilder builder, Map conf) {
        builder.setSpout("console", new ConsoleSpout(randomNumber), 1);
        builder.setBolt("LearningStormBolt", new LearningStormBolt(), 10).fieldsGrouping("console",new Fields("site"));
    }



















    private static void old(){
        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("console", new ConsoleSpout(), 2);
        builder.setBolt("LearningStormBolt", new LearningStormBolt(), 4).fieldsGrouping("console",new Fields("site"));

        Config conf = new Config();
        conf.setDebug(true);

        LocalCluster cluster = new LocalCluster();

        cluster.submitTopology("LearningStormToplogy", conf, builder.createTopology());
        try {
            Thread.sleep(10000000);
        } catch (Exception exception) {
            System.out.println("Thread interrupted exception : " + exception);
        }

        cluster.killTopology("LearningStormToplogy");

        cluster.shutdown();
    }



}
