package com.storm.util;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;



public class StaticLoadTableDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String fileEquityToScreen;
	private static String fileProviderDetailsUrl;
	private static List<String> equitystream = null;
	private static List<String> providerUrlStream = null;
	private static HashMap<String, String> equityUrlTable;

	public static void readfiles() {
		try {
			equitystream = Files.readAllLines(Paths.get(fileEquityToScreen));
			providerUrlStream = Files.readAllLines(Paths.get(fileProviderDetailsUrl));
			 /*providerUrlStream.forEach(System.out::println);
			 equitystream.forEach(System.out::println);*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void populateTable() {
		String key;
		String value;
		for (int j = 0; j < providerUrlStream.size(); j++) {
			String[] tempstr = providerUrlStream.get(j).split("-");
			for (int i = 0; i < equitystream.size(); i++) {

				key = (equitystream.get(i).trim() + "_" + tempstr[0].trim()).toUpperCase();
				value = (tempstr[1].trim() + equitystream.get(i).trim());
				equityUrlTable.put(key, value);
			}

		}

		 equityUrlTable.forEach((k,v)->System.out.println("Key :" +k +" | value : "+v));
	}
	
	public static String getMapping(String Key)
	{
		String value = null;
		
		int endpos = Key.lastIndexOf("_");
		
		Key = Key.toUpperCase().substring(0,endpos);
	//	System.out.println(Key);
		
		if (equityUrlTable.containsKey(Key) == true )
		{
			
			value = equityUrlTable.get(Key);
		}
		
		return value;
	}

	public void setfileProviderDetailsUrl(String fileProviderDetailsUrl) {
		this.fileProviderDetailsUrl = fileProviderDetailsUrl;
	}

	public List<String> getEquitystream() {
		return equitystream;
	}

	public void setEquitystream(List<String> equitystream) {
		this.equitystream = equitystream;
	}

	public List<String> getProviderUrlStream() {
		return providerUrlStream;
	}

	public void setProviderUrlStream(List<String> providerUrlStream) {
		StaticLoadTableDetails.providerUrlStream = providerUrlStream;
	}

	public HashMap<String, String> getEquityUrlTable() {
		return  equityUrlTable;
	}

	public void setEquityUrlTable(HashMap<String, String> equityUrlTable) {
		StaticLoadTableDetails.equityUrlTable = equityUrlTable;
	}

	public String getfileEquityToScreen() {
		return fileEquityToScreen;
	}

	public void setfileEquityToScreen(String fileEquityToScreen) {
		StaticLoadTableDetails.fileEquityToScreen = fileEquityToScreen;
	}

	public String getfileProviderDetailsUrl() {
		return fileProviderDetailsUrl;
	}

}