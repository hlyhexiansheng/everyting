package demo2;

import backtype.storm.generated.GlobalStreamId;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

import java.util.Map;

/**
 * Created by noodles on 16/11/12 上午11:01.
 */
public class AckBolt2 extends BaseRichBolt{

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {

    }

    public void execute(Tuple input) {
        final GlobalStreamId sourceGlobalStreamid = input.getSourceGlobalStreamid();
        System.out.println("AckBolt2" + sourceGlobalStreamid.toString());
        System.out.println("AckBolt2" + input.getStringByField("antherword"));
        System.out.println("-----");
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
