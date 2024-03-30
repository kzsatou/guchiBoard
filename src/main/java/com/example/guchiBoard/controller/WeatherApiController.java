package com.example.guchiBoard.controller;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.guchiBoard.entity.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class WeatherApiController {
    /**
    * @return 東京の天気情報
    * 
    * @see <a href="https://weather.tsukumijima.net/api/forecast">お天気Webサービス - livedoor</a>
    */
	
	  @RequestMapping( value="weather/tokyo"
	            , produces=MediaType.APPLICATION_JSON_VALUE
	            , method=RequestMethod.GET)
	    private String call() {

	        RestTemplate rest = new RestTemplate();

	        final String cityCode = "140010"; // 東京のCityCode
	        final String endpoint = "https://weather.tsukumijima.net/api/forecast";

	        final String url = endpoint + "?city=" + cityCode;

	        // 直接Beanクラスにマップ出来るけど今回はめんどくさいのでStringで。
	        ResponseEntity<String> response = rest.getForEntity(url, String.class);

	        String json = response.getBody();
	        
	        // jsonのデータを抽出
	        ObjectMapper mapper = new ObjectMapper();
	        try {
	        	Weather weather = mapper.readValue(json, Weather.class);  // 解説
	       
	            System.out.println(weather.getpublishingOffice());
	       
	          } catch (JsonProcessingException e) {
	            e.printStackTrace();
	          }

	        return decode(json);
	    }

	    // いわゆる日本語の２バイト文字がunicodeエスケープされてるので解除。
	    private static String decode(String string) {
	        return StringEscapeUtils.unescapeJava(string);  
	    }

}
