package br.com.mbs.localidadesbrasilservice.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.mbs.localidadesbrasilservice.batch.JobCompletionListener;
import br.com.mbs.localidadesbrasilservice.batch.step.ProcessorEmail;
import br.com.mbs.localidadesbrasilservice.batch.step.ReaderEmail;
import br.com.mbs.localidadesbrasilservice.batch.step.WriterEmail;
import br.com.mbs.localidadesbrasilservice.vo.Email;

@Configuration
public class BatchConfiguration {


	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public ReaderEmail readerEmail() {
		return new ReaderEmail();
	}
	
	@Bean
	public  ProcessorEmail processorEmail() {
		return new ProcessorEmail();
	}
	
	@Bean
	public WriterEmail writerEmail() {
		return new WriterEmail();
	}
	
	
	
	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.flow(orderStep1()).end().build();
	}

	@Bean
	public Step orderStep1() {
		return stepBuilderFactory.get("orderStep1").<Email, Email> chunk(1)
				.reader(readerEmail()).processor(processorEmail())
				.writer(writerEmail()).build();
	}

	
	
	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}
	
}
