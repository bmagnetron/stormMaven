package com.storm.url.stream.spouts;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

public class UrlSpout extends BaseRichSpout {
	
	private SpoutOutputCollector collector;
	//private String url = "http://finance.google.com/finance/info?client=ig&q=NSE:INFY";
  	private String url ="http://services.groupkt.com/state/get/IND/TN";
	@Override
	public void nextTuple() {
		
	//	System.out.println("Spout next tuple++++++++++++++++++++++++++++++++");
		
		collector.emit(new Values(new String(url)));
	//	collector.emit(new Values(new String(url1)));
		
	}

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector outputcollector) {
		Set<String> ComponentIds = context.getComponentIds();
		List<String> list = new ArrayList<String>(ComponentIds);
		 
	/*	String[] spoutNameArr = list.get(1).split("_");
		String spoutName = spoutNameArr[0] + "_" + spoutNameArr[1];
	*/	
		System.out.println("Spout open ++++++++++++++++++++++++++++++++"+ComponentIds.toString());
		System.out.println(context.getComponentId(context.getThisTaskId()));
		System.out.println(context.getThisTaskId());
		this.collector = outputcollector;
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("url"));
	//	declarer.declare(new Fields("url1"));
		
	}

}
