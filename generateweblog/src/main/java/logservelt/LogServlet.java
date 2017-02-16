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
import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Cmreated by noodles on 16/12/5 上午10:50.
 */
public class LogServlet extends HttpServlet {

    public static final Logger logger = LogManager.getLogger(LogServlet.class);
    ////public static final Logger logger = Logger.getLogger(LogServlet.class);
//    public static final Logger logger = LoggerFactory.getLogger(LogServlet.class);
    private static long logId = 0;

    @Override
    public void init() throws ServletException {

        final InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");

        ConfigReader.instance.loadConfig(resourceAsStream);

        int speed = ConfigReader.instance.getIntValue("speed", 1000);

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                logger.error("error log [{}]", logId++);
            }
        }, 1, speed, TimeUnit.MILLISECONDS);

        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        final Map parameterMap = req.getParameterMap();
//        final Set set = parameterMap.keySet();
//
//        for(Object o : set){
//            logger.info("key=" + o.toString() + ",value=" + parameterMap.get(o).toString());
//        }

        logger.info("eeeeee");
        resp.getWriter().write("hello,fuck");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
