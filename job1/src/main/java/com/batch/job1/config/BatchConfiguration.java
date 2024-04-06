package com.batch.job1.config;

import com.batch.job1.dto.Order;
import com.batch.job1.repository.OrderRepository;
import com.batch.util.listener.JobCompletionNotificationListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = {"com.batch.util", "com.batch.util.listener", "com.batch.job1"})
public class BatchConfiguration {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public ItemReader<Order> reader() {
        return new FlatFileItemReaderBuilder<Order>()
                .name("orderItemReader")
                .resource(new ClassPathResource("orders.csv"))
                .delimited()
                .names("CustomerId", "ItemId", "ItemName", "ItemPrice", "PurchaseDate")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Order.class);
                }})
                .build();
    }

    @Bean
    public ItemProcessor<Order, Order> processor() {
        return order -> order;
    }


    @Bean
    public ItemWriter<Order> writer() {
        RepositoryItemWriter<Order> writer = new RepositoryItemWriter<>();
        writer.setRepository(orderRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Job importOrderJob() {
        return new JobBuilder("importOrderJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(new JobCompletionNotificationListener())
                .flow(step())
                .end()
                .build();
    }

    @Bean
    public Step step() {
        return new StepBuilder("step", jobRepository)
                .<Order, Order>chunk(2, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}
