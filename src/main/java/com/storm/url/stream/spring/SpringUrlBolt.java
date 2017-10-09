package com.storm.url.stream.spring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Map;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

import com.storm.json.StateDetails;
import com.storm.json.UrlJsonParser;

public class SpringUrlBolt extends BaseRichBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//public static Logger log = Logger.getLogger(SpringUrlBolt.class);
	public final static Logger logger = Logger.getLogger(SpringUrlBolt.class);
	private OutputCollector collector;
	
	
	@Override
	public void execute(Tuple tuple) {

		URL url;
		try {
			Thread.sleep(1000);
			url = new URL(tuple.getString(0));
			System.out.println("Executing Bolt");
			getUrlData(url);
			
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

	public void getUrlData(URL Url) {
		try {

			HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
			if (200 <= conn.getResponseCode() && conn.getResponseCode() <= 299) {

				System.out.println(Jsonparser(conn.getInputStream()));
				

			} else {
				System.out.println("ResponseCode: " + conn.getResponseCode());
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/*
	private void print(BufferedReader br) throws IOException {
		while ((data = br.readLine()) != null) {
			System.out.println(data);

		}

	}

	private void printObj(BufferedReader br) throws IOException {
		while ((data = br.readLine()) != null) {
			System.out.println(data);

		}

	}
	*/

	private String Jsonparser(InputStream is) {
		StringBuilder sb = new StringBuilder();
			
		try {
			JsonParser parser = Json.createParser(is);
			while (parser.hasNext()) {
				Event e = parser.next();
				if (e == Event.KEY_NAME) {
					switch (parser.getString()) {
					case "id":
						parser.next();
						sb = sb.append(parser.getString());
						break;
					case "country":
						parser.next();
						sb = sb.append(","+parser.getString());
						break;
					case "name":
						parser.next();
						sb = sb.append(","+parser.getString());
						break;
					case "abbr":
						parser.next();
						sb = sb.append(","+parser.getString());
						break;

					case "area":
						parser.next();
						sb = sb.append(","+parser.getString());
						break;

					case "largest_city":
						parser.next();
						sb = sb.append(","+parser.getString());
						break;

					case "captial":
						parser.next();
						sb = sb.append(","+parser.getString());
						break;

					}

				}
			}
		} catch (Exception e) {

		} finally {

		}
		logger.info(sb.toString());
		return sb.toString() ;
	}

	
}
