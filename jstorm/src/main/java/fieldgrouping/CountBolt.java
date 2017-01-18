package fieldgrouping;

import backtype.storm.Config;
import backtype.storm.Constants;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by noodles on 16/11/3 上午11:01.
 */
@Slf4j
public class CountBolt extends BaseBasicBolt {

    private static final long serialVersionUID = 1L;

    private Map<String, AtomicInteger> map;

    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        System.out.println("call countBolt......");
        map = new HashMap<>();
    }

    public void execute(Tuple input, BasicOutputCollector collector) {

        if (isTickTuple(input)) {
            System.out.println(Thread.currentThread().getName() + ":" + map.toString());
            return;
        }


        String test = input.getStringByField("tag");

        if (!map.containsKey(test)) {
            map.put(test, new AtomicInteger(0));
        }
        final int andIncrement = map.get(test).getAndIncrement();

    }

    protected static boolean isTickTuple(Tuple tuple) {
        return tuple.getSourceComponent().equals(Constants.SYSTEM_COMPONENT_ID)
                && tuple.getSourceStreamId().equals(Constants.SYSTEM_TICK_STREAM_ID);
    }


    public void declareOutputFields(OutputFieldsDeclarer delarer) {
//        delarer.declare(new Fields("word"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        Config config = new Config();
        config.put(Config.TOPOLOGY_TICK_TUPLE_FREQ_SECS, 5);
        return config;
    }
}
