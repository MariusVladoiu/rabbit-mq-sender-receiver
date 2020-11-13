package com.thales.ui.server.uiserver.web.controllers;

import com.thales.ui.server.uiserver.web.domain.ToBeCheckSwitchDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@RequestMapping(path = "/railSwitch/webflow")
public class WebFlowRailSwitchController {

    @RequestMapping("/all")
    public ModelAndView showAll(Model model) {
        model.addAttribute("message", "aaaaaaaa");
        model.addAttribute("account", "bbbbbbb");


        WebClient webClient = WebClient.create("http://localhost:8092/to_be_check_switch");
        Mono<List<ToBeCheckSwitchDto>> retFlux = webClient.get().uri("/all").accept(MediaType.APPLICATION_JSON).retrieve()
                .bodyToFlux(ToBeCheckSwitchDto.class).collectList();

        model.addAttribute("railSwitches", retFlux);
        return new ModelAndView("welcome/railSwitch");
    }

}
