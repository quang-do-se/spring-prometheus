package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
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
        System.out.println("Hello");
        String tmpdir = System.getProperty("java.io.tmpdir");
        System.out.println("Temp file path: " + tmpdir);
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void overlappingSchedule() throws IOException {
        createFileWithJavaIO();
        createFileWithJavaNIO();
        copyTempFileWithApacheIO();
        copyNewFileWithApacheIO();
    }

    private void createFileWithJavaIO() {
        try {
            File myObj = new File("/data/newFileCreatedByJavaIO.txt");
            if (myObj.createNewFile()) {
                System.out.println("File IO created: " + myObj.getName());
            }
        } catch (IOException e) {
            //
        }
    }

    private void createFileWithJavaNIO() {
        Path newFilePath = Paths.get("/data/newFileCreatedByJavaNIO.txt");
        try {
            Files.createFile(newFilePath);
            System.out.println("File NIO created.");
        } catch (IOException e) {
            //
        }
    }

    private void copyTempFileWithApacheIO() {
        try {
            File tempFile = File.createTempFile("tempFileCreatedByJavaIo", ".txt");
            File newFile = new File("/data/tempFileCopiedByApacheIO.txt");
            FileUtils.copyFile(tempFile, newFile);
        } catch (IOException e) {
            //
        }
    }

    private void copyNewFileWithApacheIO () {
        try {
            Path newFile = Paths.get("/data/anotherFileCreatedByJavaNIO.txt");
            Files.createFile(newFile);

            File copyFile = new File("/data/newFileCopiedByApacheIO.txt");
            FileUtils.copyFile(newFile.toFile(), copyFile);
        } catch (IOException e) {
            //
        }
    }
}