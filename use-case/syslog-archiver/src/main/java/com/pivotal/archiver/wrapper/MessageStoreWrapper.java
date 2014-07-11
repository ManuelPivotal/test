package com.pivotal.archiver.wrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.store.MessageGroup;
import org.springframework.integration.store.SimpleMessageStore;
import org.springframework.messaging.Message;

import com.pivotal.archiver.domain.SyslogObject;

public class MessageStoreWrapper extends SimpleMessageStore {
	private static Logger LOG = LoggerFactory.getLogger(MessageStoreWrapper.class);
	@Override
	public long getMessageCount() {
		long messageCount =  super.getMessageCount();
		LOG.debug("Message count is {}", messageCount);
		return messageCount;		
	}

	@Override
	public <T> Message<T> addMessage(Message<T> message) {
		if(message.getPayload() instanceof SyslogObject) {
			LOG.debug("Adding {}", ((SyslogObject)message.getPayload()).getMessage());
		}
		return super.addMessage(message);
	}

	@Override
	public MessageGroup addMessageToGroup(Object groupId, Message<?> message) {
		if(message.getPayload() instanceof SyslogObject) {
			LOG.debug("Adding to group {} message [{}]", groupId, ((SyslogObject)message.getPayload()).getMessage());
		}
		return super.addMessageToGroup(groupId, message);
	}

	@Override
	public void completeGroup(Object groupId) {
		LOG.debug("CompleteGroup {}", groupId);
		super.completeGroup(groupId);
	}

}
