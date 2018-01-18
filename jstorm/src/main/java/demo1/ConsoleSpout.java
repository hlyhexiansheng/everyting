//package demo1;
//
//import backtype.storm.spout.SpoutOutputCollector;
//import backtype.storm.task.TopologyContext;
//import backtype.storm.topology.OutputFieldsDeclarer;
//import backtype.storm.topology.base.BaseRichSpout;
//import backtype.storm.tuple.Fields;
//import backtype.storm.tuple.Values;
//
//import java.util.*;
//
///**
// * Created by noodles on 16/11/7 下午5:47.
// */
//public class ConsoleSpout extends BaseRichSpout {
//
//    private static final long serialVersionUID = 1L;
//
//    private SpoutOutputCollector spoutOutputCollector;
//
//    private Random random = new Random();
//
//    private int randomNumber;
//
//    public ConsoleSpout(int randomNumber) {
//        this.randomNumber = randomNumber;
//    }
//
//    public ConsoleSpout() {
//
//    }
//
//
//    public void open(Map conf, TopologyContext context, SpoutOutputCollector spoutOutputCollector) {
//        this.spoutOutputCollector = spoutOutputCollector;
//    }
//
//    public void nextTuple() {
//
//            if(random.nextInt(randomNumber) > 3){
//                return;
//            }
//
//            String r = String.valueOf(new Random().nextInt());
//
//            spoutOutputCollector.emit(new Values(r));
//
//    }
//
//    public void declareOutputFields(OutputFieldsDeclarer declarer) {
//        declarer.declare(new Fields("site"));
//    }
//
//}
