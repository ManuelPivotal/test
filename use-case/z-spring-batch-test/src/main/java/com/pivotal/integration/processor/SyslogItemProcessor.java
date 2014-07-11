package com.pivotal.integration.processor;

import org.springframework.batch.item.ItemProcessor;

public class SyslogItemProcessor implements ItemProcessor<String[], String[]> {

	@Override
	public String[] process(String[] item) throws Exception {
		String[] caps = new String[item.length];
		for(int index = 0; index < item.length; index++) {
			caps[index] = item[index].toUpperCase();
		}
		return caps;
	}
}	
