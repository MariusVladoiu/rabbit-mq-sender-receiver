package com.thales.verifserver.service;

import com.thales.verifserver.model.RailSwitch;
import com.thales.verifserver.repository.RailSwitchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RailSwitchServiceImpl implements RailSwitchService {

    @Autowired
    private RailSwitchRepository repository;


    @Override
    public List<RailSwitch> getAll() {
        return repository.findAll();
    }

    /*@Override
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
    }*/
}
