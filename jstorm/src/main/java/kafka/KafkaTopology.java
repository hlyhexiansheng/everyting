package kafka;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.TopologyAssignException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;

import java.util.Map;
import java.util.Properties;

/**
 * Created by noodles on 16/11/14 下午3:42.
 */
public class KafkaTopology {

    private static Config conf = new Config();

    private static Properties props;

    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException, TopologyAssignException, InterruptedException {

        initProps();

        conf.setNumAckers(0);

        if(args != null && args.length > 0 && args[0].equals("remote")){
            System.out.println("remote.....");
            setRemoteTopology();
        }else {
            System.out.println("local......");
            setLocalTopology();
        }
    }

    public static void setRemoteTopology() throws AlreadyAliveException, InvalidTopologyException, TopologyAssignException {

        String topologyName = "kafkatopology";

        TopologyBuilder builder = new TopologyBuilder();

        setBuilder(builder, conf);

        conf.put(Config.STORM_CLUSTER_MODE, "distributed");
        conf.setNumWorkers(1);

        StormSubmitter.submitTopology(topologyName, conf, builder.createTopology());
    }

    public static void setLocalTopology() throws InterruptedException {
        TopologyBuilder builder = new TopologyBuilder();

        setBuilder(builder, conf);

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("kafkatopology", conf, builder.createTopology());

        Thread.sleep(10000000);
        cluster.killTopology("kafkatopology");
        cluster.shutdown();
    }

    private static void initProps() {
        props = new Properties();
        props.put("bootstrap.servers", ConstantsDef.Kafka_url);
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset","earliest");
    }


    public static void setBuilder(TopologyBuilder builder, Map conf) {
        builder.setSpout("kafkaspout", new KafkaSpout(props), 1);

        builder.setBolt("logicBolt1", new LogicBolt1(), 2)
                .fieldsGrouping("kafkaspout", Utils.DEFAULT_STREAM_ID,new Fields("kafkaword"));

    }

}
