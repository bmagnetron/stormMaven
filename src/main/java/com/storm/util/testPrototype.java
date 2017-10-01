package com.storm.util;

import org.apache.storm.tuple.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import  com.storm.url.stream.spring.Util;
	

public class testPrototype {
	//@Autowired
	
	private String url;
	private Values values;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void create()
	{
		
		for(int i =1;i<5; i++)
		{
			if (values== null) {          
	            values= (Values) Util
	                .initializeContext("values");
	            System.out.print("new Bean");
	            
	       //     values = (Values) Util.intializeBean(url);
	        }
			values.add(url+i);			
			System.out.println(values.toString());
			
		}
		
		
	}
	public Values getValues() {
		return values;
	}
	public void setValues(Values values) {
		this.values = values;
	}
	
	
	
}
