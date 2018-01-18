package demo2;

import backtype.storm.Config;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

/**
 * Created by noodles on 16/11/11 下午4:56.
 * 测试Ack机制
 */
public class AckSpout extends BaseRichSpout {


    private SpoutOutputCollector spoutOutputCollector;

    private Queue<String> data = new ArrayDeque();


    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        conf.put(Config.TOPOLOGY_MESSAGE_TIMEOUT_SECS, 1);
        this.spoutOutputCollector = collector;
        this.data.add("1");
        this.data.add("2");
        this.data.add("3");
        this.data.add("4");
        this.data.add("5");
        this.data.add("6");
        this.data.add("7");
        this.data.add("8");
        this.data.add("9");
        this.data.add("10");
        this.data.add("11");
        this.data.add("12");
    }

    public void nextTuple() {
        try {

            String val = this.data.poll();
            if (val == null) {
                return;
            }
            this.spoutOutputCollector.emit(new Values(val), UUID.randomUUID().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fail(Object msgId) {
        System.out.println("spout fail" + msgId);
    }


    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}
