package com.pivotal.archiver.transformer;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Header;
import org.springframework.integration.annotation.Payload;

import com.pivotal.archiver.domain.SyslogObject;

public class SyslogToPojoTransformer {
	
	private static final Logger LOG = LoggerFactory.getLogger(SyslogToPojoTransformer.class);
	private AtomicInteger total = new AtomicInteger(0);
	
	public SyslogObject fromSyslogToPojo(@Payload String message, 
				@Header("syslog_FACILITY") String facility, 
				@Header("syslog_HOST") String host, 
				@Header("syslog_TIMESTAMP") String timeStamp,
				@Header("syslog_SEVERITY") String severity) {
		//LOG.debug("Getting {}", total.incrementAndGet());
		try {
		SyslogObject syslogObject = new SyslogObject();
		syslogObject.setFacility(facility);
		syslogObject.setHost(host);
		syslogObject.setSeverity(severity);
		syslogObject.setMessage(message);
		syslogObject.setTimeStamp(timeStamp);
		return syslogObject;
		} catch(Throwable t) {
			LOG.debug("Exception caught for {}", total, t);
			throw new RuntimeException(t);
		}
	}
}
