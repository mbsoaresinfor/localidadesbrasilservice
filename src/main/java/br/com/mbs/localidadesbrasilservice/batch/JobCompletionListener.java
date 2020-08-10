package br.com.mbs.localidadesbrasilservice.batch;

import org.jboss.logging.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobCompletionListener implements JobExecutionListener {

	private static Logger LOG = Logger.getLogger(JobCompletionListener.class);
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.STARTED) {
			LOG.info("BATCH JOB STARTING");
		}

	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			LOG.info("BATCH JOB COMPLETED SUCCESSFULLY");
		}

	}

}
