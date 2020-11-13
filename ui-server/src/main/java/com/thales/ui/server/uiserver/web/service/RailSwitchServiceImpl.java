package com.thales.ui.server.uiserver.web.service;

import com.thales.ui.server.uiserver.web.controllers.TestsController;
import com.thales.ui.server.uiserver.web.domain.ToBeCheckSwitchDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class RailSwitchServiceImpl implements RailSwitchService {
    private static Logger logger = LogManager.getLogger(RailSwitchServiceImpl.class);

    @Override
    public List<RailSwitch> getRailSwitchList() {

        return getDummyRailSwithList();
    }
    public List<RailSwitch> getDummyRailSwithList() {
        List<RailSwitch> ret = new ArrayList<>();

        ret.add(new RailSwitch(1, false, "Text 1"));
        ret.add(new RailSwitch(2, false, "Text 2"));
        ret.add(new RailSwitch(3, true, "Text 3"));
        ret.add(new RailSwitch(4, false, "Text 4"));
        ret.add(new RailSwitch(5, false, "Text 5"));
        ret.add(new RailSwitch(6, true, "Text 6"));
        ret.add(new RailSwitch(7, false, "Text 7"));
        ret.add(new RailSwitch(8, false, "Text 8"));
        return ret;
    }

    @Override
    public List<ToBeCheckSwitchDto> getoBeCheckRailSwitchList() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

        ParameterizedTypeReference<List<ToBeCheckSwitchDto>> typeRef = new ParameterizedTypeReference<List<ToBeCheckSwitchDto>>() { };

        ResponseEntity<List<ToBeCheckSwitchDto>> responseEntity = restTemplate
                .exchange(
                        "http://localhost:8092/to_be_check_switch/partition",
                        HttpMethod.GET, requestEntity, typeRef);
        List<ToBeCheckSwitchDto> ret = responseEntity.getBody();
        logger.info("List of fixed deposit details: \n" + ret);
        return ret;
    }

    @Override
    public boolean markRailSwitchAsChecked(String id, String checkCommment) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> responseEntity = restTemplate
                .postForEntity(
                        "http://localhost:8092/to_be_check_switch/mark_as_checked",
                        new ToBeCheckSwitchDto(Long.parseLong(id), checkCommment),
                        Boolean.class);
        Boolean ret = responseEntity.getBody();
        logger.info("ret: "                + ret);
        return ret;
    }

    @Override
    public Long getGroupIdOfRailswitches() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Long> responseEntity = restTemplate
                .getForEntity(
                        "http://localhost:8092/to_be_check_switch/get_CurrentGroupId",
                        Long.class);
        Long ret = responseEntity.getBody();
        logger.info("ret: "                + ret);
        return ret;
    }
}
