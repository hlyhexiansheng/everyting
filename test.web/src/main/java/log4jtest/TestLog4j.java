package log4jtest;

import org.apache.log4j.Logger;

/**
 * Created by noodles on 16/6/5.
 * 测试多进程写入同一个日志文件,导致日志丢失
 */
public class TestLog4j {


    public static void main(String[] args) throws InterruptedException {

        double v = Math.random() * 1000;
        Logger logger = Logger.getLogger(TestLog4j.class);
        for(int i = 0; i < Integer.MAX_VALUE;i++){
            logger.error( v + " ");
            Thread.sleep(1000);
        }
    }
}
