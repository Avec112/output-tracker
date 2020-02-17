package io.avec.outputtracker.client;

import io.avec.outputtracker.service.output.Output;
import io.avec.outputtracker.service.output.OutputRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
public class ClientController {

    private final OutputRepository repository;

    public ClientController(OutputRepository repository) {
        this.repository = repository;
    }

    @RequestMapping
    public String index() {
        return "index";
    }

    @RequestMapping("/output")
    public String output(Model model) {

        Iterable<Output> all = repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        log.debug("{}", all);
        model.addAttribute("list", all);
        return "output";
    }

    @RequestMapping("/keycloak")
    public String testKeycloakSecurityOn() {
        return "keycloak";
    }

}
