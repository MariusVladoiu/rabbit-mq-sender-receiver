package com.thales.ui.server.uiserver.web.controllers;


import com.thales.ui.server.uiserver.web.service.RailSwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping(path = "/railSwitch")
public class RailSwitchController {

    @Autowired
    private RailSwitchService railSwitchService;

    @RequestMapping("/all")
    public ModelAndView showAll(Model model) {

        model.addAttribute("groupIdOfRailswitches", railSwitchService.getGroupIdOfRailswitches());
        model.addAttribute("railSwitches", railSwitchService.getoBeCheckRailSwitchList());
        return new ModelAndView("welcome/railSwitch");
    }

    @PostMapping(path = "/checkmark/{id}/{checkCommment}")
    public @ResponseBody String markRailSwitchAsChecked(@PathVariable String id, @PathVariable String checkCommment) {
        System.out.println("========== " + id + " checkCommment: " + checkCommment);
        boolean ret = railSwitchService.markRailSwitchAsChecked(id, checkCommment);
        return ret ? "A fost verificat macazul nr. " + id + ". Text verificare: " + checkCommment
                :
                "Eroare! Macazu cu nr. " + id + " nu a putut fi verificat";
    }
}
