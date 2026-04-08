package br.edu.atitus.greetingservice.controllers;

import br.edu.atitus.greetingservice.configs.GreetingConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

//    @Value("${greeting-service.greeting}")
//    private String greeting;
//
//    @Value("${greeting-service.default-name}")
//    private String defaultName;

    private final GreetingConfig config;
    // Injeção de dependência -
    public GreetingController(GreetingConfig config) {
        this.config = config;
    }

    @GetMapping({"", "/", "/{name}"})
    public String getGreeting(

            @PathVariable (required = false) String name){
        if (name == null || name.isEmpty()){
            name = config.getDefaultName();
        }
        String greetingReturn = String.format("%s %s!!!", config.getGreeting(), name);
        return greetingReturn;
    }

}
