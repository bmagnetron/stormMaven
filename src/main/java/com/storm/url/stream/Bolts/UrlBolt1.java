package com.storm.url.stream.Bolts;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

public class UrlBolt1 extends BaseRichBolt {

	private OutputCollector  collector;
	private BufferedReader br;
	String data;
	@Override
	public void execute(Tuple tuple) {
		
		URL url;
		try {
			url = new URL(tuple.getString(0));
		//	URLConnection conn = url.openConnection();
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			if (200 <= conn.getResponseCode() && conn.getResponseCode() <= 299) {
			    br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			    while ((data = br.readLine()) != null) {
					System.out.println(data);
					
				}
			   
			} else {
			    br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
			    System.out.println(br);
			}
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		collector.ack(tuple);
		
	}

	
	@Override
	public void prepare(Map conf, TopologyContext context, OutputCollector outputcollector) {
		this.collector = outputcollector;
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
		declarer.declare(new Fields("number"));
	}

}
