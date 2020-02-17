package io.avec.outputtracker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.avec.outputtracker.service.output.EventType;
import io.avec.outputtracker.service.output.Output;
import io.avec.outputtracker.service.output.OutputRepository;
import io.avec.outputtracker.service.output.PayloadType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@SpringBootApplication
public class OutputTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutputTrackerApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(OutputRepository repository) {
        return args -> {
            repository.save(
                    new Output(
                            getLocalDateToString(),
                            "joe",
                            "SYSTEM_A",
                            EventType.TAB,
                            PayloadType.XML,
                            "<payload>xml</payload>"
                    )
            );

            repository.save(
                    new Output(
                            getLocalDateToString(),
                            "paul",
                            "SYSTEM_B",
                            EventType.STORE,
                            PayloadType.JSON,
                            "{\"payload\" : \"json\"}"
                    )
            );
            repository.save(
                    new Output(
                            getLocalDateToString(),
                            "joe",
                            "SYSTEM_A",
                            EventType.SEND,
                            PayloadType.XML,
                            "<payload>xml2</payload>"
                    )
            );

            repository.save(
                    new Output(
                            getLocalDateToString(),
                            "susan",
                            "SYSTEM_C",
                            EventType.SEND,
                            PayloadType.XXX,
                            "Payload: XXX"
                    )
            );

            repository.save(
                    new Output(
                            getLocalDateToString(),
                            "Jill",
                            "SYSTEM_D",
                            EventType.STORE,
                            PayloadType.JSON,
                            "{\"payload\" : \"json\"}"
                    )
            );


            log.info("Output find all:");
            log.info("----------------");
            ObjectMapper mapper = new ObjectMapper();
            repository.findAll().forEach((e -> {
                // printing as json as it tells us how to build a correct post :-)
                try {
                    log.info("{}", mapper.writeValueAsString(e));
                } catch (JsonProcessingException ex) {
                    log.error(ex.getMessage());
                }
            }));

            log.info("");
            log.info("Output by page (0,2):"); // page 0 with 2 elements
            log.info("---------------------");
            Slice<Output> slice1 = repository.findAll(PageRequest.of(0, 2));
            Slice<Output> slice2 = repository.findAll(slice1.nextPageable());
            Slice<Output> slice3 = repository.findAll(slice2.nextPageable());


            log.info("Slice med {} elementer: {}", slice1.getNumberOfElements(), slice1.getContent());
            log.info("Slice med {} elementer: {}", slice2.getNumberOfElements(), slice2.getContent());
            log.info("Slice med {} elementer: {}", slice3.getNumberOfElements(), slice3.getContent());

        };


    }

    private String getLocalDateToString() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    }

}
