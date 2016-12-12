package timewindow;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by noodles on 16/11/23 下午3:45.
 */
public class TimeWindow {

    private MetricsInfo metricsInfo;

    private long startTime;

    private long endTime;

    private PriorityQueue<ElogMessage> priorityQueue;


    public TimeWindow(MetricsInfo metricsInfo){
        this.metricsInfo = metricsInfo;
        this.endTime = System.currentTimeMillis() / 1000;
        this.startTime= this.endTime - metricsInfo.getTimeSpanSize();
    }


    public synchronized void acceptNewLog(ElogMessage elogMessage){
        this.priorityQueue.add(elogMessage);
    }

    public synchronized void onTick(){
        this.startTime++;
        this.endTime++;

        while (true){
            final ElogMessage msg = this.priorityQueue.peek();
            if(msg == null){
                break;
            }
            if (msg.getCreateTime() < this.startTime){
                final ElogMessage poll = this.priorityQueue.poll();
            }else {
                break;
            }
        }
        System.out.println("size:" + this.priorityQueue.size());
    }



}

