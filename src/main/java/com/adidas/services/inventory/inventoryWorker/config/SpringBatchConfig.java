package com.adidas.services.inventory.inventoryWorker.config;

import com.adidas.services.inventory.inventoryWorker.model.User;
import org.aspectj.lang.annotation.Before;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import javax.jws.soap.SOAPBinding;

//@Configuration
public class SpringBatchConfig {

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<User> itemReader,
                   ItemProcessor<User, User> userUserItemProcessor,
                   ItemWriter<User> itemWriter) {
        Step step = stepBuilderFactory.get("file-load")
                .<User, User>chunk(100)
                .reader(itemReader)
                .processor(userUserItemProcessor)
                .writer(itemWriter)
                .build();


        final Job loader = jobBuilderFactory.get("loader")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
        return loader;
    }

    @Bean
    public FlatFileItemReader flatFileItemReader(@Value("${input}") FileSystemResource resource){
        FlatFileItemReader<User> userFlatFileItemReader = new FlatFileItemReader<>();
        userFlatFileItemReader.setResource(resource);
        userFlatFileItemReader.setName("CsvReader");
        userFlatFileItemReader.setLinesToSkip(1);
        userFlatFileItemReader.setLineMapper(linemapper());
        return userFlatFileItemReader;
    }

    @Bean
    public LineMapper<User> linemapper() {
        DefaultLineMapper<User> userDefaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setNames("id", "name", "dept","salary");

        BeanWrapperFieldSetMapper<User> userBeanWrapperFieldSetMapper =  new BeanWrapperFieldSetMapper<>();
        userBeanWrapperFieldSetMapper.setTargetType(User.class);

        userDefaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        userDefaultLineMapper.setFieldSetMapper(userBeanWrapperFieldSetMapper);

        return userDefaultLineMapper;
    }
}
