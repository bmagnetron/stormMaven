package com.storm.url.stream.spring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Map;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

public class SpringUrlBolt extends BaseRichBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	String data;
	@SuppressWarnings("unused")
	private ObjectOutputStream oos;
	private BufferedReader br;

	@Override
	public void execute(Tuple tuple) {

		URL url;
		try {
			Thread.sleep(1000);
			url = new URL(tuple.getString(0));
			System.out.println("Executing Bolt");
			print(getUrlData(url));

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		collector.ack(tuple);
	}

	@Override
	public void prepare(Map conf, TopologyContext context, OutputCollector outputcollector) {
		System.out.println("bOLTnAME:" + context.getComponentId(context.getThisTaskId()));
		this.collector = outputcollector;

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {

		declarer.declare(new Fields("url"));
	}

	public BufferedReader getUrlData(URL Url) {
		try {

			HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
			if (200 <= conn.getResponseCode() && conn.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				System.out.println(timestamp);

			} else {
				System.out.println("ResponseCode: " + conn.getResponseCode());
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
		return br;

	}

	private void print(BufferedReader br) throws IOException {
		while ((data = br.readLine()) != null) {
			System.out.println(data);

		}

	}

	@SuppressWarnings("unused")
	private void Json_parser() {
		// JSON writer -- use it later
		/*
		 * ObjectMapper mapper = new ObjectMapper();
		 * mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true); Timestamp
		 * timestamp = new Timestamp(System.currentTimeMillis());
		 * Thread.sleep(1000); // To fetch url every second GAPI_MAP obj =
		 * mapper.readValue(conn.getInputStream(), GAPI_MAP.class); oos = new
		 * ObjectOutputStream(new FileOutputStream("c:\\temp\\stock1.txt"));
		 * oos.writeObject(obj); oos.flush();
		 */
		
		//the following is not required at this moment
		// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		// mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER,true);
		// mapper.configure(DeserializationConfig.FAIL_ON_UNKNOWN_PROPERTIES,
		// false);

	}

}
