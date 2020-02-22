package io.avec.outputtracker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class OutputTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutputTrackerApplication.class, args);
    }

}
