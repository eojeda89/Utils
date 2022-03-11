package com.example.demo;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class ControllerDemo {

    @PostMapping("users")
    public ResponseEntity<List<String>> createBatchUser(@RequestBody PostObject object) {
        List<String> response = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("x-access-token", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBwZ3dmLmNvbSIsImV4cCI6MTY0NzAyMjcyMH0.p8X7CV01nHutzDI3BQy3RVkqeMCrmb5QvDaHG7Pyo1v_up6Y-FmOsFQlP6QqRRkqz0ijjAvAgqbcckf2glxxAQ");
        JSONObject req = new JSONObject();
        String transUri = "https://pecat.pangeamt.com:8443/pecatv1/users";
        String result = "";
        List<String> roles = new ArrayList<>();
        roles.add("Translator");
        List<String> source = new ArrayList<>();
        List<String> target = new ArrayList<>();
        String[] sT = object.getName().split("_");
        source.add(sT[0]);
        target.add(sT[1]);
        List<Integer> groups = new ArrayList<>();
        groups.add(15);


        try {
            for (int i = 1; i <= 80; i++) {
                String name = "Alisa_" + object.getName() + "_" + i;
                UserModel userModel = new UserModel();
                userModel.setNickname(name);
                userModel.setEmail("alisa_" + object.getName() + "_" + i + "@pecat.com");
                userModel.setRoles(roles);
                userModel.setPassword(name);
                userModel.setSource(source);
                userModel.setTarget(target);
                userModel.setStatus(1);
                userModel.setEval(1);
                userModel.setGroups(groups);

                HttpEntity<UserModel> request = new HttpEntity<>(userModel, headers);
                ResponseEntity<String> responseEntity = restTemplate.exchange(transUri, HttpMethod.POST, request, String.class);
                result = responseEntity.getBody();
                System.out.println(result);
                response.add(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("users/ssl")
    public ResponseEntity<List<String>> createBatchUserWithSSL(@RequestBody PostObject object) {
        List<String> response = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("x-access-token", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBwZ3dmLmNvbSIsImV4cCI6MTY0NzA5MjM4Mn0.yLDta0Z-KMgfUyq1P1oFvORNwHqo0x6let7dYO0kvDDxTkWTYKEQy4bmVKO0jN5tHxxNamf8WB78QF2o9mNd0g");
        JSONObject req = new JSONObject();
        String transUri = "https://pecat.pangeamt.com:8443/pecatv1/users";
        String result = "";
        List<String> roles = new ArrayList<>();
        roles.add("Translator");
        List<String> source = new ArrayList<>();
        List<String> target = new ArrayList<>();
        String[] sT = object.getName().split("_");
        source.add(sT[0]);
        target.add(sT[1]);
        List<Integer> groups = new ArrayList<>();
        groups.add(16);

        try {
            TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

            Registry<ConnectionSocketFactory> socketFactoryRegistry =
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("https", sslsf)
                            .register("http", new PlainConnectionSocketFactory())
                            .build();

            BasicHttpClientConnectionManager connectionManager =
                    new BasicHttpClientConnectionManager(socketFactoryRegistry);
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
                    .setConnectionManager(connectionManager).build();

            HttpComponentsClientHttpRequestFactory requestFactory =
                    new HttpComponentsClientHttpRequestFactory(httpClient);
            RestTemplate restTemplate = new RestTemplate(requestFactory);

            for (int i = 1; i <= object.getNumberUser(); i++) {
                String name = object.getSuffix() + "_" + object.getName() + "_" + i;
                UserModel userModel = new UserModel();
                userModel.setNickname(name);
                userModel.setEmail(object.getSuffix().toLowerCase() + "_" + object.getName() + "_" + i + "@pecat.com");
                userModel.setRoles(roles);
                userModel.setPassword(name);
                userModel.setSource(source);
                userModel.setTarget(target);
                userModel.setStatus(1);
                userModel.setEval(1);
                userModel.setGroups(groups);

                HttpEntity<UserModel> request = new HttpEntity<>(userModel, headers);
                ResponseEntity<String> responseEntity = restTemplate.exchange(transUri, HttpMethod.POST, request, String.class);
                result = responseEntity.getBody();
                System.out.println(result);
                response.add(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("users/ssl/test")
    public ResponseEntity<List<String>> createBatchUserWithSSLTest(@RequestBody PostObject object) {
        List<String> response = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("x-access-token", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBwZ3dmLmNvbSIsImV4cCI6MTY0NzAyMjcyMH0.p8X7CV01nHutzDI3BQy3RVkqeMCrmb5QvDaHG7Pyo1v_up6Y-FmOsFQlP6QqRRkqz0ijjAvAgqbcckf2glxxAQ");
        JSONObject req = new JSONObject();
        String transUri = "https://pecat.pangeamt.com:8443/pecatv1/users";
        String result = "";
        List<String> roles = new ArrayList<>();
        roles.add("Translator");
        List<String> source = new ArrayList<>();
        List<String> target = new ArrayList<>();
        String[] sT = object.getName().split("_");
        source.add(sT[0]);
        target.add(sT[1]);
        List<Integer> groups = new ArrayList<>();
        groups.add(15);

        /*****************************************************************
         *
         */
        try {
            TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

            Registry<ConnectionSocketFactory> socketFactoryRegistry =
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("https", sslsf)
                            .register("http", new PlainConnectionSocketFactory())
                            .build();

            BasicHttpClientConnectionManager connectionManager =
                    new BasicHttpClientConnectionManager(socketFactoryRegistry);
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
                    .setConnectionManager(connectionManager).build();

            HttpComponentsClientHttpRequestFactory requestFactory =
                    new HttpComponentsClientHttpRequestFactory(httpClient);
            RestTemplate restTemplate = new RestTemplate(requestFactory);


            String name = "test";
            UserModel userModel = new UserModel();
            userModel.setNickname(name);
            userModel.setEmail(name + "@pecat.com");
            userModel.setRoles(roles);
            userModel.setPassword(name);
            userModel.setSource(source);
            userModel.setTarget(target);
            userModel.setStatus(1);
            userModel.setEval(1);
            userModel.setGroups(groups);

            HttpEntity<UserModel> request = new HttpEntity<>(userModel, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(transUri, HttpMethod.POST, request, String.class);
            result = responseEntity.getBody();
            System.out.println(result);
            response.add(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
