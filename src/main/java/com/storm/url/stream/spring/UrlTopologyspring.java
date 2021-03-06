package com.storm.url.stream.spring;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class UrlTopologyspring {
	private TopologyBuilder builder;
	private SpringUrlSpout urlspout;
	private SpringUrlBolt urlbolt;
	private Config config;
	private LocalCluster cluster;
	private String spoutName;
	private String boltName;
	private String topologyName;
	private static final String spouttype = "UrlSpout";
	private static final String bolttype = "UrlBolt";
 /*	private URLSpoutBuilder spoutbuilder;
	private URLBoltBuilder<?> boltbuilder;
	
*/
	private ISpoutBuilder spoutbuilder;
	private IBoltBuilder boltbuilder;
	
	public void buildTopology(String spoutName, String boltName) 
	{	
		System.out.println("Spoutname passed :"+spoutName);
		this.spoutName = spoutName;
		this.boltName = boltName;
		
		urlspout = spoutbuilder.createSpoutInstance(spouttype);
		urlspout.setSpoutName(spoutName);
		builder.setSpout(spoutName, urlspout,1);
		builder.setBolt(boltName, boltbuilder.createBoltInstance(bolttype),1).shuffleGrouping(spoutName);
		
	}
	
	
	
	public IBoltBuilder getBoltbuilder() {
		return boltbuilder;
	}



	public void setBoltbuilder(URLBoltBuilder<?> boltbuilder) {
		this.boltbuilder = boltbuilder;
	}



	public void submit_topology(String topologyName)
	{
		//Config conf = new Config();
	
		
		cluster.submitTopology(topologyName, config, builder.createTopology());
		/*
		 * THE BELOW SLEEP AND CLUSTER KILL /SHUTDOWN IS NOT REQUIRED IF CLUSTER
		 * SHUTDOWN IS REQUESTED THEN SLEEP IS REQUIRED ELSE THE TOPOLOGY IS
		 * KILLED STRAIGHT AWAY
		 */
		// Utils.sleep(50000);
		// conf.setDebug(true);
		// conf.setNumWorkers(2);

		/*
		 * try { Thread.sleep(5000); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 */

		/*
		 * cluster.killTopology(topologyName); cluster.shutdown();
		 */

	}

	/*
	
	public void buildSpout(String SpoutName, SpringUrlSpout urlspout) {
		builder.setSpout(SpoutName, urlspout);
	}

	public void buildBolt(String boltName, SpringUrlBolt urlbolt, String SpoutName) {
		builder.setBolt(boltName, urlbolt).shuffleGrouping(SpoutName);
	}

*/
	public void buildCluster(String topologyName, Config config) {

		cluster.submitTopology(topologyName, config, getBuilder().createTopology());
	}
	public TopologyBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(TopologyBuilder builder) {
		this.builder = builder;
	}

	public SpringUrlSpout getUrlspout() {
		return urlspout;
	}

	public void setUrlspout(SpringUrlSpout urlspout) {
		this.urlspout = urlspout;
	}

	public SpringUrlBolt getUrlbolt() {
		return urlbolt;
	}

	public void setUrlbolt(SpringUrlBolt urlbolt) {
		this.urlbolt = urlbolt;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public LocalCluster getCluster() {
		return cluster;
	}

	public void setCluster(LocalCluster cluster) {
		this.cluster = cluster;
	}

	public String getSpoutName() {
		return spoutName;
	}

	public void setSpoutName(String spoutName) {
		this.spoutName = spoutName;
	}

	public String getBoltName() {
		return boltName;
	}

	public void setBoltName(String boltName) {
		this.boltName = boltName;
	}

	public String getTopologyName() {
		return topologyName;
	}

	public void setTopologyName(String topologyName) {
		this.topologyName = topologyName;
	}

	public ISpoutBuilder getSpoutbuilder() {
		return spoutbuilder;
	}

	public void setSpoutbuilder(URLSpoutBuilder spoutbuilder) {
		this.spoutbuilder = spoutbuilder;
	}

}
