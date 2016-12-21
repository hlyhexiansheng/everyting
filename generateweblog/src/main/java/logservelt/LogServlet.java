package logservelt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ReusableMessageFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Cmreated by noodles on 16/12/5 上午10:50.
 */
public class LogServlet extends HttpServlet{

    public static final Logger logger = LogManager.getLogger(LogServlet.class);
////public static final Logger logger = Logger.getLogger(LogServlet.class);
//    public static final Logger logger = LoggerFactory.getLogger(LogServlet.class);
    @Override
    public void init() throws ServletException {

//        System.out.println(logger.getName());
//        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            public void run() {
//                logger.warn("warn log");
//                System.out.println("----------------");
//                logger.warn("warn log,{},{}",1,"1212");
//                System.out.println("----------------");
//                logger.info("Info log",new RuntimeException("Exception"));
//                System.out.println("----------------");
//                logger.error("error log");
//                System.out.println("----------------");
//            }
//        },1,3, TimeUnit.SECONDS);

        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final ServletInputStream inputStream = req.getInputStream();

        byte[] bytes = new byte[10000];
        final int read = inputStream.read(bytes);

        byte[] newbyte = new byte[read];
        for(int i = 0; i < read;i++){
            newbyte[i] = bytes[i];
        }


        System.out.println(new String(newbyte));


        super.doPost(req, resp);
    }


}
