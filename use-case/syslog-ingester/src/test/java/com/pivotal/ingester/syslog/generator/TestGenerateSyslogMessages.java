package com.pivotal.ingester.syslog.generator;

import org.junit.Test;
import org.productivity.java.syslog4j.Syslog;
import org.productivity.java.syslog4j.SyslogIF;

public class TestGenerateSyslogMessages {
	
	@Test
	public void generateSyslogMessages() throws Exception {
		SyslogIF syslog = Syslog.getInstance("udp");
		syslog.getConfig().setHost("localhost");
		syslog.getConfig().setPort(1514);
		syslog.getConfig().setFacility("KERN");
		for(int index = 0; index < 1000000; index++) {
			syslog.alert("Log Message");
		}
	}
}
