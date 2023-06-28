package org.example;

import java.time.LocalDateTime;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import static java.lang.Thread.sleep;

@SpringBootApplication
@EnableScheduling
public class Main implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
	}

    @Override
    public void run(final ApplicationArguments args) {
        System.out.println("Hello");
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void overlappingSchedule() throws InterruptedException {
        System.out.printf("%s - %s - Started %n", LocalDateTime.now(), Thread.currentThread().getName());
        sleep(7000);
        System.out.printf("%s - %s - Finished %n", LocalDateTime.now(), Thread.currentThread().getName());
    }
}