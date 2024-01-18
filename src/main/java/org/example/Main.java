package org.example;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
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

    @PostConstruct
    void initCounters() {
        Counter yearCounter = Counter.builder("year")
                                     .description("Year")
                                     .register(meterRegistry);
        yearCounter.increment(2024);
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void populateMetric() {
        Counter testingCounter = Counter.builder("spring.app")
                                        .description("Increase counter in Spring App")
                                        .tags("name", "testing")
                                        .register(meterRegistry);


        Counter anotherCounter = Counter.builder("spring.app")
                                        .description("Increase counter in Spring App")
                                        .tags("name", "duplicate-testing")
                                        .register(meterRegistry);

        testingCounter.increment();
        anotherCounter.increment();

        System.out.println("Increased counter.");
    }
}