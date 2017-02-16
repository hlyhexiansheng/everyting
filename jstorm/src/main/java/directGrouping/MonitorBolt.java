package directGrouping;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by noodles on 2017/2/3 15:41.
 */
public class MonitorBolt extends BaseBasicBolt {

    private static final long serialVersionUID = 1L;

    private Map<String, AtomicInteger> map;

    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        map = new HashMap<>();
    }

    public void execute(Tuple input, BasicOutputCollector collector) {

        try {
            String test = input.getStringByField("monitorMsg");

            System.out.println("MonitorBolt:" + test);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void declareOutputFields(OutputFieldsDeclarer delarer) {

    }
}
