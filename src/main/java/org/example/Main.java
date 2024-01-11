package org.example;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    MeterRegistry meterRegistry;

    @Scheduled(cron = "*/5 * * * * *")
    public void populateMetric() {
        Counter counter = Counter.builder("spring.app")
                                 .description("Increase counter in Spring App")
                                 .tags("for", "testing")
                                 .register(meterRegistry);

        counter.increment();
        System.out.println("Increased counter.");
    }
}