package com.storm.url.stream.spring;

public interface ISpoutBuilder {

	public SpringUrlSpout createSpoutInstance(String spoutType);

}
