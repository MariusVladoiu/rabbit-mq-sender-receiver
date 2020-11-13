package com.example.workmanager.service;

import com.example.workmanager.model.RailSwitch;
import com.example.workmanager.model.ToBeCheckSwitch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@Service
public class RailSwitchServiceImpl implements RailSwitchService {
    private static Logger logger = LogManager.getLogger(RailSwitchServiceImpl.class);

    @Override
    public List<RailSwitch> getRailSwitchList() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

        ParameterizedTypeReference<List<RailSwitch>> typeRef = new ParameterizedTypeReference<List<RailSwitch>>() { };

        ResponseEntity<List<RailSwitch>> responseEntity = restTemplate
                .exchange(
                        "http://localhost:8092/railswitch/all",
                        HttpMethod.GET, requestEntity, typeRef);
        List<RailSwitch> ret = responseEntity.getBody();
        logger.info("List of fixed deposit details: \n" + ret);
        return ret;
    }
    @Override
    public List<ToBeCheckSwitch> partitionateCheckingListInSixChunks() {
        List<RailSwitch> allInitList = getRailSwitchList();

        Random random = new Random();

        //AtomicLong contor = new AtomicLong(0);
        List<ToBeCheckSwitch> ret = allInitList.stream()
                .map(x -> {
                    long groupId = random.nextInt(6) + 1;
                    //Long groupId = contor.getAndIncrement() % 6 + 1; // from 1 to 6
                    return new ToBeCheckSwitch((long) x.getId(), false, null, groupId);
                })
                .collect(Collectors.toList());
        return ret;
    }


}
