package transformer;


import transformer.log.log4j.Log4jTransformer;
import transformer.log.log4j2.Log4j2Transformer;
import transformer.log.logback.LogBackTransformer;
import transformer.none.NoneTransformer;

/**
 * Created by noodles on 16/11/18 下午11:47.
 */
public class TransformerFactory {

    public static Transformer getTransformer(String className){
        if(className.equals("ch.qos.logback.classic.spi.LoggingEvent")){
            return new LogBackTransformer();
        }else if(className.equals("org.apache.log4j.spi.LoggingEvent")){
            return new Log4jTransformer();
        }else if(className.equals("org.apache.logging.log4j.core.config.LoggerConfig")){
            return new Log4j2Transformer();
        }
        return new NoneTransformer();
    }
}
