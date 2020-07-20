package com.dm.utils.http;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hu.yuhao
 * post send，get send
 * */
public class HttpUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static String postRequest(String url, String params) {
        StringBuilder result = new StringBuilder();
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        InputStreamReader streamReader = null;
        BufferedReader reader = null;
        try {
            URL uri = new URL(url);
            httpURLConnection = (HttpURLConnection)uri.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(90000);
            httpURLConnection.setReadTimeout(60000);
            httpURLConnection.setUseCaches(false);

            //必要时添加浏览器模拟参数
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            //产生http头部信息，参数要在此之前设定好
            httpURLConnection.connect();

            //写入参数
            outputStream = httpURLConnection.getOutputStream();
            outputStream.write(params.getBytes("utf-8"));
            outputStream.flush();

            //真正发送http请求
            inputStream = httpURLConnection.getInputStream();
            streamReader = new InputStreamReader(inputStream, "utf-8");
            reader = new BufferedReader(streamReader);

            String line = "";
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (streamReader != null) {
                    streamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "huyuhao test post request.");
        //String s = JSONObject.toJSONString(map);
        //System.out.println(s);
        String result = postRequest("http://127.0.0.1:8090/test/test/order", "2");
        //logger.info(result);
        System.out.println(result);
    }
}
