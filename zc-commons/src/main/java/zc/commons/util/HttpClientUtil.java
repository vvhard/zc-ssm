package zc.commons.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpRequest;

public class HttpClientUtil {
    private static PoolingHttpClientConnectionManager cm; // 连接池管理器
    private static String EMPTY_STR = "";
    private static String UTF_8 = "UTF-8";

    /**
     * 初始化
     */
    private static void init() {
        if (cm == null) {
            cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(50);// 整个连接池最大连接数
            cm.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2
        }
    }

    /**
     * 通过连接池获取HttpClient
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        init();
        return HttpClients.custom().setConnectionManager(cm).build();
    }

    /**
     * GeT请求，请求url
     * @param url
     * @return
     */
    public static String httpGetRequest(String url) {
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet);
    }

    /**
     * get请求，带请求参数
     * @param url
     * @param params Map类型，存放请求参数
     * @return
     * @throws URISyntaxException
     */
    public static String httpGetRequest(String url, Map<String, Object> params) throws URISyntaxException {
        URIBuilder ub = new URIBuilder(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs); // 设置参数
        HttpGet httpGet = new HttpGet(ub.build()); // 创建请求
        return getResult(httpGet); // 返回结果
    }

    /**
     * get请求，带头部信息以及参数
     * @param url
     * @param headers
     * @param params
     * @return
     * @throws URISyntaxException
     */
    public static String httpGetRequest(String url, Map<String, Object> headers, Map<String, Object> params)
            throws URISyntaxException {
        URIBuilder ub = new URIBuilder(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);  // 转换成键值对
        ub.setParameters(pairs);
        HttpGet httpGet = new HttpGet(ub.build());
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        return getResult(httpGet);
    }

    /**
     * pos请求
     * @param url
     * @return
     */
    public static String httpPostRequest(String url) {
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url, Map<String, Object> params) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url, Map<String, Object> headers, Map<String, Object> params)
            throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        return getResult(httpPost);
    }

    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }

        return pairs;
    }

    /**
     * 处理Http请求，返回结果，字符串类型
     *
     * @param request
     * @return
     */
    private static String getResult(HttpRequestBase request) {
        CloseableHttpClient httpClient = getHttpClient();
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            // response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // long len = entity.getContentLength();// -1 表示长度未知
                String result = EntityUtils.toString(entity); // 转换成字符串
                response.close();
                // httpClient.close();
                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

        return EMPTY_STR;
    }

}