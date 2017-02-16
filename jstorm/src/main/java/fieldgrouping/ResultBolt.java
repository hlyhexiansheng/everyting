package fieldgrouping;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

/**
 * Created by noodles on 2017/2/16 15:45.
 */
public class ResultBolt extends BaseBasicBolt {

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        System.out.println("ResultBolt taskId=" + input.getSourceTask());
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
