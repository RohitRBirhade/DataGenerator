package com.psl.smartdataservice.controller;

import java.net.http.HttpHeaders;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.psl.smartdataservice.dataservice.SmartDataService;

/**Controller Class 
 * Responsible for handling all the request
 * currently supporting 2 request
 * 1> /hello  
 * 2> /api/testdata
 * 
 * */
@RestController
public class SmartDataController {
	
	@Autowired
	SmartDataService smartDataService;
	
	
	/**On the request of \hello sends hello world as a response 
	 * @return String
	 */
	@GetMapping("/hello")
	ResponseEntity<String> hello() {
	    return new ResponseEntity<>("Hello World!", HttpStatus.OK);
	}
	
	
	/**On the request of /api/testdata takes the json formated request through Post Body method and sends generated data 
	 * in the form of List 
	 * @param Json
	 * @return List<String>
	 */
	@PostMapping("/api/testdata")
	ResponseEntity<List<List<String>>> getJson1(@RequestBody String Json) {
		List<List<String>> list = this.smartDataService.getQuery(Json);
		return new ResponseEntity<List<List<String>>>(list , HttpStatus.OK);
	}
	
}