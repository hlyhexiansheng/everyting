package asynhttp;

import com.alibaba.fastjson.JSONObject;
import com.ning.http.client.*;
import lombok.extern.log4j.Log4j;

import java.io.*;
import java.lang.reflect.WildcardType;
import java.net.Socket;
import java.util.concurrent.Future;

/**
 * Created by noodles on 16/3/12.
 */

@Log4j
public class AsynHttpUitl {

    public static final int REQUEST_TIMEOUT = 5000;

    public static Future<String> doAsynHttpGet(String url){
        return doAsynHttpGet(url,REQUEST_TIMEOUT);
    }

    public static Future<String> doAsynHttpGet(final String url, int requestTimeout) {
        AsyncHttpClient asyncHttpClient = buildClient(requestTimeout);
        ListenableFuture<String> future = asyncHttpClient.prepareGet(url).execute(new AsyncCompletionHandler<String>() {
            @Override
            public String onCompleted(Response response) throws Exception {
                System.out.println("---------");
                return response.getResponseBody();
            }
            @Override
            public void onThrowable(Throwable t) {
                System.out.println("onThrowable");
                log.error("doAsynHttpGet catch exception-->" + url);
            }
        });
        return future;
    }

    public static Future<Response> doAsynHttpRequest(final String url){
        return doAsynHttpRequest(url,REQUEST_TIMEOUT);
    }

    public static void main(String[] agrs){
        String url = "http://localhost:9696/testServlet";
//        sendPostRequestJson(url);
//        sendPostReqeustWithForm(url);
        socketRequest();
    }

    public static void sendPostRequestJson(final String url){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key1","value1");
        jsonObject.put("key2","value2");
        jsonObject.put("key3","value3");
        String s = jsonObject.toJSONString();

        AsyncHttpClient asyncHttpClient = buildClient(REQUEST_TIMEOUT);
        asyncHttpClient.preparePost(url).setHeader("Content-type","application/json").setBody(s).execute(new AsyncCompletionHandler<Object>() {
            @Override
            public Object onCompleted(Response response) throws Exception {
                String responseBody = response.getResponseBody();
                System.out.println(responseBody);
                return null;
            }
        });
    }

    public static void socketRequest(){
        try {
//            String url = "test.api.v.eaglive.com";
            String url = "127.0.0.1";
            Socket socket = new Socket(url,9696);
            StringBuffer sb = new StringBuffer();
            sb.append("GET /testServlet?cmd=livestreamupload HTTP/1.1\r\n");
            sb.append("Host: localhost:8088\r\n");
            sb.append("Connection: Keep-Alive\r\n");
            sb.append("\r\n");
            socket.getOutputStream().write(sb.toString().getBytes());
            socket.getOutputStream().flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String tempString;
            while ((tempString = reader.readLine()) != null){
                stringBuilder.append(tempString);
            }
            System.out.println(stringBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendPostReqeustWithForm(final String url){
        AsyncHttpClient asyncHttpClient = buildClient(REQUEST_TIMEOUT);
        asyncHttpClient.preparePost(url)
                .addFormParam("formKey1","formValue1")
                .addFormParam("formKey2","formValue2")
                .addFormParam("formKey3","formValue3").execute();
    }


    public static Future<Response> doAsynHttpRequest(final String url, int reqeustTimeout){
        AsyncHttpClient asyncHttpClient = buildClient(reqeustTimeout);
       return asyncHttpClient.prepareGet(url).execute();
    }

    private static AsyncHttpClient buildClient(int requestTimeout){
        return new AsyncHttpClient(new AsyncHttpClientConfig.Builder().setRequestTimeout(requestTimeout).build());
    }
}
