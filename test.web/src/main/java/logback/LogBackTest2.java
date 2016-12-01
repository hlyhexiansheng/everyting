package logback;

import org.slf4j.LoggerFactory;

/**
 * Created by noodles on 16/6/7.
 * 测试多进程写入同一个日志文件,导致日志丢失
 */
public class LogBackTest2 {

    public static void main(String[] args) throws InterruptedException {
        double v = Math.random() * 1000;
        org.slf4j.Logger logger = LoggerFactory.getLogger(LogBackTest.class);
        for(int i = 0; i < Integer.MAX_VALUE;i++){
            logger.error( v + " ");
            Thread.sleep(1000);
        }
    }
}
