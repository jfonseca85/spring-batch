package com.shangrila.microservices.currency.conversion.config;

import java.io.ByteArrayInputStream;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.shangrila.microservices.currency.conversion.model.Cotacaomoeda;
import com.shangrila.microservices.currency.conversion.service.batch.MultiResourceItemReaderDataFileReader;
import com.shangrila.microservices.currency.conversion.service.batch.ValueSetDataFileReader;

public abstract class BatchConfig {
		
	@Autowired
	private MongoTemplate mongoTemplate;
	
    // tag::readerwriterprocessor[]
	protected abstract Job valueSetJob();
	protected abstract Step valueSetStep();
	
	@Bean
	@StepScope
	protected FlatFileItemReader<Cotacaomoeda> reader() {

		// To read lines from an input file
		FlatFileItemReader<Cotacaomoeda> csvFilereader = new ValueSetDataFileReader();
		csvFilereader.setResource(new InputStreamResource(new ByteArrayInputStream(new byte[0])));
		csvFilereader.setLineMapper(CotacaomoedasLineMapper());
		return csvFilereader;

	}

	@Bean
	@StepScope
	protected MultiResourceItemReader<Cotacaomoeda> multiResourceItemReader() {

		// To read lines from an input file
		MultiResourceItemReader<Cotacaomoeda> csvFilereader = new MultiResourceItemReaderDataFileReader();
		csvFilereader.setResources(new Resource[0]);
		csvFilereader.setDelegate(reader());
		return csvFilereader;

	}

	@Bean
	protected MongoItemWriter<Cotacaomoeda> writer() {
		MongoItemWriter<Cotacaomoeda> writer = new MongoItemWriter<Cotacaomoeda>();
		writer.setTemplate(mongoTemplate);
		writer.setCollection("Cotacaomoedas");
		return writer;
	}

	protected LineMapper<Cotacaomoeda> CotacaomoedasLineMapper() {
		DefaultLineMapper<Cotacaomoeda> valueSetLineMapper = new DefaultLineMapper<>();
		valueSetLineMapper.setLineTokenizer(createValueSetLineTokenizer());
		valueSetLineMapper.setFieldSetMapper(valueSetFieldSetMapper());
		return valueSetLineMapper;
	}

	protected LineTokenizer createValueSetLineTokenizer() {
		DelimitedLineTokenizer valueSetLineTokenizer = new DelimitedLineTokenizer();
		valueSetLineTokenizer.setDelimiter(";");
		valueSetLineTokenizer.setNames(new String[] { "id.data", "id.codMoeda", "tipo", "siglaMoeda", "taxaCompra",
				"TaxaVenda", "paridadeCompra", "paridadeVenda" });
		return valueSetLineTokenizer;
	}

	protected FieldSetMapper<Cotacaomoeda> valueSetFieldSetMapper() {
		BeanWrapperFieldSetMapper<Cotacaomoeda> valueSetFieldMapper = new BeanWrapperFieldSetMapper<>();
		valueSetFieldMapper.setTargetType(Cotacaomoeda.class);
		return valueSetFieldMapper;
	}

	// end::readerwriterprocessor[]

}
