package org.example;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class Main implements ApplicationRunner {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        VaultUtils.printVersion();
    }
}