import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

public class PrimeSpout extends BaseRichSpout {
	
	private SpoutOutputCollector collector;
	private int number;

	@Override
	public void nextTuple() {
		collector.emit(new Values(new Integer(number++)));
		
	}

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector outputcollector) {
		this.collector = outputcollector;
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("number"));
		
	}

}
