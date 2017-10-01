package com.storm.url.stream.Bolts;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.storm.json.GAPI_MAP;

public class NSEBolt extends BaseRichBolt {

	private OutputCollector  collector;
	private BufferedReader br;
	private JSONObject json;
	
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
			  
			    ObjectMapper mapper = new ObjectMapper();
				// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				// false);
				// mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER,
				// true );
				mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
				// mapper.configure(DeserializationConfig.FAIL_ON_UNKNOWN_PROPERTIES,
				// false);
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		       try {
				Thread.sleep(1000);

				GAPI_MAP obj = mapper.readValue(conn.getInputStream(), GAPI_MAP.class);
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("c:\\temp\\stock2.txt"));
				oos.writeObject(obj);
				
				System.out.println(obj.toString()+","+timestamp);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    
			    
/*			    while ((data = br.readLine()) != null) {
					System.out.println(data);
					
				}
			   
			} else {
			    br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
			    System.out.println(br);
*/			}
			
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
		
		declarer.declare(new Fields("url"));
	}

}
