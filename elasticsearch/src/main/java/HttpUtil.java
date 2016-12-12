import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Slf4j
public class HttpUtil {


	private static final int CONN_TIME_OUT = 1200;

	private static final int SOCKET_TIME_OUT = 1200;

	/**
	 * 发送http get请求 (默认5s超时，改变超时时间可使用重载方法)
	 * 
	 * @param url
	 * @return
	 */
	public static String doGet(String url) {
		return doGet(url, SOCKET_TIME_OUT);
	}

	/**
	 * 发送http get请求
	 * 
	 * @param url
	 * @param timeout
	 *            超时时间，单位毫秒
	 * @return
	 */
	public static String doGet(String url, int timeout) {
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, CONN_TIME_OUT);
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
			log.warn("HTTP GET URL[{}] ERROR", url);
		}

		log.info("HTTP GET URL[{}] with result[{}]. ElapseTime:[{}]", url, resp, System.currentTimeMillis() - start);

		if(System.currentTimeMillis() - start > 3000){
			log.warn("HTTP GET URL[{}] TOOK TOO MUCH TIME[{}]",url,System.currentTimeMillis() - start);
		}
		return resp;
	}

	public static String doGet(String url,Map<String, String> params,int timeOut){
		StringBuilder sb = new StringBuilder(url);
		sb.append("?");
		for(String key : params.keySet()){
			sb.append(key).append("=").append(params.get(key)).append("&");
		}
		sb.deleteCharAt(sb.length() - 1);
		return doGet(sb.toString(),timeOut);
	}

	public static String doPost(String url, Map<String, String> params) {
		return doPost(url, 5000, params);
	}

	public static String doPost(String url, Map<String, String> params,int timeOut) {
		return doPost(url, timeOut, params);
	}


	/**
	 * 发送http post 请求
	 * @param url 请求地址
	 * @param timeout 超时时间, 单位为毫秒
	 * @param params post 参数
	 * @return 接口返回的文本信息
	 */
	private static String doPost(String url, int timeout, Map<String, String> params) {
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, CONN_TIME_OUT);
		HttpConnectionParams.setSoTimeout(httpParameters, timeout);

		// 构造HttpClient的实例
		HttpClient httpClient = new DefaultHttpClient(httpParameters);
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");

		List<NameValuePair> nvps = new ArrayList<>();
		Set<String> keySet = params.keySet();
		StringBuilder postParams = new StringBuilder();
		for(String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
			postParams.append(key).append("=").append(params.get(key)).append("&");
		}

		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		long start = System.currentTimeMillis();
		String resp = null;
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				resp = EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (Exception e) {
			log.warn("call url error,{},{}", url,e);
		}

		log.info("Post URL[{}] params[{}]with result[{}]. time:[{}]", url,postParams.toString(), resp,System.currentTimeMillis() - start);
		return resp;
	}

	public static String doPostJson(String url, int timeout, String jsonString) {
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, CONN_TIME_OUT);
		HttpConnectionParams.setSoTimeout(httpParameters, timeout);

		// 构造HttpClient的实例
		HttpClient httpClient = new DefaultHttpClient(httpParameters);
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, " application/json;charset=utf-8");


		httpPost.setEntity(new StringEntity(jsonString, Consts.UTF_8));

		long start = System.currentTimeMillis();

		String resp = null;
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				resp = EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (Exception e) {
			log.warn("call url error,{},{}", url,e);
		}

		log.info("PostJson URL[{}] params[{}]with result[{}]. time:[{}]", url,jsonString,resp,System.currentTimeMillis() - start);
		return resp;
	}

	public static String doPostPlainText(String url, int timeout, String postData) {
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, CONN_TIME_OUT);
		HttpConnectionParams.setSoTimeout(httpParameters, timeout);

		// 构造HttpClient的实例
		HttpClient httpClient = new DefaultHttpClient(httpParameters);
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");


		httpPost.setEntity(new StringEntity(postData, Consts.UTF_8));

		long start = System.currentTimeMillis();

		String resp = null;
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
			System.out.println(httpResponse.getStatusLine().getStatusCode());
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				resp = EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (Exception e) {
			log.warn("call url error,{},{}", url,e);
		}

		log.info("PostJson URL[{}] params[{}]with result[{}]. time:[{}]", url,postData,resp,System.currentTimeMillis() - start);
		return resp;
	}

}