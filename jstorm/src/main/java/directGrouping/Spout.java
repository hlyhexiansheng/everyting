//package directGrouping;
//
//import backtype.storm.spout.SpoutOutputCollector;
//import backtype.storm.task.TopologyContext;
//import backtype.storm.topology.OutputFieldsDeclarer;
//import backtype.storm.topology.base.BaseRichSpout;
//import backtype.storm.tuple.Fields;
//import backtype.storm.tuple.Values;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//
//import java.util.Arrays;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * Created by noodles on 16/11/14 下午3:21.
// */
//public class Spout extends BaseRichSpout {
//
//    private final Properties kafkaProperties;
//
//    private SpoutOutputCollector collector;
//
//    private transient KafkaConsumer<String, String> consumer;
//
//
//    public Spout(Properties kafkaProperties) {
//        this.kafkaProperties = kafkaProperties;
//    }
//
//    private void initKafka() {
//        if (consumer == null) {
//            consumer = new KafkaConsumer<>(kafkaProperties);
//            consumer.subscribe(Arrays.asList("fieldGrouping"));
//        }
//    }
//
//    @Override
//    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
//        this.collector = collector;
//        initKafka();
//    }
//
//
//    @Override
//    public void nextTuple() {
//        final ConsumerRecords<String, String> records = consumer.poll(100);
//        for (ConsumerRecord<String, String> record : records) {
//            this.collector.emit(new Values(record.value(), "msg" + record.value()));
//            this.collector.emit("monitorStream",new Values("1"));
//        }
//    }
//
//    @Override
//    public void declareOutputFields(OutputFieldsDeclarer declarer) {
//        declarer.declare(new Fields("tag", "msg"));
//        declarer.declareStream("monitorStream",new Fields("monitorMsg"));
//
//    }
//}
