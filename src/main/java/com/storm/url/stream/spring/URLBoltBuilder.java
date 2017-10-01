/**
 * 
 */
package com.storm.url.stream.spring;

/**
 * @author BharathiKumar_S
 *
 */
public class URLBoltBuilder<T> implements IBoltBuilder {

	/* (non-Javadoc)
	 * @see com.storm.url.stream.spring.ISpoutBuilder#createBoltInstance(java.lang.String)
	 */
	
	@Override
	public SpringUrlBolt createBoltInstance(String boltType)
	{
		return Util.intializeSpringUrlBoltBean(boltType);
		
		
	}

}
