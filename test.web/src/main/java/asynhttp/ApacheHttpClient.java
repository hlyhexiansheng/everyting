package asynhttp;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.CountDownLatch;

/**
 * Created by noodles on 16/3/12.
 */
@Slf4j
public class ApacheHttpClient {

    public static String doGet(String url, int timeout) {
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeout);
        HttpConnectionParams.setSoTimeout(httpParameters, timeout);

        // 构造HttpClient的实例
        HttpClient httpClient = new DefaultHttpClient(httpParameters);
        // 创建GET方法的实例
        HttpGet httpGet = new HttpGet(url);
        long start = System.currentTimeMillis();
        String resp = null; // 返回信息
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            // SC_OK = 200
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 获得返回结果
                resp = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (Exception e) {
            log.error("call url error", e);
        }
        log.info(String.format("Request URL[%s] with result[%s]. time:[%d]", url, resp,
                System.currentTimeMillis() - start));
        return resp;
    }


}
