package com.pivotal.archiver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ArchiverMain {
	public static void main(String[] args) {
		//ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/integration/ archiver-no-aggregator.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/integration/archiver.xml");
	}
}
