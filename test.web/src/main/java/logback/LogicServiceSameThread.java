package logback;

import org.slf4j.LoggerFactory;

/**
 * Created by noodles on 16/6/9.
 */
public class LogicServiceSameThread {

    public void doBussiness(){
        org.slf4j.Logger logger = LoggerFactory.getLogger(LogicServiceSameThread.class);
        logger.error("service LogicServiceSameThread");
    }
}
