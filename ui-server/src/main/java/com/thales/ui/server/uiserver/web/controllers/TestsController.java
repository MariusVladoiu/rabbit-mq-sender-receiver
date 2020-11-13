package com.thales.ui.server.uiserver.web.controllers;

import com.thales.ui.server.uiserver.web.domain.ToBeCheckSwitchDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/test")
public class TestsController {
    private static Logger logger = LogManager.getLogger(TestsController.class);

    @GetMapping(path = "/get_test")
    public @ResponseBody
    String getMessage_1() throws InterruptedException {
        List<ToBeCheckSwitchDto> lst=null;
        WebClient webClient = WebClient.create("http://localhost:8092/to_be_check_switch");
        try {
            lst = getRailSwtchToBeChecked(webClient);
            //getFixedDepositList(webClient);
            //openFixedDeposit(webClient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Thread.sleep(1000);
        System.out.println("===== " + lst);
        return "ret list:  " + lst.toString();
    }

    @GetMapping(path = "/get_test2")
    public Flux<ToBeCheckSwitchDto> getMessage_2() {
        WebClient webClient = WebClient.create("http://localhost:8092/to_be_check_switch");
        Flux<ToBeCheckSwitchDto> retFlux = webClient.get().uri("/all").accept(MediaType.APPLICATION_JSON).retrieve()
                .bodyToFlux(ToBeCheckSwitchDto.class);
        System.out.println("/get_test2 XXXXXXXX");
        return retFlux;
    }

    @GetMapping(path = "/get_test_first")
    public @ResponseBody String getFirstItem() throws InterruptedException {
        ToBeCheckSwitchDto ret = getPersonList();
        System.out.println("===== " + ret);
        return ret.toString();
    }

    private static List<ToBeCheckSwitchDto> getRailSwtchToBeChecked_2(WebClient webClient) {
        List<ToBeCheckSwitchDto> ret = new ArrayList<>();
        Flux<ToBeCheckSwitchDto> retFlux = webClient.get().uri("/all").accept(MediaType.APPLICATION_JSON).retrieve()
                .bodyToFlux(ToBeCheckSwitchDto.class);

        retFlux.subscribe(x -> ret.add(x));

        System.out.println("===== " + ret.size());
        return ret;
    }

    private static List<ToBeCheckSwitchDto> getRailSwtchToBeChecked(WebClient webClient) {
        List<ToBeCheckSwitchDto> ret = new ArrayList<>();
        webClient.get().uri("/all")
                .accept(MediaType.APPLICATION_JSON).retrieve()
                .bodyToFlux(ToBeCheckSwitchDto.class)
                .log()
                /* .subscribe(fixedDeposit -> {
                     logger.info("Fixed Deposit -> " + fixedDeposit);
                     ret.add(fixedDeposit);
                 });*/
                .subscribe(new Subscriber<ToBeCheckSwitchDto>() {
                    private Subscription s;
                    int onNextAmount;

                    @Override
                    public void onSubscribe(final Subscription s) {
                        this.s = s;
                        s.request(2);
                    }

                    @Override
                    public void onNext(final ToBeCheckSwitchDto obj) {
                        ret.add(obj);
                        onNextAmount++;
                        if (onNextAmount % 2 == 0) {
                            s.request(2);
                        }
                    }

                    @Override
                    public void onError(final Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("===== onComplete: "  + ret.size());
                        int ham = 2;
                    }
                });
        //.subscribe(railSwitch -> ret.add(railSwitch));

        System.out.println("===== " + ret.size());
        return ret;
    }
    private static ToBeCheckSwitchDto getPersonList() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ToBeCheckSwitchDto> responseEntity = restTemplate
                .getForEntity(
                        "http://localhost:8092/to_be_check_switch/first",
                        ToBeCheckSwitchDto.class);
        ToBeCheckSwitchDto ret = responseEntity.getBody();
        logger.info("Fixed deposit details for id = 1: \n"
                + ret);
        return ret;
    }


    @GetMapping(path = "/get_all_rails_by_restTemplate")
    public @ResponseBody List<ToBeCheckSwitchDto> getFixedDepositList() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

        ParameterizedTypeReference<List<ToBeCheckSwitchDto>> typeRef = new ParameterizedTypeReference<List<ToBeCheckSwitchDto>>() {
        };

        ResponseEntity<List<ToBeCheckSwitchDto>> responseEntity = restTemplate
                .exchange(
                        "http://localhost:8092/to_be_check_switch/all",
                        HttpMethod.GET, requestEntity, typeRef);
        List<ToBeCheckSwitchDto> ret = responseEntity.getBody();
        logger.info("List of fixed deposit details: \n" + ret);
        return ret;
        //return ret.toString()''
    }

}
