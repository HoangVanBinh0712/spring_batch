package com.batch.job2;

import com.batch.job2.launcher.MyBatchLauncher;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.batch.util", "com.batch.util.listener", "com.batch.job2"})
@Log4j2
public class ReadUserCsv {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ReadUserCsv.class, args);

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