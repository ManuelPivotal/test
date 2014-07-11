package com.pivotal.batch.testlaunch;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/integration/batch-test.xml")
public class TestLauchByMessage {
	
	@Autowired(required=true)
	MessageChannel fileToBatch;
	
	@Autowired(required=true)
	MessageChannel fileToLaunchRequest;
	
	@Test
	public void canLaunchUsingMessage() {
		System.out.println("Getting into test channel is " + fileToLaunchRequest);
		Message<File> message = MessageBuilder.withPayload(new File("/test.csv")).build(); 
		fileToBatch.send(message);
	}
}
