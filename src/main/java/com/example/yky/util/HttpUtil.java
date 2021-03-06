package com.example.yky.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.example.yky.dao.HttpCallbackListener;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
	
	    //封装的发送请求函数
	    public static void sendHttpRequest(final String address, final HttpCallbackListener httpCallbackListener) {
	        if (!HttpUtil.isNetworkAvailable()){
	            //这里写相应的网络设置处理
	            return;
	        }
	        new Thread(new Runnable() {
	            @Override
	            public void run() {
	                HttpURLConnection connection = null;
	                try{
	                    URL url = new URL(address);
	                    //使用HttpURLConnection
	                    connection = (HttpURLConnection) url.openConnection();
	                    //设置方法和参数
	                    connection.setRequestMethod("GET");
	                    connection.setConnectTimeout(8000);
	                    connection.setReadTimeout(8000);
	                    connection.setDoInput(true);
	                    connection.setDoOutput(true);
	                    //获取返回结果
	                    InputStream inputStream = connection.getInputStream();
	                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	                    StringBuilder response = new StringBuilder();
	                    String line;
	                    while ((line = reader.readLine()) != null){
	                        response.append(line);
	                    }
	                    //成功则回调onFinish
	                    if (httpCallbackListener != null){
	                        httpCallbackListener.onFinish(response.toString());
	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    //出现异常则回调onError
	                    if (httpCallbackListener != null){
	                        httpCallbackListener.onError(e);
	                    }
	                }finally {
	                    if (connection != null){
	                        connection.disconnect();
	                    }
	                }
	            }
	        }).start();
	    }

	    //组装出带参数的完整URL
	    public static String getURLWithParams(String address,HashMap<String,String> params) throws UnsupportedEncodingException {
	        //设置编码
	        final String encode = "UTF-8";
	        StringBuilder url = new StringBuilder(address);
	        url.append("?");
	        //将map中的key，value构造进入URL中
	        for(Map.Entry<String, String> entry:params.entrySet())
	        {
	            url.append(entry.getKey()).append("=");
	            url.append(URLEncoder.encode(entry.getValue(), encode));
	            url.append("&");
	        }
	        //删掉最后一个&
	        url.deleteCharAt(url.length() - 1);
	        return url.toString();
	    }

	    //判断当前网络是否可用
	    public static boolean isNetworkAvailable(){
	        //这里检查网络，后续再添加
	        return true;
	    }
	    public static void sendOkHttpRequest(String address, okhttp3.Callback callback){
	        OkHttpClient client = new OkHttpClient();
	        Request request = new Request.Builder()
	                .url(address).build();
	        client.newCall(request).enqueue(callback);
	    }
}
