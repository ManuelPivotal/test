package com.pivotal.archiver.domain;

import java.io.Serializable;
import java.util.UUID;

@SuppressWarnings("serial")
public class SyslogObject implements Serializable {
	private String key = UUID.randomUUID().toString();
	private String message;
	private String facility;
	private String severity;
	private String host;
	private String timeStamp;
	
	public String getMessage() {return message;}
	public void setMessage(String message) {this.message = message;}
	
	public String getFacility() {return facility;}
	public void setFacility(String facility) {this.facility = facility;}
	
	public String getSeverity() {return severity;}
	public void setSeverity(String severity) {this.severity = severity;}
	
	public String getHost() {return host;}
	public void setHost(String host) {this.host = host;}
	
	public String  getTimeStamp() {return timeStamp;}
	public void setTimeStamp(String  timeStamp) {this.timeStamp = timeStamp;}
	
	public String getKey() {return key;}
	public void setKey(String key) {this.key = key;}
	
	public int hashcode() {
		return key.hashCode();
	}
	
	public boolean equals(Object eq) {
		if(eq == null || !(eq instanceof SyslogObject)) {
			return false;
		}
		return key.equals(((SyslogObject)eq).key);
	}
}
