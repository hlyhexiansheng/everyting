//package ticktest;
//
//import backtype.storm.Config;
//import backtype.storm.Constants;
//import backtype.storm.topology.BasicOutputCollector;
//import backtype.storm.topology.OutputFieldsDeclarer;
//import backtype.storm.topology.base.BaseBasicBolt;
//import backtype.storm.tuple.Fields;
//import backtype.storm.tuple.Tuple;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Map;
//import java.util.Random;
//
///**
// * Created by noodles on 16/11/3 上午11:01.
// */
//@Slf4j
//public class LearningStormBolt extends BaseBasicBolt {
//
//    private static final long serialVersionUID = 1L;
//
//
//    public void execute(Tuple input, BasicOutputCollector collector) {
//
//        if(isTickTuple(input)){
//            System.out.println("learningStormBolt system tick....");
//            return;
//        }
//
//        String test = input.getStringByField("site");
//        System.out.println(test);
//
//    }
//
//    protected static boolean isTickTuple(Tuple tuple) {
//        return tuple.getSourceComponent().equals(Constants.SYSTEM_COMPONENT_ID)
//                && tuple.getSourceStreamId().equals(Constants.SYSTEM_TICK_STREAM_ID);
//    }
//
//
//    public void declareOutputFields(OutputFieldsDeclarer delarer) {
//        delarer.declare(new Fields("word"));
//    }
//
//    @Override
//    public Map<String, Object> getComponentConfiguration() {
//        Config config = new Config();
//        config.put(Config.TOPOLOGY_TICK_TUPLE_FREQ_SECS, 5);
//        return config;
//    }
//}
