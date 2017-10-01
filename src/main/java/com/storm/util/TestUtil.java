package com.storm.util;

import org.apache.storm.tuple.Values;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.storm.url.stream.spring.ApplicationContextUtil;

public class TestUtil {
	public static Object initializeContext(String beanName) {
		Object bean = null;
		try {
			synchronized (TestUtil.class) {
				if (ApplicationContextUtil.getAppContext() == null) {
					ApplicationContext appContext = new ClassPathXmlApplicationContext("testBeans.xml");
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
	
	public static singleproto intializeLoadBean(String stringValues)
	{
		singleproto bean = new singleproto();
		return bean;

	}
	
	public static String spoutUrlMap(String spoutName)
	{
		
		
		return spoutName;
		
		
	}
	
	
}