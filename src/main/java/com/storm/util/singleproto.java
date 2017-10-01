package com.storm.util;



import org.apache.storm.tuple.Values;

import com.storm.url.stream.spring.Util;

public class singleproto {
	
	//private StaticLoadTableDetails lt;
	private LoadTableDetails lt;
	private String key;
	private String url;
	private Values values;
	
	public void testValues()
	{
		//url = StaticLoadTableDetails.getMapping(key);
		url = lt.getMapping(key);
		System.out.println("URL got :" +url);
		values = (Values) TestUtil.intializeBean(url);
		System.out.println("Values :"+values.toString());
		
	}

	

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Values getValues() {
		return values;
	}

	public void setValues(Values values) {
		this.values = values;
	}



	public LoadTableDetails getLt() {
		return lt;
	}



	public void setLt(LoadTableDetails lt) {
		this.lt = lt;
	}

}
