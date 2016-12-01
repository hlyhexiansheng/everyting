import java.util.Random;

/**
 * Created by noodles on 16/11/22 下午5:26.
 */
public class PushData2Es {



    public static void main(String[] args) {
        createIndex();
//        pushData();
    }

    private static void createIndex() {
        String postData = "{\n" +
                "  \"mappings\": {\n" +
                "    \"app_1\": {\n" +
                "      \"properties\": {\n" +
                "        \"level\": {\n" +
                "          \"type\":       \"string\",\n" +
                "          \"index\": \"not_analyzed\"\n" +
                "        },\n" +
                "        \"time\": {\n" +
                "          \"type\":       \"long\"\n" +
                "        },\n" +
                "        \"title\":{\n" +
                "           \"type\": \"string\",\n" +
                "           \"index\": \"not_analyzed\"\n" +
                "        },\n" +
                "        \"message\":{\n" +
                "           \"type\": \"string\"\n" +
                "        },\n" +
                "        \"fromIp\":{\n" +
                "           \"type\":\"string\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        final String rspn = HttpUtil.doPostJson("http://10.40.6.118:9200/log", 2000, postData);
        System.out.println(rspn);
    }

    private static void pushData() {


        final long startTime = System.currentTimeMillis();

        String[] levels = new String[]{"info", "warn", "error"};
        String[] metrics = new String[]{"com.gb.service.AccountService.fakeMethod.11"
                , "com.gb.service.AccountService.fuck.11"
                , "com.gb.service.OrderService.aaa.11"
                , "com.gb.service.OrderService.bb.11"};
        for (int i = 0; i < 10000; i++) {
            int random = i + new Random().nextInt(10000);
            long unixTimestap = System.currentTimeMillis() / 1000;
            String level = levels[random % levels.length];
            String metric = metrics[random % metrics.length];

            String data = "{\n" +
                    "    \"level\" : \""+level+"\",\n" +
                    "    \"time\" : " + unixTimestap + ",\n" +
                    "    \"title\" : \"" +metric+ "\",\n" +
                    "    \"message\":\"this is a info message\",\n" +
                    "    \"fromIp\":\"127.0.0.1\"\n" +
                    "}";

            final String rspn = HttpUtil.doPostJson("http://10.40.6.118:9200/log/app_1", 2000, data);
            System.out.println(rspn);
        }

        System.out.println("cost time:" + String.valueOf(System.currentTimeMillis() - startTime));


    }

}
