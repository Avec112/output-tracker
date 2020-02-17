package io.avec.outputtracker.service.track;

import io.avec.outputtracker.service.output.Output;
import io.avec.outputtracker.service.output.OutputRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class OutputRestController {

    private final OutputRepository repository;

    public OutputRestController(OutputRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/v1/output")
    public Output save(@RequestBody Output output) {
        log.debug("{}", output);
        return repository.save(output);
    }


}
