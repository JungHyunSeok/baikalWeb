package com.baikal.springboot.controller;

import com.baikal.springboot.common.JsonResult;
import com.baikal.springboot.service.RestService;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class ViewController {
	@Autowired
	RestService restService;

	@RequestMapping("/")
	public String index(Map<String, Object> model) {
		model.put("message", "대시보드");
		return "dashBoard/index";
	}

	@RequestMapping("/data/traffic")
	public String traffic(Map<String, Object> model) {
		model.put("message", "트래픽");
		return "dataAnalysis/traffic";
	}

	@RequestMapping("/data/intents")
	public String intents(Map<String, Object> model) {
		model.put("message", "인텐트");
		return "dataAnalysis/intents";
	}

	@RequestMapping(value = "/getTrafficsList")
	@ResponseBody
	public JsonResult getTrafficsList(){
		JsonResult jr = new JsonResult();
		Map<String, Object> filter = new HashMap<String, Object>();

		try{
      /*if("local".equals(profiles) || "test".equals(profiles)){
        String ip = nodeIPInfo(filter.getNode_name());
        filter.setIp(ip);
      }else{

      }*/

			filter.put("ip", "http://104.197.107.202:12130");
			filter.put("apiNm", "/traffics");

			jr.setData(restService.getRestApiInfo(filter));
		}catch (Exception e) {
			jr.setErrorMessage(e.getMessage());
		}
		return jr;
 	}

	@RequestMapping(value = "/getIntentsList")
	@ResponseBody
	public JsonResult getIntentsList(){
		JsonResult jr = new JsonResult();
		Map<String, Object> filter = new HashMap<String, Object>();

		try{
      /*if("local".equals(profiles) || "test".equals(profiles)){
        String ip = nodeIPInfo(filter.getNode_name());
        filter.setIp(ip);
      }else{

      }*/

			filter.put("ip", "http://104.197.107.202:12130");
			filter.put("apiNm", "/intents");

			jr.setData(restService.getRestApiInfo(filter));
		}catch (Exception e) {
			jr.setErrorMessage(e.getMessage());
		}
		return jr;
	}


	@RequestMapping(value = "/getIntentDetailList")
	@ResponseBody
	public JsonResult getIntentDetailList(@RequestBody Map<String, Object> param, HttpServletRequest request){
		JsonResult jr = new JsonResult();
		Map<String, Object> filter = new HashMap<String, Object>();

		try{
      /*if("local".equals(profiles) || "test".equals(profiles)){
        String ip = nodeIPInfo(filter.getNode_name());
        filter.setIp(ip);
      }else{

      }*/

			filter.put("ip", "http://104.197.107.202:12130");
			filter.put("apiNm", "/traffics?intent_id=" + param.get("id"));

			jr.setData(restService.getRestApiInfo(filter));
		}catch (Exception e) {
			jr.setErrorMessage(e.getMessage());
		}
		return jr;
	}

}
