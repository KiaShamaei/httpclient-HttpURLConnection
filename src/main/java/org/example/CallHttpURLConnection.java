package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CallHttpURLConnection {
    public static void main(String[] args) {
        get();

    }
    public static void get(){
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts/1");
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            var resCode = c.getResponseCode();
            System.out.println("res code : " + resCode);
            BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            c.disconnect();
            // Parse the JSON string into a Java object using Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            //you can add your model insist of Object.class
            Object dataObject = objectMapper.readValue(response.toString(), Object.class);

            System.out.println(response.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
