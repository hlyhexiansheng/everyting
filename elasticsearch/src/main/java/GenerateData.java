import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.PrintWriter;
import java.util.Random;
import java.util.UUID;

/**
 * Created by noodles on 16/11/22 下午6:14.
 */
public class GenerateData {

    public static void main(String[] args) throws Exception {


        int count = 10000 * 1000;
        if(args.length > 0){
            count = Integer.valueOf(args[0]);
        }

        PrintWriter printWriter = new PrintWriter("/root/heliyu/WEB-INF/logdata.json");
        String[] levels = new String[]{"info", "warn", "error"};
        String[] metrics = new String[]{"com.gb.service.AccountService.fakeMethod.11"
                , "com.gb.service.AccountService.fuck.11"
                , "com.gb.service.OrderService.aaa.11"
                , "com.gb.service.OrderService.bb.11"};



        long startTime = System.currentTimeMillis() / 1000;
        int delta = 0;
        for (int i = 0; i < count; i++) {
            int random = i + new Random().nextInt(10000);

            long unixTimestap = startTime;
            if (i % 3000 == 0) {
                unixTimestap = unixTimestap + delta;
                delta++;
            }


            String level = levels[random % levels.length];
            String metric = metrics[random % metrics.length];

            MetricsLog log = new MetricsLog();
            log.setLevel(level);
            log.setTime(unixTimestap);
            log.setTitle(metric);
            log.setMessage("this is info message");
            log.setFromIp("127.0.0.1");

            String _id = UUID.randomUUID().toString();
            printWriter.append("{\"index\":{\"_id\":\""+ _id + "\"}}").append("\n");
            printWriter.append(JSON.toJSONString(log)).append("\n");

            if (i % 500 == 0) {
                printWriter.flush();
            }
        }
        printWriter.flush();
    }
}

