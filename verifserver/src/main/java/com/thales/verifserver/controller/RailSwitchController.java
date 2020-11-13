package com.thales.verifserver.controller;


import com.thales.verifserver.model.RailSwitch;
import com.thales.verifserver.service.RailSwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/railswitch")
public class RailSwitchController {
    @Autowired
    private RailSwitchService railSwitchService;


    @CrossOrigin
    @GetMapping(path = "/all")
    public List<RailSwitch> getAll() {
        return railSwitchService.getAll();
    }


}
