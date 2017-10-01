/**
 * 
 */
package com.storm.url.stream.spring;

/**
 * @author BharathiKumar_S
 *
 */
public class URLSpoutBuilder implements ISpoutBuilder {

	/* (non-Javadoc)
	 * @see com.storm.url.stream.spring.ISpoutBuilder#createSpoutInstance(java.lang.String)
	 */

	@Override
	public SpringUrlSpout createSpoutInstance(String spoutType) 
	{
		
		return Util.intializeSpringUrlSpoutBean(spoutType);
		
	}

}
