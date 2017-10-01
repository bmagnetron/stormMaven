package com.storm.junit;

import static org.junit.Assert.*;

import org.junit.Test;

public class Testbase {

	@Test
	public void testRes() {
		
		base b = new base();
		assertEquals("onetwo",b.res("on", "two"));
		
	}

}
