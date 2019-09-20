package com.baikal.springboot.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class RestService {


  @ResponseBody
  public StringBuilder getRestApiInfo(Map<String, Object> restApiModel) throws Exception {
    String output = "";
    StringBuilder sb = new StringBuilder();

    int timeout = 5;
    RequestConfig config = RequestConfig.custom()
        .setConnectTimeout(timeout * 3000)
        .setConnectionRequestTimeout(timeout * 3000)
        .setSocketTimeout(timeout * 3000).build();

    HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

    HttpGet getRequest = null;
    getRequest = new HttpGet(restApiModel.get("ip").toString() + restApiModel.get("apiNm").toString());

    getRequest.addHeader("accept", "application/json");

    HttpResponse response = httpClient.execute(getRequest);


    BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

    while ((output = br.readLine()) != null) {
      sb.append(output);
    }

    return sb;
  }
}
