package com.pivotal.archiver.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.gemfire.GemfireTemplate;

import com.pivotal.archiver.domain.SyslogObject;

public class GemfireWriter {
	
	private static final Logger LOG = LoggerFactory.getLogger(GemfireWriter.class);
	
	private GemfireTemplate gemfireTemplate;
	AtomicInteger total = new AtomicInteger(0);
	
	public void writeMultipleInCache(List<SyslogObject> syslogObjects) throws Exception {
		int current = total.addAndGet(syslogObjects.size());
		LOG.debug("Writting {} syslogObject in the cache - total is {}", syslogObjects.size(), current);
		Map<String, SyslogObject> map = new HashMap<String, SyslogObject>();
		for(SyslogObject syslogObject : syslogObjects) {
			LOG.debug("Writting [{}]", syslogObject.getMessage());
			map.put(syslogObject.getKey(), syslogObject);
		}
		gemfireTemplate.putAll(map);
	}
	
	public void writeInCache(SyslogObject syslogObject) throws Exception {
		LOG.debug("Writting syslogObject in the cache");
		gemfireTemplate.put(syslogObject.getKey(), syslogObject);
	}

	public GemfireTemplate getGemfireTemplate() {return gemfireTemplate;}
	public void setGemfireTemplate(GemfireTemplate gemfireTemplate) {this.gemfireTemplate = gemfireTemplate;}
}
