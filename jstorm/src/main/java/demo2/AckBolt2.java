package demo2;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

import java.util.Map;

/**
 * Created by noodles on 16/11/12 上午11:01.
 */
public class AckBolt2 extends BaseRichBolt {

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {

    }

    public void execute(Tuple input) {
        try {
            final String newWord = input.getStringByField("newWord");
            System.out.println(newWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
