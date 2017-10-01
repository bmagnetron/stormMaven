package com.storm.url.stream.Bolts;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Map;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.json.JSONObject;

public class AUROPHARMA_Write_Bolt extends BaseRichBolt {

	private static final String FILENAME = "c:\\temp\\";
	private OutputCollector collector;
	String bolt_name = "";
	String data;
	static int i = 0;
	static int j = 0;

	@Override
	public void execute(Tuple tuple) {

		System.out.println("Write Bolt in Execute method :" + j + 1);

		data = tuple.getString(0);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(tuple.getString(0) + "," + timestamp + ", wRITE_bOLT");
		OutputStream o;
		bolt_name= this.getClass().getSimpleName();
		try {
			o = new FileOutputStream(FILENAME+bolt_name+".txt", true);
			o.write(data.getBytes());
			o.write(13);
			o.write(10);
			o.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
		collector.ack(tuple);

	}

	@Override
	public void prepare(Map conf, TopologyContext context, OutputCollector outputcollector) {
		this.collector = outputcollector;
		/* 
		 * do one time initialization activities here 
		 * this method runs only once
		 * 
		 */

		System.out.println("Write Bolt in prepare method :" + i + 1);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {

		declarer.declare(new Fields("data"));
	}

	@Override
	public void cleanup() {
	}

}
