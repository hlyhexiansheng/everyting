import java.util.concurrent.CountDownLatch;

/**
 * Created by noodles on 16/11/23 上午9:53.
 */
public class QueryEs {

    public static void main(String[] args) throws InterruptedException {

        final long l = System.currentTimeMillis();

        String[] data = new String[5];

        data[0] = "{\"query\":{\"bool\":{\"must\":[{\"prefix\":{\"title\":\"com.gb.service.\"}},{\"range\":{\"time\":{\"gte\":\"1479865521\",\"lte\":\"1479865523\"}}},{\"term\":{\"level\":\"info\"}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":2,\"sort\":[],\"aggs\":{}}";

        data[1] = "{\"query\":{\"bool\":{\"must\":[{\"prefix\":{\"title\":\"com.gb.service.\"}},{\"range\":{\"time\":{\"gte\":\"1479865521\",\"lte\":\"1479865523\"}}},{\"term\":{\"level\":\"error\"}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":2,\"sort\":[],\"aggs\":{}}";

        data[2] = "{\"query\":{\"bool\":{\"must\":[{\"prefix\":{\"title\":\"com.gb.service.\"}},{\"range\":{\"time\":{\"gte\":\"1479865521\",\"lte\":\"1479865523\"}}},{\"term\":{\"level\":\"warn\"}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":2,\"sort\":[],\"aggs\":{}}";

        data[3] = "{\"query\":{\"bool\":{\"must\":[{\"prefix\":{\"title\":\"com.gb.service.OrderService\"}},{\"range\":{\"time\":{\"gte\":\"1479865521\",\"lte\":\"1479865523\"}}},{\"term\":{\"level\":\"warn\"}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":2,\"sort\":[],\"aggs\":{}}";

        data[4] = "{\"query\":{\"bool\":{\"must\":[{\"prefix\":{\"title\":\"com.gb.service.AccountService\"}},{\"range\":{\"time\":{\"gte\":\"1479865521\",\"lte\":\"1479865523\"}}},{\"term\":{\"level\":\"warn\"}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":2,\"sort\":[],\"aggs\":{}}";


        class QueryCommand implements Runnable {
            private String query;
            private CountDownLatch latch;

            QueryCommand(String query, CountDownLatch latch) {
                this.query = query;
                this.latch = latch;
            }

            @Override
            public void run() {
                try {
                    final String s1 = HttpUtil.doPostJson("http://10.40.6.118:9200/log/_search", 10000, this.query);
                    System.out.println(Thread.currentThread().getName() +  "--" + s1);
                }finally {
                    this.latch.countDown();
                }

            }
        }

        int count = 300;
        CountDownLatch latch = new CountDownLatch(count);

        Thread[] threads = new Thread[count];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new QueryCommand(data[i % data.length],latch));
            threads[i].setName("tt-" + i);
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        latch.await();

        System.out.println("cost time:" + String.valueOf(System.currentTimeMillis() - l));

    }
}
