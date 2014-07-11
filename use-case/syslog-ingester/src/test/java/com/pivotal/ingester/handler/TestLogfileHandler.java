package com.pivotal.ingester.handler;

import static org.mockito.Mockito.mock;

import java.net.URL;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

public class TestLogfileHandler {
	
	@Test
	public void canProcessFile() {
		MessageChannel outputChannel = mock(MessageChannel.class);
		LogfileHandler handler = new LogfileHandler();
		handler.setOutputChannel(outputChannel);
		URL url = Thread.currentThread().getContextClassLoader().getResource("com/pivotal/ingester/handler/test-log.log");
		handler.processFile(url.getFile());
		Mockito.verify(outputChannel, Mockito.times(3)).send((Message<?>) Mockito.any());
	}
}
