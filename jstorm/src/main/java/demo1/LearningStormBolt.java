//package demo1;
//
//import backtype.storm.topology.BasicOutputCollector;
//import backtype.storm.topology.OutputFieldsDeclarer;
//import backtype.storm.topology.base.BaseBasicBolt;
//import backtype.storm.tuple.Fields;
//import backtype.storm.tuple.Tuple;
//import backtype.storm.tuple.Values;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
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
//    private static FileWriter fileWriter;
//
//    public void execute(Tuple input, BasicOutputCollector collector) {
//
//        String test = input.getStringByField("site");
//
//        System.out.println("Name of input site is : " + test);
//
//        write2File(test);
//    }
//
//    private void write2File(String s) {
//
//        log.info("got message " + s);
//        try {
//            if(fileWriter == null){
//                fileWriter = new FileWriter("/tmp/ss_" + String.valueOf(new Random().nextInt(100)));
//            }
//            fileWriter.write(s + "\n");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void declareOutputFields(OutputFieldsDeclarer delarer) {
//        delarer.declare(new Fields("word"));
//    }
//}
