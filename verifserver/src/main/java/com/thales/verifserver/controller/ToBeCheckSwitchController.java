package com.thales.verifserver.controller;


import com.thales.verifserver.model.ToBeCheckSwitch;
import com.thales.verifserver.service.ToBeCheckSwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/to_be_check_switch")
public class ToBeCheckSwitchController {
    @Autowired
    private ToBeCheckSwitchService toBeCheckSwitchService;

    private long currentGroupId = 1;

    @CrossOrigin
    @GetMapping(path = "/partition")
    public List<ToBeCheckSwitch> getAllFromPartition( ) {
        return toBeCheckSwitchService.getCurrentPartition(currentGroupId);
    }

    @CrossOrigin
    @GetMapping(path = "/set_CurrentGroupId")
    public String setCurrentGroupId(@RequestParam(required = false, defaultValue = "1") int groupId) {
        this.currentGroupId = groupId;
        return "Am setat nr listei de verificari la valoarea: " + groupId;
    }
    @CrossOrigin
    @GetMapping(path = "/get_CurrentGroupId")
    public long getCurrentGroupId() {
        return this.currentGroupId;
    }

    @CrossOrigin
    @PostMapping(path = "/mark_as_checked")
    public boolean markAsChecked(@RequestBody ToBeCheckSwitch obj) {
        return toBeCheckSwitchService.markAsChecked(obj);
    }

    @CrossOrigin
    @GetMapping(path = "/first")
    public ToBeCheckSwitch getFirst() {
        return toBeCheckSwitchService.getFirst();
    }
}
