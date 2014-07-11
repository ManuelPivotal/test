package com.pivotal.integration.transformer;

import java.io.File;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.integration.launch.JobLaunchRequest;

public class FileToJobLaunchRequest {
	
	private Job job;
	private String parameterName;

	public void setJob(Job job) {
		this.job = job;
	}
	
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	
	public JobLaunchRequest fileToJobAluchRequest(File file) {
		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addString(parameterName, file.getAbsolutePath());
		return new JobLaunchRequest(job, builder.toJobParameters());
	}
}
