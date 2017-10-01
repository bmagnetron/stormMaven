package com.storm.url.stream.Topology;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.utils.Utils;

import com.storm.url.stream.Bolts.*;
import com.storm.url.stream.spouts.*;

public class UrlTopology {
	private static int i=0;
	private TopologyBuilder builder;

	public static void main(String[] args) {

		TopologyBuilder builder = new TopologyBuilder();

		/*
		 * Spout and Bolt to Stream SHARES Use threads when you want to write
		 * the same data to multiple operation say for processing the data for
		 * writing the data for triggering another search etc if we use threads
		 * 3 for spout and bolt then the same data can be used for the above
		 * said process independently
		 * 
		 */

		/*builder.setSpout("AUROPHARMA_Spout", new AUROPHARMA_Spout());
		builder.setBolt("AUROPHARMAS_Bolt", new AUROPHARMA_Bolt()).shuffleGrouping("AUROPHARMA_Spout");
		builder.setBolt("AUROPHARMA_Write_Bolt", new AUROPHARMA_Write_Bolt()).shuffleGrouping("AUROPHARMAS_Bolt");*/

		/* Spout and Bolt to Stream INFY */

			System.out.println("Creating Spout/Bolt "+ i +1);
		  builder.setSpout("Googlespout", new UrlSpout(),10);
		  builder.setBolt("Googlebolt", new UrlBolt()).shuffleGrouping("Googlespout");
		 

		/* Spout and Bolt to Stream HDFC SHARES */

		
		  builder.setSpout("HDFC_Spout",new HDFC_Spout());
		  builder.setBolt("HDFC_Bolt",new HDFC_Bolt()).shuffleGrouping("HDFC_Spout");
		  
		 System.out.println(builder.getClass());
		Config conf = new Config();
		
	//	conf.setDebug(true);
		// conf.setNumWorkers(2);

		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("URLtopology", conf, builder.createTopology());
	//	Utils.sleep(50000);

		/*
		 * TO BUILD AN RUN ANOTHER TOPOLOGY builder.setSpout("AUROPHARMAspout",
		 * new UrlSpout()); builder.setBolt("AUROPHARMAbolt", new
		 * UrlBolt()).shuffleGrouping("AUROPHARMAspout");
		 * cluster.submitTopology("AUROPHARMATopology",conf,builder.
		 * createTopology());
		 */

		
	//	cluster.killTopology("URLtopology");
	//	System.out.println(
	//			"Cluster topoloy kill ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	//	cluster.shutdown();

	}

}
