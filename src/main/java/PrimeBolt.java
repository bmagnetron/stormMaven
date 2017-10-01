import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

public class PrimeBolt extends BaseRichBolt {

	private OutputCollector  collector;
	@Override
	public void execute(Tuple tuple) {
		int number = tuple.getInteger(0);
		
		if(isPrime(number))
					{
			System.out.println(number);
			
		}
		collector.ack(tuple);
		
	}

	private boolean isPrime(int n) {
		for (int i=3;i*i<=n;i+=2)
		{
			if(n%2 == 0)
			{
				return false;
			}
				
		}
		return true;
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
