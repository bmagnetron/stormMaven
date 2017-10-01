package com.storm.util;

import com.storm.url.stream.spring.Util;

public class Proto {

	private singleproto spt;

	public void CreateProto() {


		spt = TestUtil.intializeLoadBean("singleProto");

		spt.setKey("DELL_GOOGLE");
		spt.testValues();
		
		spt.setKey("INFY_Google");
		spt.testValues();

		
	}

	public singleproto getSpt() {
		return spt;
	}

	public void setSpt(singleproto spt) {
		this.spt = spt;
	}

}
