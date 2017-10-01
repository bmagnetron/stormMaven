package com.storm.url.stream.spring;

public interface IBoltBuilder {

	public SpringUrlBolt createBoltInstance(String boltType);

}
