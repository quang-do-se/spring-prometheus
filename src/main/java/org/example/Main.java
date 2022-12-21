package org.example;

import edu.colorado.oit.se.hashicorp.vault.utils.EchelonVault;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
	}

    @Override
    public void run(final ApplicationArguments args) {
        EchelonVault.printVersion();
    }
}