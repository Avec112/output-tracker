package io.avec.outputtracker.output;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/v1")
public class OutputRestController {

    private final OutputRepository repository;

    public OutputRestController(OutputRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/output")
    public Output save(@RequestBody Output output) {
        log.debug("{}", output);
        return repository.save(output);
    }

    @GetMapping("/test")
    public String test() {
        return "testing, testing";
    }


}
