package com.storm.url.stream.spring;

import org.apache.storm.tuple.Values;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Util {
	public static Object initializeContext(String beanName) {
		Object bean = null;
		try {
			synchronized (Util.class) {
				if (ApplicationContextUtil.getAppContext() == null) {
					ApplicationContext appContext = new ClassPathXmlApplicationContext("SpringBeans.xml");
					// bean = ApplicationContextUtil.getAppContext().getBean(beanName);
					bean = appContext.getBean(beanName);
				} else {
					bean = ApplicationContextUtil.getAppContext().getBean(beanName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bean;
	}

	public static Object intializeBean(String stringValues)
	{
		Object bean = new Values(new String(stringValues));
		return bean;

	}
	
	public static SpringUrlSpout intializeSpringUrlSpoutBean(String stringValues)
	{
		SpringUrlSpout bean = new SpringUrlSpout();
		return bean;

	}
	
	public static SpringUrlBolt intializeSpringUrlBoltBean(String stringValues)
	{
		SpringUrlBolt bean = new SpringUrlBolt();
		return bean;

	}
	
	
	
	public static String spoutUrlMap(String spoutName)
	{
		
		
		return spoutName;
		
		
	}
	
	
}