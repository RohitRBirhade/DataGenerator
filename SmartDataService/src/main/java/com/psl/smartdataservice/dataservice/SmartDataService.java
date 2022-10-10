package com.psl.smartdataservice.dataservice;

import java.util.List;

import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

/**
 * Service Class Interface
 */
@Service
public interface SmartDataService {
	/**
	 * @param Json
	 * @return List<List<String>>
	 */
	public List<List<String>> getQuery(String Json) ;	
	
}
