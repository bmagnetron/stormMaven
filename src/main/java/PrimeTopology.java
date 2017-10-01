import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class PrimeTopology {

	public static void main(String[] args) {
		
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("spout", new PrimeSpout());
		builder.setBolt("bolt", new PrimeBolt()).shuffleGrouping("spout");
		Config conf = new Config();
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("Mytopology", conf, builder.createTopology());
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cluster.killTopology("MyTopology");
		cluster.shutdown();
		
	}

}
