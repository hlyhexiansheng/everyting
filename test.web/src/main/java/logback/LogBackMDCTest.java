package logback;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Created by noodles on 16/6/9.
 */
public class LogBackMDCTest {

    public static void main(String[] args) throws InterruptedException {
        org.slf4j.Logger logger = LoggerFactory.getLogger(LogBackTest.class);

        LogicServiceSameThread service = new LogicServiceSameThread();
        LogicServiceDiffThread service2 = new LogicServiceDiffThread();

        for(int i = 0; i < Integer.MAX_VALUE;i++){
            MDC.put("sessionId","requestID:" + String.valueOf(i+1));
            logger.error(String.valueOf(i));
            service.doBussiness();
            service2.doBusiness();

            Thread.sleep(1000);
        }
    }
}
