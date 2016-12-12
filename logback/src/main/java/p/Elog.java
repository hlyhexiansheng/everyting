package p;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by noodles on 16/11/16 下午3:35.
 */
public class Elog {

    private Logger logger;

    private Elog(Class clazz){
        logger = LoggerFactory.getLogger(clazz);
    }

    public static Elog getLogger(Class clazz){
        return new Elog(clazz);
    }

    public void info(String msg){
        final StackTraceElement logPointFrame = Thread.currentThread().getStackTrace()[2];

        String[] titleInfoArr = new String[]{"INFO",getAppName(),getLocalIP(),logPointFrame.getClassName(),logPointFrame.getMethodName(),String.valueOf(logPointFrame.getLineNumber())};

        this.logger.info(msg + " [" + gennerateLogTitle(titleInfoArr) + "]");
    }

    public void error(String msg){
        final StackTraceElement logPointFrame = Thread.currentThread().getStackTrace()[2];

        String[] titleInfoArr = new String[]{"ERROR",getAppName(),getLocalIP(),logPointFrame.getClassName(),logPointFrame.getMethodName(),String.valueOf(logPointFrame.getLineNumber())};

        this.logger.error(msg + " [" + gennerateLogTitle(titleInfoArr) + "]");
    }

    public void trace(String msg){
        final StackTraceElement logPointFrame = Thread.currentThread().getStackTrace()[2];

        String[] titleInfoArr = new String[]{"TRACE",getAppName(),getLocalIP(),logPointFrame.getClassName(),logPointFrame.getMethodName(),String.valueOf(logPointFrame.getLineNumber())};

        this.logger.trace(msg + " [" + gennerateLogTitle(titleInfoArr) + "]");
    }

    public void warn(String msg){
        final StackTraceElement logPointFrame = Thread.currentThread().getStackTrace()[2];

        String[] titleInfoArr = new String[]{"WARN",getAppName(),getLocalIP(),logPointFrame.getClassName(),logPointFrame.getMethodName(),String.valueOf(logPointFrame.getLineNumber())};

        this.logger.warn(msg + " [" + gennerateLogTitle(titleInfoArr) + "]");

    }



    private String gennerateLogTitle(String[] args){
//        return String.join(" ",args);
        return null;
    }


    public static String getLocalIP(){
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "0.0.0.0";
    }

    public static String getAppName(){
        return "AppName";
    }


}
