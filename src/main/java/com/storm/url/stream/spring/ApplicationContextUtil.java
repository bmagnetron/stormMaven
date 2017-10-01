package com.storm.url.stream.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext appContext;

    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException {
        appContext = applicationContext;
    }

    public static ApplicationContext getAppContext() {
        return appContext;
    }



}
