package util;

import com.google.gson.Gson;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {

    private Gson gson = new Gson();


    @Test
    public void doGet() throws Exception {
        String url = "";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // 设置请求头
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        if(responseCode != 200){
            throw new RuntimeException(con.getResponseMessage());
        }


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // 打印响应结果
        System.out.println("响应结果为 : " + response);
    }

    @Test
    public void doPost() throws Exception {

        URL url = new URL("http://example.com/api");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置请求方法为POST
        connection.setRequestMethod("POST");

        // 添加请求头信息
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer your_token_here");

        // 启用输出流
        connection.setDoOutput(true);

        // 添加请求体
        Map<String, String> param = new HashMap<>();
        param.put("param1", "");
        param.put("param2", "");
        String jsonInputString = gson.toJson(param);
        byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
        connection.getOutputStream().write(input, 0, input.length);

        // 发送请求
        int responseCode = connection.getResponseCode();
        if(responseCode != 200){
            throw new RuntimeException(connection.getResponseMessage());
        }

        // 读取响应
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        // 打印响应结果
        System.out.println(response);

    }
}
