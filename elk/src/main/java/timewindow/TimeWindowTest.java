package timewindow;

import java.util.Random;

/**
 * Created by noodles on 16/11/23 下午5:05.
 */
public class TimeWindowTest {

    public static void main(String[] args) throws InterruptedException {
        MetricsInfo metricsInfo = new MetricsInfo();
        metricsInfo.setTitle("com.service.OrderService");
        metricsInfo.setTimeSpanSize(10);

        TimeWindow timeWindow = new TimeWindow(metricsInfo);


        class AcceptThread extends Thread{
            private TimeWindow timeWindow;
            public AcceptThread(TimeWindow timeWindow){
                this.timeWindow = timeWindow;
            }
            @Override
            public void run() {
                while (true){
                    ElogMessage message = new ElogMessage();
                    long createTime = System.currentTimeMillis() / 1000;
                    message.setCreateTime(createTime);
                    message.setMsg("message-" + createTime);
                    this.timeWindow.acceptNewLog(message);
                    Util.sleep(1000);
                }
            }
        }

        class TickThread extends Thread{

            private TimeWindow timeWindow;

            private TickThread(TimeWindow timeWindow){
                this.timeWindow = timeWindow;
            }
            @Override
            public void run() {
                while (true){
                    this.timeWindow.onTick();
                    Util.sleep(2000);
                }
            }
        }

        AcceptThread acceptThread = new AcceptThread(timeWindow);
        TickThread tickThread = new TickThread(timeWindow);
        acceptThread.start();
        tickThread.start();

        tickThread.join();

    }
}
