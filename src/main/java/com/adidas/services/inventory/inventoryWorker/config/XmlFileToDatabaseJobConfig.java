package com.adidas.services.inventory.inventoryWorker.config;

import com.adidas.services.inventory.inventoryWorker.batch.DbWriterStudent;
import com.adidas.services.inventory.inventoryWorker.batch.ProcessorStudent;
import com.adidas.services.inventory.inventoryWorker.model.Student;
import com.adidas.services.inventory.inventoryWorker.repository.StudentRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.sql.DataSource;

@Configuration
public class XmlFileToDatabaseJobConfig {

    @Bean
    ItemReader<Student> xmlFileItemReader(Environment environment) {
        StaxEventItemReader<Student> xmlFileReader = new StaxEventItemReader<>();
        xmlFileReader.setResource(new ClassPathResource("students.xml"));
        xmlFileReader.setFragmentRootElementNames(new String[] {"student"});

        Jaxb2Marshaller studentMarshaller = new Jaxb2Marshaller();
        studentMarshaller.setClassesToBeBound(new Class[] {Student.class});
        xmlFileReader.setUnmarshaller(studentMarshaller);

        return xmlFileReader;
    }

    @Bean
    ItemProcessor<Student, Student> xmlFileItemProcessor() {
        return new ProcessorStudent();
    }

    @Bean
    ItemWriter<Student> xmlFileDatabaseItemWriter() {
      return new DbWriterStudent();
    }

    @Bean
    Step xmlFileToDatabaseStep(ItemReader<Student> xmlFileItemReader,
                               ItemProcessor<Student, Student> xmlFileItemProcessor,
                               ItemWriter<Student> xmlFileDatabaseItemWriter,
                               StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("xmlFileToDatabaseStep")
                .<Student, Student>chunk(1)
                .reader(xmlFileItemReader)
                .processor(xmlFileItemProcessor)
                .writer(xmlFileDatabaseItemWriter)
                .build();
    }

    @Bean
    Job xmlFileToDatabaseJob(JobBuilderFactory jobBuilderFactory,
                             @Qualifier("xmlFileToDatabaseStep") Step xmlStudentStep) {
        return jobBuilderFactory.get("xmlFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .flow(xmlStudentStep)
                .end()
                .build();
    }
}
