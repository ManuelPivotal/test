package com.pivotal.ingester.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.integration.core.MessageProducer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

public class LogfileHandler implements MessageProducer {
	
	final private Logger LOG = Logger.getLogger(LogfileHandler.class);
	
	private MessageChannel outputChannel;
	private boolean deleteFile = true;

	@Override
	public void setOutputChannel(MessageChannel outputChannel) {
		this.outputChannel = outputChannel;
	}
	
	public void setDeleteFileAfterProcessing(boolean deleteFile) {
		this.deleteFile = deleteFile;
	}
		
	public void processFile(String fileName) {
		LOG.info(String.format("Processing [%s]", fileName));
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line;
			while((line = reader.readLine()) != null) {
				outputChannel.send(new GenericMessage<String>(line));
			}
			reader.close();
			deleteIfNecessary(fileName);
		} catch(Exception exception) {
			LOG.error(String.format("Error processing file {%s]", fileName), exception);
			throw new RuntimeException(exception);
		} finally {
			if(reader != null) {
				try { reader.close(); } catch (IOException e) {}
			}
			LOG.info(String.format("Processed [%s]", fileName));
		}
	}

	private void deleteIfNecessary(String fileName) {
		if(deleteFile) {
			boolean hasdeleted = new File(fileName).delete();
			if(!hasdeleted) {
				LOG.error(String.format("Could not delete file %s", fileName));
			}
		}
	}
}
