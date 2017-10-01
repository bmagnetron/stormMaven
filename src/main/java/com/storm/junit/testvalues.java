package com.storm.junit;

import org.apache.storm.tuple.Values;

public class testvalues {
	
	public Values getValues() {
		return values;
	}

	public void setValues(Values values) {
		this.values = values;
	}

	private Values values;
	private String url;
	
	public void print()
	{
		System.out.println(values.toString());
	}

}
