package io.avec.outputtracker.output;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@RestController
@RequestMapping("/api/v1")
public class OutputRestController {

    private final AtomicInteger atomicInteger = new AtomicInteger(1);
    private final OutputRepository repository;

    public OutputRestController(OutputRepository repository) {
        this.repository = repository;
    }

//    @PostConstruct
//    private void initializeRepository() {
//        repository.save(createOutput());
//        repository.save(createOutput());
//        repository.save(createOutput());
//    }

    @PostMapping("/output")
    public Output save(@RequestBody Output output) {
        log.debug("{}", output);
        return repository.save(output);
    }

    @GetMapping(value = "/payload/{id}")
    public ResponseEntity<String> payload(@PathVariable long id) {
        Optional<Output> byId = repository.findById(id);
        return byId.map(output -> new ResponseEntity<>(output.getPayload(), HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>("Output with id = " + id + " was not found.", HttpStatus.NOT_FOUND));

    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello friend!";
    }

    @GetMapping("/error")
    public String error() {
        throw new RuntimeException("Oops a simulated error occurred!");
    }

    // post without body
    @PostMapping("/fakeoutput")
    public String fakeOutput() {
        repository.save(createOutput());
        return "success";
    }

    private Output createOutput() {
        int i = atomicInteger.getAndIncrement();
        return new Output(
                "datetime"+i,
                "username"+i,
                "endpoint"+i,
                "eventType"+i,
                "payloadType"+i,
                "payload"+i + "\n<type>xml</type>\n{\"type\":\"json\"}"
        );
    }

}
