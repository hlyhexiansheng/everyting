//package kafka;
//
//import backtype.storm.task.OutputCollector;
//import backtype.storm.task.TopologyContext;
//import backtype.storm.topology.OutputFieldsDeclarer;
//import backtype.storm.topology.base.BaseRichBolt;
//import backtype.storm.tuple.Tuple;
//
//import java.util.Map;
//
///**
// * Created by noodles on 16/11/14 下午3:46.
// */
//public class LogicBolt1 extends BaseRichBolt{
//
//    @Override
//    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
//
//    }
//
//    @Override
//    public void execute(Tuple input) {
//        System.out.println(input.getStringByField("kafkaword"));
//    }
//
//    @Override
//    public void declareOutputFields(OutputFieldsDeclarer declarer) {
//
//    }
//}
