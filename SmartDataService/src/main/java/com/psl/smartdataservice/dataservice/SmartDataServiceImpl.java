package com.psl.smartdataservice.dataservice;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.management.Query;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.psl.smartdataservice.datamapper.DataMapper;
import com.psl.smartdataservice.generators.DataGenerators;

import ch.qos.logback.core.filter.Filter;
/**
 * Service Class Implementor 
 * Provides functionality for data generation and filtration   
 */
@Repository
public class SmartDataServiceImpl implements SmartDataService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	Random rand = new Random();
	
	private JSONArray jsonArray;
	private JSONObject jsonObject;
	private HashMap<String,String> mapForSeg = new HashMap<>();
	private HashMap<String,String> mapForTitle = new HashMap<>();
	private HashMap<String,String> mapForData = new HashMap<>();
	private HashMap<String,String> mapForCountry = new HashMap<>();
	private JSONParser jsonParser = new JSONParser();
	String query ;
	
	/**
	 * Performes data generation operations and collectes data into JSONArray
	 * 
	 * @param String request 
	 * @return JSONArray
	 */
	@Override
	public List<List<String>> getQuery(String Json)  {
		// TODO Auto-generated method stub
		int size_of;
		try {
			jsonObject = (JSONObject) jsonParser.parse(Json);
			jsonArray =  (JSONArray)jsonObject.get("Rowspace");
            size_of = Integer.parseInt((String) jsonObject.get("Size"));
            JSONArray json_Array = new JSONArray();
        	    
            	for(int i=0;i<jsonArray.size();i++) {
			    	jsonObject = (JSONObject) jsonArray.get(i);
			    	jsonObject.entrySet().forEach( item ->{
			    		String key = item.toString().split("=")[0];
			    		String value = item.toString().split("=")[1];
			    		if(key.equals("seg")) 
			    			setMapForSeg(key, value);
			    		if(key.equals("title")) 
			    			setMapForTitle(key, value);
			    		if(key.equals("data")) 
			    			setMapForData(key, value);
			    		if(key.equals("country")) 
			    			setMapForCountry(key, value);
			    		if(key.equals("type")) {
			    			List<String> listLinkerObject = new ArrayList<>(); 
			    		 	        switch(value) {
					    			     	case "Auto":{
					    			     		listLinkerObject.add(getMapForTitle("title"));
					    			     		listLinkerObject.addAll(new DataGenerators().Auto(size_of));
					    			     		json_Array.add(listLinkerObject);
					    			    		break;
					    			    	}
					    			    	case "Number":{
					    			    		listLinkerObject.add(getMapForTitle("title"));
					    			    		listLinkerObject.addAll(new DataGenerators().Number(size_of));
					    			    		json_Array.add(listLinkerObject);
					    			    		break;
					    			    	}
					    			    	case "AlphaNum":{
					    			    		listLinkerObject.add(getMapForTitle("title"));
					    			    		listLinkerObject.addAll(new DataGenerators().AlphaNum(size_of));
					    			    		json_Array.add(listLinkerObject);
					    			    		break;
					    			     	}
					    			    	case "Date":{
					    			    		listLinkerObject.add(getMapForTitle("title"));
					    			    		listLinkerObject.addAll(new DataGenerators().Date(size_of));
					    			    		json_Array.add(listLinkerObject);
					    			    		break;
					    			    	}
					    			    	case "Time":{
					    			    		listLinkerObject.add(getMapForTitle("title"));
					    			    		listLinkerObject.addAll(new DataGenerators().Time(size_of));
					    			    		json_Array.add(listLinkerObject);
					    			    		break;
					    			        }
					    			    	case "FirstName":{
					    			    		query = "SELECT name FROM firstname WHERE regioncode = \""+getMapForSeg("seg")+"\"";
					    			    		listLinkerObject.add(getMapForTitle("title"));
					    			    		listLinkerObject.addAll(getDataFromDB(query, size_of));
					    			    		json_Array.add(listLinkerObject);
					    			    		break;
					    			    	}
					    			    	case "LastName":{
					    			    		query = "SELECT name FROM lastname WHERE regioncode = \""+getMapForSeg("seg")+"\"";
					    			    		listLinkerObject.add(getMapForTitle("title"));
					    			    		listLinkerObject.addAll(getDataFromDB(query, size_of));
					    			    		json_Array.add(listLinkerObject);
					    			    		break;
					    			    	}
					    			    	case "Email":{
					    			    		int flag_1 = 0;
					    			    		int flag_2 = 0;
					    			    		for(int m=0;m<json_Array.size();m++) {
					    			    			List<String> l1 = (List<String>) json_Array.get(m);
					    			    			if(l1.get(0).equals("FirstName")) {
					    			    				flag_1 = 1;	
					    			    			}
					    			    			if(l1.get(0).equals("LastName")) {
					    			    				flag_2 = 1;	
					    			    			}
					    			    		}					    			    		
					    			    		if(getMapForSeg("seg").equals("fix")) {
					    			    			String str = getMapForData("data");
					    			    			str = str.replaceAll("\\[", "");
					    			    			str = str.replaceAll("\\]", "");
					    			    			query = "SELECT address FROM email WHERE address IN("+str+")";
					    			    			listLinkerObject.add(getMapForTitle("title"));
 							    			     	listLinkerObject.addAll(getDataFromDB(query, size_of));
 							    			     	json_Array.add(getRefineEmails(flag_1,flag_2,listLinkerObject,json_Array,size_of));
					    			    			break;
					    			    		}
					    			    		if(getMapForSeg("seg").equals("random")) {
					    			    			query = "SELECT address FROM email";
					    			    			listLinkerObject.add(getMapForTitle("title"));
 							    			     	listLinkerObject.addAll(getDataFromDB(query, size_of));
 							    			     	json_Array.add(getRefineEmails(flag_1,flag_2,listLinkerObject,json_Array,size_of));
					    			    			break;
					    			    		}
					    			    	}
					    			    	case "Address":{
					    			    		
					    			    		query = "select address from address where country = \""+getMapForCountry("country")+"\"";
					    			    		List<String> localAdd_list = getDataFromDB(query, size_of);
					    			    		query = "select city from address where country = \""+getMapForCountry("country")+"\"";
					    			    		List<String> city_list= getDataFromDB(query, size_of);
					    			    		query = "select state from address where country = \""+getMapForCountry("country")+"\"";
					    			    		List<String> state_list = getDataFromDB(query, size_of);
					    			    		query = "select zip from address where country = \""+getMapForCountry("country")+"\"";
					    			    		List<String> zip_list = getDataFromDB(query, size_of);
					    			    		listLinkerObject.add(getMapForTitle("title"));
					    			    		listLinkerObject.addAll(getRefineAddress(localAdd_list, city_list, state_list, zip_list,size_of));
					    			    		json_Array.add(listLinkerObject);
					    			    		break;
					    			    	}
			        	  	     }
			    		}
			    	 	//System.out.println(key);
					});
			    		
			     }
          new DataMapper().jsonToExalWriter(json_Array);
		  return json_Array;
	     } catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Concats the data to create meaningful Mails
	 * @param flag_1
	 * @param flag_2
	 * @param listLinkerObject
	 * @param json_Array
	 * @param size
	 * @return
	 */
	protected List<String> getRefineEmails(int flag_1, int flag_2, List<String> listLinkerObject, JSONArray json_Array, int size) {
		List<String> emails = new ArrayList<String>();
		emails.add("Emails");
		if((flag_1 + flag_2) != 0) {
			   	if(flag_1 == flag_2) {
				   		List<String> firstName = new ArrayList<String>();
				   		List<String> lastName  = new ArrayList<String>();
			   		    for(int i=0;i<json_Array.size();i++) {
			   		    	   List<String> list = (List<String>) json_Array.get(i);
			       		   	   if(list.get(0).equals("FirstName")) {
					   			    firstName = list;
					   		   }else
					   		   if(list.get(0).equals("LastName")) {
					   			    lastName = list;
					   		   }
					   	}
			   		    for(int j=1;j<size+1;j++) {
			   			   emails.add(firstName.get(j)+""+lastName.get(j)+"@"+listLinkerObject.get(j));
			   		    }
			   	}else if(flag_1 == 1){
				   		List<String> firstName = new ArrayList<String>();
		        		for(int i=0;i<json_Array.size();i++) {
		        			List<String> list = (List<String>) json_Array.get(i);
		        			if(list.get(0).equals("FirstName")) {
				   			    firstName = list;
				   			    break;
				   		    }
		        		}
		        		for(int j=1;j<size+1;j++) {
				   			emails.add(firstName.get(j)+"@"+listLinkerObject.get(j));
				   		}
			   		
			   	}else if(flag_2 == 1){
			   			List<String> lastName  = new ArrayList<String>();
			   		    for(int i=0;i<json_Array.size();i++) {
					   		List<String> list = (List<String>) json_Array.get(i);
					   		if(list.get(0).equals("LastName")) {
				   			    lastName = list;
				   			    break;
				   		    }
					    }
				   		for(int j=1;j<size+1;j++) {
				   			   emails.add(lastName.get(j)+"@"+listLinkerObject.get(j));
				   		}
			    }
		  }
	  return emails;		
	}
	
	/**
	 * Concats the data to create meaningfull Addresses
	 * @param localAdd_list
	 * @param city_list
	 * @param state_list
	 * @param zip_list
	 * @param size
	 * @return
	 */
	
	protected List<String> getRefineAddress(List localAdd_list, List city_list, List state_list, List zip_list, int size){
		List<String> reshaper = new ArrayList<String>();
		int min = 100;
		int max = 999;
		for(int i=0;i<size;i++) {
			reshaper.add(""+(int) (rand.nextFloat() * (max - min) + min)+","+localAdd_list.get(rand.nextInt(size))
			           +","+city_list.get(rand.nextInt(size))
			           +","+state_list.get(rand.nextInt(size))
			           +","+zip_list.get(rand.nextInt(size)));
			
		}
		return reshaper;
	}

	protected void setMapForSeg(String key , String value) {
		mapForSeg.put(key, value);
	}
	
	protected String getMapForSeg(String key) {
		return mapForSeg.get(key);
	}
	protected void setMapForTitle(String key , String value) {
		mapForTitle.put(key, value);
	}
	protected String getMapForTitle(String key) {
		return mapForTitle.get(key);
	}
	protected void setMapForData(String key , String value) {
		mapForData.put(key, value);
	}
	protected String getMapForData(String key) {
		return mapForData.get(key);
	}
	protected void setMapForCountry(String key,String value){
		mapForCountry.put(key, value);
	}
	protected String getMapForCountry(String key) {
		return mapForCountry.get(key);
	}
	
	/**
	 * Fetches All required data from dbs and sends in the form of List<String>
	 * @param queries
	 * @param size
	 * @return
	 */
	
	protected List<String> getDataFromDB(String queries, int size){
		List<String> filter = jdbcTemplate.queryForList(queries).stream()
                .map(curr -> curr.values().stream().findFirst().get().toString())
				.collect(Collectors.toList());
		
		List<String> filterrrr = new ArrayList();
		for(int i= 0; i<size;i++) {
			filterrrr.add(filter.get(rand.nextInt(filter.size())));
		}
		return filterrrr;
	}
	
	public void Head(List list){
		for(int i=0;i<5;i++)
			System.out.println(list.get(i));
	}

}
