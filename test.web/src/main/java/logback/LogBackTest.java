package logback;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by noodles on 16/5/31.
 * 测试日志级别,分别打印到不通级别的log文件中.
 */
public class LogBackTest {

    public static void main(String[] args) throws InterruptedException {
        Logger logger = LoggerFactory.getLogger(LogBackTest.class);

        for(int i = 0 ; i < Integer.MAX_VALUE; i++){
            logger.debug("debug...");
            logger.trace("trace...");
            logger.info("info....");
            logger.warn("warn");
            logger.error("error");
            Exception e = new NullPointerException("fuck");

            Logger logger1 = LoggerFactory.getLogger("DATAREPORT");
            logger1.info("datareport.......{},{}",i,i);
            logger1.info("datareportafds",e);
            logger1.info("bean {}",new Bean(2,"fff"));
            logger1.info("fff{}",1,3,43433,4343);
            if(i == Integer.MAX_VALUE - 10){
                i = 0;
            }
            Thread.sleep(1000);
        }


    }
}
@Data
class Bean{
    private int a;
    private  String b;
    public Bean(int a ,String b){
        this.a = a;
        this.b = b;
    }
}