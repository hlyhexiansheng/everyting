package asynhttp;

import com.ning.http.client.*;
import com.ning.http.client.multipart.StringPart;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by noodles on 16/3/11.
 */
public class AsynHttpTest {

    private final static int RequestTime = 200;

    @Test
    public void test(){
        Future<String> future = AsynHttpUitl.doAsynHttpGet("http://localhost:9696/testServlet");
    }

    public static void main(String[] args) throws InterruptedException {
//        synHttp();
//        asynHttp();
//        testSynTimeOut();

//        testAsynTimeOut();

//        testAsynHttpUitl();

//        testAsynHttpUitl2();

        testPostMethod();
    }

    private static void testPostMethod() {
        String s = ApacheHttpClient.doGet("http://www.baidu.com", 2000);
        System.out.println(s);


        AsyncHttpClient asyncHttpClient = new AsyncHttpClient("com.ning.http.client.providers.jdk.JDKAsyncHttpProvider",new AsyncHttpClientConfig.Builder().setRequestTimeout(5000).build());
        ListenableFuture<Response> execute = asyncHttpClient.preparePost("http://localhost:9696/testServlet")
                .addQueryParam("key1", "valeu1").addFormParam("formParams","formParams..").addBodyPart(new StringPart("bodiiiiiiiy","body context........."))
                .execute();
        try {
            System.out.println(execute.get().getResponseBody());
        } catch (IOException e) {

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    private static void testAsynHttpUitl2() {

        Future<String> future = AsynHttpUitl.doAsynHttpGet("http://localhost:9696/testServlet");


    }

    private static void testAsynHttpUitl() {
        List<Future<String>> futures = new ArrayList<Future<String>>();

        for(int i = 0; i < 30;i++){
            futures.add(AsynHttpUitl.doAsynHttpGet("http://localhost:9696/testServlet"));
        }
        System.out.println("--------------");
        for (Future<String> future : futures){
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private static void testAsynTimeOut() {
        AsyncHttpClientConfig config = new AsyncHttpClientConfig.Builder().setRequestTimeout(5000).build();
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient(config);
        asyncHttpClient.prepareGet("http://localhost:9696/testServlet").execute(new AsyncHandler<Object>() {
            @Override
            public void onThrowable(Throwable t) {
                System.out.println("onThrowable" + t);
            }

            @Override
            public STATE onBodyPartReceived(HttpResponseBodyPart bodyPart) throws Exception {
                System.out.println("onBodyPartReceived");
                return null;
            }

            @Override
            public STATE onStatusReceived(HttpResponseStatus responseStatus) throws Exception {
                System.out.println("onStatusReceived");
                return null;
            }

            @Override
            public STATE onHeadersReceived(HttpResponseHeaders headers) throws Exception {
                System.out.println("onHeadersReceived");
                return null;
            }

            @Override
            public Object onCompleted() throws Exception {
                System.out.println("onCompleted");
                return null;
            }
        });

    }

    private static void testSynTimeOut() {
        String rspn = null;
        try {
            rspn = ApacheHttpClient.doGet("http://localhost:9696/testServlet", 5000);
        } catch (Exception e) {
            System.out.println("异常来了");
        }

        System.out.println("finish.........." + rspn);
    }

    private static void synHttp() {


        long l = System.currentTimeMillis();

        for (int i = 0; i < RequestTime; i++){
            String s = ApacheHttpClient.doGet("http://api.eaglive.com/api/?cmd=gettime", 1000);
        }

        System.out.println("同步IO耗时:--" + (System.currentTimeMillis() - l));


    }

    private static void asynHttp() throws InterruptedException {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        CountDownLatch countDownLatch = new CountDownLatch(100);
        long l = System.currentTimeMillis();

        for (int i = 0 ; i < RequestTime;i++){
            asyncHttpClient.prepareGet("http://api.eaglive.com/api/?cmd=gettime").execute(new CustomHandler("name" + i,countDownLatch));
        }
        countDownLatch.await();
        System.out.println("异步IO耗时:--" + (System.currentTimeMillis() - l));
    }

}

class CustomHandler extends AsyncCompletionHandler<Response>{

    private CountDownLatch countDownLatch;

    public CustomHandler(String name,CountDownLatch countDownLatch){
        this.name = name;
        this.countDownLatch = countDownLatch;
    }

    private String name;

    @Override
    public Response onCompleted(Response response) throws Exception {

        //System.out.println(response.getResponseBody());
        System.out.println(name);
        this.countDownLatch.countDown();
        return null;
    }

    @Override
    public void onThrowable(Throwable t) {
        this.countDownLatch.countDown();
    }
}
