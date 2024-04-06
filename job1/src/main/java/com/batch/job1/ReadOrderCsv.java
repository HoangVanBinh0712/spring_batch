package com.batch.job1;

import com.batch.job1.launcher.MyBatchLauncher;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Log4j2
public class ReadOrderCsv {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ReadOrderCsv.class, args);

        MyBatchLauncher jobLauncher = context.getBean(MyBatchLauncher.class);
        try {
            jobLauncher.runBatchJob();
        } catch (Exception e) {
            log.error("Batch run failed: {1}", e);
        } finally {
            SpringApplication.exit(context);
        }
    }
}