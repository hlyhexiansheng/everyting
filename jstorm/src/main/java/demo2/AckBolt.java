package demo2;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.Map;

/**
 * Created by noodles on 16/11/11 下午4:57.
 */
public class AckBolt extends BaseRichBolt {

    private OutputCollector collector;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    public void execute(Tuple input) {

        try {
            final String word = input.getStringByField("word");
//            this.collector.emit(new Values("new" + word));
//            collector.fail(input);
//            this.collector.ack(input);
            System.out.println(word);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    @Override
//    public void prepare(Map stormConf, TopologyContext context) {
//    }


    //    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        try {
            final String word = input.getStringByField("word");
//            this.collector.emit(new Values("new" + word));
//            collector.fail(input);
            System.out.println(word);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
//        declarer.declare(new Fields("newWord"));
    }
}
