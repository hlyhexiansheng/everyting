package logback;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;

/**
 * Created by noodles on 16/6/9.
 */
public class LogicServiceDiffThread {

    public void doBusiness(){
        System.out.println("--------" + MDC.get("sessionId"));
        System.out.println("MDC--value" + MDC.getCopyOfContextMap());
        new T1(MDC.getCopyOfContextMap()).start();
    }
}

class T1 extends Thread{
    private Map previous;
    public T1(){}
    public T1(Map previous){
        this.previous = previous;
    }

    org.slf4j.Logger logger = LoggerFactory.getLogger(T1.class);
    @Override
    public void run() {
        MDC.setContextMap(previous);
        System.out.println("MDC--value" + MDC.getCopyOfContextMap());
        System.out.println("--------" + MDC.get("sessionId"));
        logger.error("T1....");
    }
}
