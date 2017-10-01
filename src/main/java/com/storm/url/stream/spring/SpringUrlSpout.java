package com.storm.url.stream.spring;

import java.util.Map;
import java.util.Set;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import com.storm.util.StaticLoadTableDetails;

public class SpringUrlSpout extends BaseRichSpout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SpoutOutputCollector collector;
	private String url;
	private Values values;
	private String spoutName;
	private StaticLoadTableDetails lt;

	@Override
	public void nextTuple() {

		url = StaticLoadTableDetails.getMapping(spoutName);
		values = (Values) Util.intializeBean(url);
		collector.emit(values);
	}

	public Values getValues() {
		return values;
	}

	public void setValues(Values values) {
		this.values = values;
	}

	public SpoutOutputCollector getCollector() {
		return collector;
	}

	public void setCollector(SpoutOutputCollector collector) {
		this.collector = collector;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector outputcollector) {

		Set<String> ComponentIds = context.getComponentIds();
		System.out.println("Spout open ++++++++++++++++++++++++++++++++" + ComponentIds.toString());
		System.out.println(context.getComponentId(context.getThisTaskId()));
		System.out.println(context.getThisTaskId());

		this.collector = outputcollector;

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("url"));

	}

	public String getSpoutName() {
		return spoutName;
	}

	public void setSpoutName(String spoutName) {
		this.spoutName = spoutName;
	}

	public StaticLoadTableDetails getLt() {
		return lt;
	}

	public void setLt(StaticLoadTableDetails lt) {
		this.lt = lt;
	}

}
