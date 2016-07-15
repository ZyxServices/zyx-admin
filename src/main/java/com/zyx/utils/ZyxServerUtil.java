package com.zyx.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by MrDeng on 2016/7/12.
 */
public class ZyxServerUtil {
    static  CloseableHttpClient client = HttpClients.createDefault();
    private static String IP_ADDRESS = "http://211.157.152.200:8089/";
    public static String doPost(String path,Map<String,Object> args) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            client = HttpClients.createDefault();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            if(args!=null&&!args.isEmpty()){
                Set<String> keys = args.keySet();
                for (String key :keys ){
                    params.add(new BasicNameValuePair(key,String.valueOf(args.get(key))));
                }
            }
            UrlEncodedFormEntity httpEntity = new UrlEncodedFormEntity( params, "UTF-8");
            HttpPost httpPost = new HttpPost(IP_ADDRESS+path);
            httpPost.setEntity(httpEntity );
            CloseableHttpResponse response = client.execute(httpPost);
            System.out.println(EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(client!=null)
                client.close();
        }
        return null;
    }
    public static String doPost(String path) throws IOException {
        return doPost(path,null);
    }

    public static void main(String[] args) {
        try {
            ZyxServerUtil.doPost("/v1/live/get");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
