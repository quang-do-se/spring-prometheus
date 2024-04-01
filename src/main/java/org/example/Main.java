package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
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

        Gauge.builder("is-notifiable", 2024, Main::isNotification)
                           .strongReference(true)
             .register(meterRegistry);

    }

    static int isNotification(int term) {
        return term > 2023 ? 1 : 0;
    }

    @Scheduled(cron = "*/1 * * * * *")
    public void populateMetric() {
        List<String> names = Arrays.asList("superman", "spider-man", "batman");

        Random random = new Random();
        int randomInt = random.nextInt(names.size());
        String name = names.get(randomInt);

        Counter dynamicCounter = Counter.builder("spring.app")
                                        .description("Increase counter in Spring App")
                                        .tags("name", name)
                                        .register(meterRegistry);

        dynamicCounter.increment();

        System.out.println("Increased counter for " + name + ".");
    }
}