package org.example;

import java.io.IOException;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Main implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(final ApplicationArguments args) {
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void populateMetric() {
        SimpleMeterRegistry registry = new SimpleMeterRegistry();
        Counter counter = Counter.builder("Spring App")
                                 .description("Increase counter in Spring App")
                                 .tags("for", "testing")
                                 .register(registry);

        counter.increment();
        System.out.println("Finished running.");
    }
}