package com.storm.junit;



import com.sun.jersey.spi.inject.Inject;
import com.sun.net.ssl.internal.ssl.Provider;

public class Index {

    @Inject
    Provider boundary;

    public String getMessage() {
		return null;
    //    return boundary.get().message();
    }

}