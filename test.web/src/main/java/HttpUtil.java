import com.alibaba.fastjson.JSON;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by noodles on 16/12/9 上午10:54.
 */
@Slf4j
public class HttpUtil {

    private static final int CONN_TIME_OUT = 2000;

    private static final int SOCKET_TIME_OUT = 5000;

    private static String ContentType = "application/x-www-form-urlencoded;charset=utf-8";


    public static void main(String[] args) {

        final String postBody = buildRequest();

        final String s = doPost("http://10.4.4.203", SOCKET_TIME_OUT, postBody);

        System.out.println(s);
    }

    public static String buildRequest() {
        //1.设置参数
        Map<String, String> req = new HashMap<>();
        req.put("method", "com.order.query.query_order");
        req.put("app_key", "gateway");
        req.put("module", "pdm");
        req.put("timestamp", "2018-01-09 07:27:45");

        //2.按照参数的key排序
        final String[] keys = req.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        //3.拼接待签名字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            sb.append(keys[i]).append(req.get(keys[i]));
        }
        //3.在带签名字符串追加签名密钥
        sb.append("3e21ab62fb17400301d9f0156b6c3031");

        //4.对字符串执行 Md5签名
        String sign = Hashing.md5().hashString(sb.toString(), Charset.forName("UTF-8")).toString();

        //5.把签名值，放入map中
        req.put("sign", sign);

        //6.把最终结果拼成HTTP body字符串.
        sb = new StringBuilder();
        for (String key : req.keySet()) {
            sb.append(key).append("=").append(req.get(key)).append("&");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }




    public static String doPost(String url, int timeout, String postBody) {
        return doExecutePost(url, timeout, postBody, ContentType);
    }

    private static String doExecutePost(String url, int timeout, String postBody, String contentType) {

        final CloseableHttpClient client = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        final RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout)
                .setConnectTimeout(CONN_TIME_OUT).build();


        httpPost.setConfig(requestConfig);

        httpPost.addHeader(HTTP.CONTENT_TYPE, contentType);

        httpPost.setEntity(new StringEntity(postBody, Consts.UTF_8));

        long start = System.currentTimeMillis();

        String resp = null;
        try {
            HttpResponse httpResponse = client.execute(httpPost);
            resp = EntityUtils.toString(httpResponse.getEntity());

        } catch (Exception e) {
            log.warn("call url error,{},{}", url, e.getMessage());
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                log.error("close socket error,{},{}", url, e.getMessage());
            }
        }

        log.info("Post URL[{}] params[{}]]. time:[{}]", url, postBody, System.currentTimeMillis() - start);

        return resp;
    }
}
