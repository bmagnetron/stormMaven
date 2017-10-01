package com.storm.url.stream.spring;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.scheduler.Cluster;
import org.apache.storm.topology.TopologyBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.storm.util.LoadTableDetails;
import com.storm.util.StaticLoadTableDetails;



public class UrlTopologyRun {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
//		LoadTableDetails lts = (LoadTableDetails) context.getBean("loadtable");
		StaticLoadTableDetails lts = (StaticLoadTableDetails) context.getBean("loadtable");
		lts.readfiles();
		lts.populateTable();
	
		UrlTopologyspring uts = (UrlTopologyspring) context.getBean("UrlTopologyspring");
		/* url list file and build topology 
		 * cluster.submit is required for each spout or bolt
		 *  topology1 --> spout 1..n --> bol1 1..n
		 *  topology2 --> spout 1..n --> bolt 1..n
		 * 
		 * Spout/ bolt naming conventions
		 * ---------------------------------
		 * Spout = stockname_sourcesitename_spout
		 * bolt  = stockname_sourcesitename_spout
		 * */
		
		uts.buildTopology("up_Google_spout","up_Google_Bolt");
		uts.buildTopology("tn_NSE_spout","tn_NSE_Bolt"); 
		uts.buildTopology("ap_NSE_spout","ap_NSE_Bolt"); 
		uts.buildTopology("br_NSE_spout","br_NSE_Bolt"); 
		uts.buildTopology("ar_NSE_spout","ar_NSE_Bolt"); 
		uts.submit_topology("SpringTopology");

	}
}
