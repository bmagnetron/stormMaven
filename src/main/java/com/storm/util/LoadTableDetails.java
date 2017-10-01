package com.storm.util;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class LoadTableDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileEquityToScreen;
	private String fileProviderDetailsUrl;
	private List<String> equitystream = null;
	private List<String> providerUrlStream = null;
	private HashMap<String, String> equityUrlTable;

	public void readfiles() {
		try {
			equitystream = Files.readAllLines(Paths.get(fileEquityToScreen));
			providerUrlStream = Files.readAllLines(Paths.get(fileProviderDetailsUrl));
			 /*providerUrlStream.forEach(System.out::println);
			 equitystream.forEach(System.out::println);*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void populateTable() {
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
	
	public String getMapping(String Key)
	{
		String value = null;
		Key = Key.toUpperCase();
		
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
		this.providerUrlStream = providerUrlStream;
	}

	public HashMap<String, String> getEquityUrlTable() {
		return  equityUrlTable;
	}

	public void setEquityUrlTable(HashMap<String, String> equityUrlTable) {
		this.equityUrlTable = equityUrlTable;
	}

	public String getfileEquityToScreen() {
		return fileEquityToScreen;
	}

	public void setfileEquityToScreen(String fileEquityToScreen) {
		this.fileEquityToScreen = fileEquityToScreen;
	}

	public String getfileProviderDetailsUrl() {
		return fileProviderDetailsUrl;
	}

}