package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CallWithHttpClient {
    public static void main(String[] args) {
        get();
    }
    public static void get(){
        //first set timeOut config
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(35000)
                .setSocketTimeout(35000)
                .build();
        SocketConfig socketConfig = SocketConfig.custom()
                .setSoTimeout(35000)
                .build();
        //add url for get method use HttpGet for post use HttpPost
        final String uri = "https://jsonplaceholder.typicode.com/posts/1";
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setDefaultSocketConfig(socketConfig)
                .build();
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            //this make status code
            StatusLine statusLine = response.getStatusLine();
            //this make response string
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            //to mapping you can use this
            // Parse the JSON string into a Java object using Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            //you can add your model insist of Object.class
            Object dataObject = objectMapper.readValue(responseBody, Object.class);
            System.out.println(statusLine.getStatusCode());
            System.out.println(responseBody.toString());
            System.out.println(dataObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
