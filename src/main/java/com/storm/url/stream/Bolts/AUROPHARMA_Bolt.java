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
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.storm.json.GAPI_MAP;

public class AUROPHARMA_Bolt extends BaseRichBolt {

	private OutputCollector collector;
	private BufferedReader br;
	private JSONObject json;

	String data;

	@Override
	public void execute(Tuple tuple) {
		
		URL url;
		try {
			url = new URL(tuple.getString(0));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			if (200 <= conn.getResponseCode() && conn.getResponseCode() <= 299) {
			    br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			    ObjectMapper mapper = new ObjectMapper();
				mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				GAPI_MAP obj = mapper.readValue(conn.getInputStream(), GAPI_MAP.class);
			//	System.out.println(obj.toString()+","+timestamp+"AUROPHARMA_BOLT");
				collector.emit(tuple, new Values(obj.toString()));
			//	collector.ack(tuple);
				
			}
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

	@Override
	public void cleanup() {
	}

	// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
	// false);
	// mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER,
	// true );
	// mapper.configure(DeserializationConfig.FAIL_ON_UNKNOWN_PROPERTIES,
	// false);

}
