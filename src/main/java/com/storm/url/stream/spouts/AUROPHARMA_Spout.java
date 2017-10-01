package com.storm.url.stream.spouts;
import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

public class AUROPHARMA_Spout extends BaseRichSpout {
	
	private SpoutOutputCollector collector;
	private String url = "http://finance.google.com/finance/info?client=ig&q=NSE:AUROPHARMA";
	@Override
	public void nextTuple() {
		
		collector.emit(new Values(new String(url)));
	//	collector.emit(new Values(new String(url1)));
		
	}

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector outputcollector) {
		this.collector = outputcollector;
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("url"));
	//	declarer.declare(new Fields("url1"));
		
	}

}
