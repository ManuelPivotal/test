package com.pivotal.ingester.message.transformer;

import java.util.Map;

import org.springframework.integration.support.DefaultMessageBuilderFactory;
import org.springframework.integration.transformer.SyslogToMapTransformer;
import org.springframework.messaging.Message;

public class SyslogToMessage {
	
	@SuppressWarnings("unchecked")
	public Message<?> fromSyslogToMessage(Message<?> syslog) throws Exception {
		Map<String, Object> map = (Map<String, Object>)syslog.getPayload();
		String payload = getMessageFromSyslogPayload(map);
		return new DefaultMessageBuilderFactory()
							.withPayload(payload)
							.copyHeaders(syslog.getHeaders())
							.build();
	}

	private String getMessageFromSyslogPayload(Map<String, Object> map) {
		return (String)map.get(SyslogToMapTransformer.MESSAGE);
	}
}
