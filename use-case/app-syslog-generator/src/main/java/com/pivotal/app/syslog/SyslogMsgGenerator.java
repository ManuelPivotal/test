package com.pivotal.app.syslog;

import org.productivity.java.syslog4j.Syslog;
import org.productivity.java.syslog4j.SyslogIF;

public class SyslogMsgGenerator {
	
	static public void main(String args[]) throws Exception {
		
		int maxMessage = 10000;
		if(args.length > 0) {
			maxMessage = Integer.valueOf(args[0]);
		}
		
		System.out.printf("Generating %d messages\n", maxMessage);

		SyslogIF syslog = Syslog.getInstance("udp");
		
		syslog.getConfig().setHost("localhost");
		syslog.getConfig().setPort(1514);
		syslog.getConfig().setFacility("KERN");
		
		System.out.printf("Starting sending syslog\n");
		
		for(int index = 0; index < maxMessage; index++) {
			syslog.alert("Alert Message " + index);
			if(index % 1000 == 0 && index > 0) {
				System.out.printf("Send %d syslogs\n", index);
			}
		}
		
		System.out.printf("Done\n");
	}
}
