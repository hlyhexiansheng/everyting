package demo2;

import backtype.storm.generated.GlobalStreamId;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

import java.util.Map;

/**
 * Created by noodles on 16/11/11 下午4:57.
 */
public class AckBolt extends BaseRichBolt{

    private OutputCollector collector;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    public void execute(Tuple input) {

        final GlobalStreamId sourceGlobalStreamid = input.getSourceGlobalStreamid();
        System.out.println("AckBolt " + sourceGlobalStreamid.toString());
        if(input.getFields().contains("antherword")){
            System.out.println("AckBolt " + input.getStringByField("antherword"));
        }else {
            System.out.println("size=" + input.getFields().size());
            System.out.println("AckBolt " + input.getStringByField("word"));
        }

        System.out.println("-----");

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
