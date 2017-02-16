package directGrouping;

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
        map = new HashMap<>();
    }

    public void execute(Tuple input, BasicOutputCollector collector) {

        try {
            String test = input.getStringByField("tag");
            System.out.println("CountBolt:" + test);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void declareOutputFields(OutputFieldsDeclarer delarer) {

    }


}
