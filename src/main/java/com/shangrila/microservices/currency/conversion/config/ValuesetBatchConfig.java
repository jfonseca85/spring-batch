package com.shangrila.microservices.currency.conversion.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shangrila.microservices.currency.conversion.model.Cotacaomoeda;
import com.shangrila.microservices.currency.conversion.service.batch.DuplicateRecordSkipListener;

@Configuration
public class ValuesetBatchConfig extends BatchConfig{

	@Autowired
	private JobBuilderFactory jobs;
	@Autowired
	private StepBuilderFactory steps;
    
    // tag::jobstep[]
    @Bean(name = "valueSetJob")
    public Job valueSetJob() {
        return jobs.get("valueSetJob")
                .incrementer(new RunIdIncrementer())
                .flow(valueSetStep())
                .end()
                .build();
    }

    @Bean
    public Step valueSetStep() {
        return steps.get("valueSetStep")
                .<Cotacaomoeda, Cotacaomoeda>chunk(10)
                .reader(reader())
                .writer(writer())
                .faultTolerant()
                .skipPolicy(new DuplicateRecordSkipListener())
                .build();

    }
    // end::jobstep[]

}
