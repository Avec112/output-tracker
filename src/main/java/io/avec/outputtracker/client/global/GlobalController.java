package io.avec.outputtracker.client.global;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/*
    Class for setting global model attributes
 */
@Slf4j
@ControllerAdvice
public class GlobalController {

    private final ApplicationContext context;

    public GlobalController(ApplicationContext context) {
        this.context = context;
    }

    @ModelAttribute("isKeycloakEnabled") // global attribute
    public Boolean isKeycloakEnabled() {
        try {
            // will throw exception if not found
            KeycloakSpringBootProperties properties = context.getBean(KeycloakSpringBootProperties.class);
            log.debug("Keycloak {} enabled", properties.isEnabled() ? "is" : "is not");
            return properties.isEnabled();
        } catch (NoSuchBeanDefinitionException e) {
            log.warn("Keycloak is not enabled!");
        }
        return false;
    }

}
