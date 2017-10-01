package com.storm.junit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.storm.url.stream.spring.UrlTopologyspring;

public class testvaluesRun {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("testBeans.xml");
		testvalues tv = (testvalues)context.getBean("testvalues");
		tv.print();
	  
		tv.print();
		
	}

}
