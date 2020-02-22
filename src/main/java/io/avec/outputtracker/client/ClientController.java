package io.avec.outputtracker.client;

import io.avec.outputtracker.output.Output;
import io.avec.outputtracker.output.OutputRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @RequestMapping("/log")
    public String output(Model model) {
        findAll(model);
        return "log";
    }

    @RequestMapping("/keycloak")
    public String testKeycloakSecurityOn() {
        return "keycloak";
    }

    @GetMapping("/logtablebody")
    public String tableBody(Model model) {
        findAll(model);
        return "/fragments/Log :: table-body";
    }

    private void findAll(Model model) {
        Iterable<Output> all = repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
//        log.debug("{}", all);
        model.addAttribute("list", all);
    }
}
