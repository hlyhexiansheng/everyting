package fieldgrouping;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;

import java.util.Map;
import java.util.Properties;

/**
 * Created by noodles on 17/1/16 上午10:26.
 */
public class FieldGroupingTest {

    private static Config conf = new Config();

    private static Properties props;

    public static void main(String[] args) throws InterruptedException {

        initProps();

        conf.setNumAckers(0);

        setLocalTopology();

    }

    public static void setLocalTopology() throws InterruptedException {
        TopologyBuilder builder = new TopologyBuilder();

        setBuilder(builder, conf);

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("FieldGroupingTest", conf, builder.createTopology());

        Thread.sleep(10000000);
        cluster.killTopology("FieldGroupingTest");
        cluster.shutdown();
    }

    private static void initProps() {
        props = new Properties();
        props.put("bootstrap.servers", "10.40.6.151:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest");
    }


    public static void setBuilder(TopologyBuilder builder, Map conf) {
        builder.setSpout("spout", new Spout(props), 1);

        builder.setBolt("countBolt", new CountBolt(), 1)
                .fieldsGrouping("spout", Utils.DEFAULT_STREAM_ID, new Fields("tag"));

    }
}
