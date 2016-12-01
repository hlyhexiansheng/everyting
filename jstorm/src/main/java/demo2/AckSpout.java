package demo2;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

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

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declareStream(Utils.DEFAULT_STREAM_ID,new Fields("word","word1","word2"));
        declarer.declareStream(AckTopologyDef.SteamId,new Fields("antherword"));
    }

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.spoutOutputCollector = collector;
        this.data.add("1");
        this.data.add("2");
        this.data.add("3");
    }

    public void nextTuple() {
        try {

//            Thread.sleep(10000);

            String remove = this.data.poll();
            if(remove == null){
                return;
            }
            this.spoutOutputCollector.emit(Utils.DEFAULT_STREAM_ID,new Values(remove,remove,remove),UUID.randomUUID().toString());

            this.spoutOutputCollector.emit(AckTopologyDef.SteamId,new Values(remove + "+" + remove),UUID.randomUUID().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fail(Object msgId) {
        System.out.println(msgId);
    }
}
